package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ProtectionIntegration;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.internal.platform.WorldGuardPlatform;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WorldGuardProtection extends ProtectionIntegration {
    private com.sk89q.worldguard.WorldGuard instance;
    private WorldGuardPlugin pluginInstance;
    private WorldGuardPlatform platform;

    public WorldGuardProtection() {
        super("WorldGuard");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.sk89q.worldguard.bukkit.WorldGuardPlugin");
    }

    @Override
    public boolean setup() {
        instance = com.sk89q.worldguard.WorldGuard.getInstance();
        platform = instance.getPlatform();
        pluginInstance = WorldGuardPlugin.inst();
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean canPlace(@NotNull Player player, @NotNull Location location) {
        if (!testState(player, location, Flags.BUILD)) return false;
        return testState(player, location, Flags.BLOCK_PLACE);
    }

    @Override
    public boolean canBreak(@NotNull Player player, @NotNull Location location) {
        if (!testState(player, location, Flags.BUILD)) return false;
        return testState(player, location, Flags.BLOCK_BREAK);
    }

    @Override
    public boolean canInteract(@NotNull Player player, @NotNull Location location) {
        return testState(player, location, Flags.INTERACT);
    }

    @Override
    public boolean canOpenContainer(@NotNull Player player, @NotNull Location location) {
        return testState(player, location, Flags.CHEST_ACCESS);
    }

    @Override
    public boolean canPvP(@NotNull Player player, @NotNull Location location) {
        return testState(player, location, Flags.PVP);
    }

    private boolean testState(Player player, Location location, StateFlag flag) {
        LocalPlayer localPlayer = pluginInstance.wrapPlayer(player);
        World world = BukkitAdapter.adapt(player.getWorld());

        final boolean canBypass = platform.getSessionManager().hasBypass(localPlayer, world);
        if (canBypass) return true;

        RegionContainer container = platform.getRegionContainer();
        RegionQuery query = container.createQuery();
        com.sk89q.worldedit.util.Location worldGuardLocation = BukkitAdapter.adapt(location);
        return query.testState(worldGuardLocation, localPlayer, flag);
    }
}
