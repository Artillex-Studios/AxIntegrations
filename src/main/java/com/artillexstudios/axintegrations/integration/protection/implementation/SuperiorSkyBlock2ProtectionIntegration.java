package com.artillexstudios.axintegrations.integration.protection.implementation;

import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.island.IslandPrivilege;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class SuperiorSkyBlock2ProtectionIntegration implements ProtectionIntegration {

    @Override
    public boolean canBuild(Player player, Location location) {
        Island island = SuperiorSkyblockAPI.getIslandAt(location);
        if (island == null) {
            return true;
        }

        return island.hasPermission(player, IslandPrivilege.getByName("BUILD"));
    }

    @Override
    public boolean canPlace(Player player, Location location) {
        Island island = SuperiorSkyblockAPI.getIslandAt(location);
        if (island == null) {
            return true;
        }

        return island.hasPermission(player, IslandPrivilege.getByName("BUILD"));
    }

    @Override
    public boolean canBreak(Player player, Location location) {
        Island island = SuperiorSkyblockAPI.getIslandAt(location);
        if (island == null) {
            return true;
        }

        return island.hasPermission(player, IslandPrivilege.getByName("BREAK"));
    }

    @Override
    public boolean canInteract(Player player, Location location) {
        Island island = SuperiorSkyblockAPI.getIslandAt(location);
        if (island == null) {
            return true;
        }

        return island.hasPermission(player, IslandPrivilege.getByName("CHEST_ACCESS"));
    }

    @Override
    public boolean canOpen(Player player, Location location) {
        return false;
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("SuperiorSkyBlock2")
        };
    }

    @Override
    public String id() {
        return "superiorskyblock";
    }
}
