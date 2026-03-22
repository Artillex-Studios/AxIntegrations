package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ProtectionIntegration;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TownyProtection extends ProtectionIntegration {
    private TownyAPI api;

    public TownyProtection() {
        super("Towny");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.palmergames.bukkit.towny.TownyAPI");
    }

    @Override
    public boolean setup() {
        api = TownyAPI.getInstance();
        if (api == null) return false;
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean canPlace(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, Material.STONE, TownyPermission.ActionType.BUILD);
    }

    @Override
    public boolean canBreak(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, Material.STONE, TownyPermission.ActionType.DESTROY);
    }

    @Override
    public boolean canInteract(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, Material.STONE, TownyPermission.ActionType.ITEM_USE);
    }

    @Override
    public boolean canOpenContainer(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, Material.CHEST, TownyPermission.ActionType.ITEM_USE);
    }

    private boolean hasPermission(Player player, Location location, Material material, TownyPermission.ActionType actionType) {
        return PlayerCacheUtil.getCachePermission(player, location, material, actionType);
    }
}
