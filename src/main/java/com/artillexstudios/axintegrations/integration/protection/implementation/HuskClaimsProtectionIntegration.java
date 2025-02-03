package com.artillexstudios.axintegrations.integration.protection.implementation;

import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import net.william278.huskclaims.api.HuskClaimsAPI;
import net.william278.huskclaims.libraries.cloplib.operation.OperationType;
import net.william278.huskclaims.position.Position;
import net.william278.huskclaims.user.OnlineUser;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class HuskClaimsProtectionIntegration implements ProtectionIntegration {

    @Override
    public boolean canBuild(Player player, Location location) {
        OnlineUser user = HuskClaimsAPI.getInstance().getOnlineUser(player.getUniqueId());
        Position position = HuskClaimsAPI.getInstance().getPosition(location.getX(), location.getY(), location.getZ(), location.getWorld().getName());
        return HuskClaimsAPI.getInstance().isOperationAllowed(user, OperationType.BLOCK_PLACE, position);
    }

    @Override
    public boolean canPlace(Player player, Location location) {
        OnlineUser user = HuskClaimsAPI.getInstance().getOnlineUser(player.getUniqueId());
        Position position = HuskClaimsAPI.getInstance().getPosition(location.getX(), location.getY(), location.getZ(), location.getWorld().getName());
        return HuskClaimsAPI.getInstance().isOperationAllowed(user, OperationType.BLOCK_PLACE, position);
    }

    @Override
    public boolean canBreak(Player player, Location location) {
        OnlineUser user = HuskClaimsAPI.getInstance().getOnlineUser(player.getUniqueId());
        Position position = HuskClaimsAPI.getInstance().getPosition(location.getX(), location.getY(), location.getZ(), location.getWorld().getName());
        return HuskClaimsAPI.getInstance().isOperationAllowed(user, OperationType.BLOCK_BREAK, position);
    }

    @Override
    public boolean canInteract(Player player, Location location) {
        OnlineUser user = HuskClaimsAPI.getInstance().getOnlineUser(player.getUniqueId());
        Position position = HuskClaimsAPI.getInstance().getPosition(location.getX(), location.getY(), location.getZ(), location.getWorld().getName());
        return HuskClaimsAPI.getInstance().isOperationAllowed(user, OperationType.BLOCK_INTERACT, position);
    }

    @Override
    public boolean canOpen(Player player, Location location) {
        OnlineUser user = HuskClaimsAPI.getInstance().getOnlineUser(player.getUniqueId());
        Position position = HuskClaimsAPI.getInstance().getPosition(location.getX(), location.getY(), location.getZ(), location.getWorld().getName());
        return HuskClaimsAPI.getInstance().isOperationAllowed(user, OperationType.CONTAINER_OPEN, position);
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("HuskClaims")
        };
    }

    @Override
    public String id() {
        return "huskclaims";
    }
}
