package com.artillexstudios.axintegrations.integration.protection.implementation;

import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class WorldGuardProtectionIntegration implements ProtectionIntegration {

    @Override
    public boolean canBuild(Player player, Location location) {
        if (location.getWorld() == null) {
            return false;
        }

        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        World world = BukkitAdapter.adapt(location.getWorld());
        if (WorldGuard.getInstance().getPlatform().getSessionManager().hasBypass(localPlayer, world)) {
            return true;
        }

        return WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().testState(BukkitAdapter.adapt(location), localPlayer, Flags.BUILD);
    }

    @Override
    public boolean canPlace(Player player, Location location) {
        if (location.getWorld() == null) {
            return false;
        }

        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        World world = BukkitAdapter.adapt(location.getWorld());
        if (WorldGuard.getInstance().getPlatform().getSessionManager().hasBypass(localPlayer, world)) {
            return true;
        }

        return WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().testState(BukkitAdapter.adapt(location), localPlayer, Flags.BLOCK_PLACE);
    }

    @Override
    public boolean canBreak(Player player, Location location) {
        if (location.getWorld() == null) {
            return false;
        }

        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        World world = BukkitAdapter.adapt(location.getWorld());
        if (WorldGuard.getInstance().getPlatform().getSessionManager().hasBypass(localPlayer, world)) {
            return true;
        }

        return WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().testState(BukkitAdapter.adapt(location), localPlayer, Flags.BLOCK_BREAK);
    }

    @Override
    public boolean canInteract(Player player, Location location) {
        if (location.getWorld() == null) {
            return false;
        }

        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        World world = BukkitAdapter.adapt(location.getWorld());
        if (WorldGuard.getInstance().getPlatform().getSessionManager().hasBypass(localPlayer, world)) {
            return true;
        }

        return WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().testState(BukkitAdapter.adapt(location), localPlayer, Flags.INTERACT);
    }

    @Override
    public boolean canOpen(Player player, Location location) {
        if (location.getWorld() == null) {
            return false;
        }

        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        World world = BukkitAdapter.adapt(location.getWorld());
        if (WorldGuard.getInstance().getPlatform().getSessionManager().hasBypass(localPlayer, world)) {
            return true;
        }

        return WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().testState(BukkitAdapter.adapt(location), localPlayer, Flags.CHEST_ACCESS);
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("WorldGuard")
        };
    }

    @Override
    public String id() {
        return "worldguard";
    }
}
