package com.artillexstudios.axintegrations.integration.protection.implementation;

import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;

import net.william278.husktowns.api.BukkitHuskTownsAPI;
import net.william278.husktowns.api.HuskTownsAPI;
import net.william278.husktowns.claim.Position;
import net.william278.husktowns.libraries.cloplib.operation.OperationType;
import net.william278.husktowns.user.OnlineUser;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class HuskTownsProtectionIntegration implements ProtectionIntegration {

    @Override
    public boolean canBuild(Player player, Location location) {
        OnlineUser user = BukkitHuskTownsAPI.getInstance().getOnlineUser(player);
        Position position = BukkitHuskTownsAPI.getInstance().getPosition(location);
        return HuskTownsAPI.getInstance().isOperationAllowed(user, OperationType.BLOCK_PLACE, position);
    }

    @Override
    public boolean canPlace(Player player, Location location) {
        OnlineUser user = BukkitHuskTownsAPI.getInstance().getOnlineUser(player);
        Position position = BukkitHuskTownsAPI.getInstance().getPosition(location);
        return HuskTownsAPI.getInstance().isOperationAllowed(user, OperationType.BLOCK_PLACE, position);
    }

    @Override
    public boolean canBreak(Player player, Location location) {
        OnlineUser user = BukkitHuskTownsAPI.getInstance().getOnlineUser(player);
        Position position = BukkitHuskTownsAPI.getInstance().getPosition(location);
        return HuskTownsAPI.getInstance().isOperationAllowed(user, OperationType.BLOCK_BREAK, position);
    }

    @Override
    public boolean canInteract(Player player, Location location) {
        OnlineUser user = BukkitHuskTownsAPI.getInstance().getOnlineUser(player);
        Position position = BukkitHuskTownsAPI.getInstance().getPosition(location);
        return HuskTownsAPI.getInstance().isOperationAllowed(user, OperationType.BLOCK_INTERACT, position);
    }

    @Override
    public boolean canOpen(Player player, Location location) {
        OnlineUser user = BukkitHuskTownsAPI.getInstance().getOnlineUser(player);
        Position position = BukkitHuskTownsAPI.getInstance().getPosition(location);
        return HuskTownsAPI.getInstance().isOperationAllowed(user, OperationType.CONTAINER_OPEN, position);
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("HuskTowns")
        };
    }

    @Override
    public String id() {
        return "husktowns";
    }
}
