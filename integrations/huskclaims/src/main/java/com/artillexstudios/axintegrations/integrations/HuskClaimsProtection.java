package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ProtectionIntegration;
import net.william278.huskclaims.api.BukkitHuskClaimsAPI;
import net.william278.huskclaims.libraries.cloplib.operation.OperationType;
import net.william278.huskclaims.position.Position;
import net.william278.huskclaims.user.OnlineUser;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HuskClaimsProtection extends ProtectionIntegration {
    private BukkitHuskClaimsAPI api;

    public HuskClaimsProtection() {
        super("HuskClaims");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("net.william278.huskclaims.api.BukkitHuskClaimsAPI");
    }

    @Override
    public boolean setup() {
        api = BukkitHuskClaimsAPI.getInstance();
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
