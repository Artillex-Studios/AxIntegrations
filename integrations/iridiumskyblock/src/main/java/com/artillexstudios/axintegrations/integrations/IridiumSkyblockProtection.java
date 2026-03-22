package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ProtectionIntegration;
import com.iridium.iridiumskyblock.api.IridiumSkyblockAPI;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.User;
import com.iridium.iridiumskyblock.dependencies.iridiumteams.PermissionType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class IridiumSkyblockProtection extends ProtectionIntegration {
    private IridiumSkyblockAPI api;

    public IridiumSkyblockProtection() {
        super("IridiumSkyblock");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.iridium.iridiumskyblock.api.IridiumSkyblockAPI");
    }

    @Override
    public boolean setup() {
        api = IridiumSkyblockAPI.getInstance();
        if (api == null) return false;
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean canPlace(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, PermissionType.BLOCK_PLACE);
    }

    @Override
    public boolean canBreak(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, PermissionType.BLOCK_BREAK);
    }

    @Override
    public boolean canInteract(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, PermissionType.INTERACT);
    }

    @Override
    public boolean canOpenContainer(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, PermissionType.OPEN_CONTAINERS);
    }

    private boolean hasPermission(Player player, Location location, PermissionType permission) {
        Island island = api.getIslandViaLocation(location).orElse(null);
        if (island == null) return true;
        User user = api.getUser(player);
        if (user == null) return true;
        return api.getIslandPermission(island, user, permission);
    }
}
