package com.artillexstudios.axintegrations.integration.protection.implementation;

import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public final class TownyProtectionIntegration implements ProtectionIntegration {

    @Override
    public boolean canBuild(Player player, Location location) {
        return PlayerCacheUtil.getCachePermission(player, location, Material.STONE, TownyPermission.ActionType.BUILD);
    }

    @Override
    public boolean canPlace(Player player, Location location) {
        return PlayerCacheUtil.getCachePermission(player, location, Material.STONE, TownyPermission.ActionType.BUILD);
    }

    @Override
    public boolean canBreak(Player player, Location location) {
        return PlayerCacheUtil.getCachePermission(player, location, Material.STONE, TownyPermission.ActionType.DESTROY);
    }

    @Override
    public boolean canInteract(Player player, Location location) {
        return PlayerCacheUtil.getCachePermission(player, location, Material.STONE, TownyPermission.ActionType.ITEM_USE);
    }

    @Override
    public boolean canOpen(Player player, Location location) {
        return PlayerCacheUtil.getCachePermission(player, location, Material.STONE, TownyPermission.ActionType.ITEM_USE);
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("Towny")
        };
    }

    @Override
    public String id() {
        return "towny";
    }
}
