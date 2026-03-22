package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ProtectionIntegration;
import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.flags.type.Flags;
import me.angeschossen.lands.api.flags.type.RoleFlag;
import me.angeschossen.lands.api.land.LandWorld;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class LandsProtection extends ProtectionIntegration {
    private LandsIntegration api;

    public LandsProtection() {
        super("Lands");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("me.angeschossen.lands.api.LandsIntegration");
    }

    @Override
    public boolean setup() {
        api = LandsIntegration.of(JavaPlugin.getProvidingPlugin(LandsProtection.class));
        if (api == null) return false;
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean canPlace(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, Flags.BLOCK_PLACE);
    }

    @Override
    public boolean canBreak(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, Flags.BLOCK_BREAK);
    }

    @Override
    public boolean canInteract(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, Flags.INTERACT_GENERAL);
    }

    @Override
    public boolean canOpenContainer(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, Flags.INTERACT_CONTAINER);
    }

    private boolean hasPermission(Player player, Location location, RoleFlag flag) {
        LandWorld landWorld = api.getWorld(location.getWorld());
        if (landWorld == null) return true;
        return landWorld.hasRoleFlag(player.getUniqueId(), location, flag);
    }
}
