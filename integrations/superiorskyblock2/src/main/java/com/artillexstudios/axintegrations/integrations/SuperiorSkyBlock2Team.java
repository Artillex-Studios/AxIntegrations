package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SuperiorSkyBlock2Team extends TeamIntegration {

    public SuperiorSkyBlock2Team() {
        super("SuperiorSkyBlock2");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public String getTeam(@NotNull Player player) {
        SuperiorPlayer superiorPlayer = SuperiorSkyblockAPI.getPlayer(player);
        if (superiorPlayer == null) return null;
        Island island = superiorPlayer.getIsland();
        if (island == null) return null;
        return island.getName();
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        SuperiorPlayer superiorPlayer = SuperiorSkyblockAPI.getPlayer(player);
        if (superiorPlayer == null) return List.of();
        Island island = superiorPlayer.getIsland();
        if (island == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (SuperiorPlayer member : island.getIslandMembers(true)) {
            members.add(member.asOfflinePlayer());
        }
        return members;
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Island island = SuperiorSkyblockAPI.getIsland(teamName);
        if (island == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (SuperiorPlayer member : island.getIslandMembers(true)) {
            members.add(member.asOfflinePlayer());
        }
        return members;
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        SuperiorPlayer superiorPlayer = SuperiorSkyblockAPI.getPlayer(player);
        if (superiorPlayer == null) return List.of();
        Island island = superiorPlayer.getIsland();
        if (island == null) return List.of();
        List<Player> members = new ArrayList<>();
        for (SuperiorPlayer member : island.getIslandMembers(true)) {
            if (!member.isOnline()) continue;
            members.add(member.asPlayer());
        }
        return members;
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        Island island = SuperiorSkyblockAPI.getIsland(teamName);
        if (island == null) return List.of();
        List<Player> members = new ArrayList<>();
        for (SuperiorPlayer member : island.getIslandMembers(true)) {
            if (!member.isOnline()) continue;
            members.add(member.asPlayer());
        }
        return members;
    }
}
