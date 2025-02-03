package com.artillexstudios.axintegrations.integration.protection.implementation;

import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import com.iridium.iridiumskyblock.api.IridiumSkyblockAPI;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.User;
import com.iridium.iridiumteams.PermissionType;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Optional;

public final class IridiumSkyBlockProtectionIntegration implements ProtectionIntegration {

    @Override
    public boolean canBuild(Player player, Location location) {
        User user = IridiumSkyblockAPI.getInstance().getUser(player);
        Island island = this.islandAt(location);
        if (island == null) {
            return true;
        }

        return IridiumSkyblockAPI.getInstance().getIslandPermission(island, user, PermissionType.BLOCK_PLACE);
    }

    @Override
    public boolean canPlace(Player player, Location location) {
        User user = IridiumSkyblockAPI.getInstance().getUser(player);
        Island island = this.islandAt(location);
        if (island == null) {
            return true;
        }

        return IridiumSkyblockAPI.getInstance().getIslandPermission(island, user, PermissionType.BLOCK_PLACE);
    }

    @Override
    public boolean canBreak(Player player, Location location) {
        User user = IridiumSkyblockAPI.getInstance().getUser(player);
        Island island = this.islandAt(location);
        if (island == null) {
            return true;
        }

        return IridiumSkyblockAPI.getInstance().getIslandPermission(island, user, PermissionType.BLOCK_BREAK);
    }

    @Override
    public boolean canInteract(Player player, Location location) {
        User user = IridiumSkyblockAPI.getInstance().getUser(player);
        Island island = this.islandAt(location);
        if (island == null) {
            return true;
        }

        return IridiumSkyblockAPI.getInstance().getIslandPermission(island, user, PermissionType.DOORS);
    }

    @Override
    public boolean canOpen(Player player, Location location) {
        User user = IridiumSkyblockAPI.getInstance().getUser(player);
        Island island = this.islandAt(location);
        if (island == null) {
            return true;
        }

        return IridiumSkyblockAPI.getInstance().getIslandPermission(island, user, PermissionType.OPEN_CONTAINERS);
    }

    private Island islandAt(Location location) {
        Optional<Island> optionalIsland = IridiumSkyblockAPI.getInstance().getIslandViaLocation(location);
        return optionalIsland.orElse(null);

    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("IridiumSkyBlock")
        };
    }

    @Override
    public String id() {
        return "iridiumskyblock";
    }
}
