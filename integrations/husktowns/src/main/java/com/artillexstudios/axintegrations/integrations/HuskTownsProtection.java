package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ProtectionIntegration;
import net.william278.husktowns.api.BukkitHuskTownsAPI;
import net.william278.husktowns.claim.Position;
import net.william278.husktowns.libraries.cloplib.operation.OperationType;
import net.william278.husktowns.user.OnlineUser;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HuskTownsProtection extends ProtectionIntegration {
    private BukkitHuskTownsAPI api;

    public HuskTownsProtection() {
        super("HuskTowns");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("net.william278.husktowns.api.BukkitHuskTownsAPI");
    }

    @Override
    public boolean setup() {
        api = BukkitHuskTownsAPI.getInstance();
        if (api == null) return false;
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean canPlace(@NotNull Player player, @NotNull Location location) {
        return testState(player, location, OperationType.BLOCK_PLACE);
    }

    @Override
    public boolean canBreak(@NotNull Player player, @NotNull Location location) {
        return testState(player, location, OperationType.BLOCK_BREAK);
    }

    @Override
    public boolean canInteract(@NotNull Player player, @NotNull Location location) {
        return testState(player, location, OperationType.BLOCK_INTERACT);
    }

    @Override
    public boolean canOpenContainer(@NotNull Player player, @NotNull Location location) {
        return testState(player, location, OperationType.CONTAINER_OPEN);
    }

    private boolean testState(Player player, Location location, OperationType operationType) {
        OnlineUser onlineUser = api.getOnlineUser(player.getUniqueId());
        if (onlineUser == null) return true;
        Position position = api.getPosition(location);
        if (position == null) return true;
        return api.isOperationAllowed(onlineUser, operationType, position);
    }
}
