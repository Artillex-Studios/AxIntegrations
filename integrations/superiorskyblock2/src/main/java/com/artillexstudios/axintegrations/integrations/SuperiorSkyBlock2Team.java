package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        Island island = getObject(player);
        if (island == null) return null;
        return island.getName();
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull Player player) {
        Island island = getObject(player);
        if (island == null) return null;
        return island.getOwner().asOfflinePlayer();
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull String teamName) {
        Island island = getObject(teamName);
        if (island == null) return null;
        return island.getOwner().asOfflinePlayer();
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        Island island = getObject(player);
        if (island == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (SuperiorPlayer member : island.getIslandMembers(true)) {
            members.add(member.asOfflinePlayer());
        }
        return members;
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Island island = getObject(teamName);
        if (island == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (SuperiorPlayer member : island.getIslandMembers(true)) {
            members.add(member.asOfflinePlayer());
        }
        return members;
    }

    @NotNull
    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        Island island = getObject(player);
        if (island == null) return List.of();
        List<Player> members = new ArrayList<>();
        for (SuperiorPlayer member : island.getIslandMembers(true)) {
            if (!member.isOnline()) continue;
            members.add(member.asPlayer());
        }
        return members;
    }

    @NotNull
    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        Island island = getObject(teamName);
        if (island == null) return List.of();
        List<Player> members = new ArrayList<>();
        for (SuperiorPlayer member : island.getIslandMembers(true)) {
            if (!member.isOnline()) continue;
            members.add(member.asPlayer());
        }
        return members;
    }

    @Nullable
    private Island getObject(@NotNull Player player) {
        SuperiorPlayer superiorPlayer = SuperiorSkyblockAPI.getPlayer(player);
        if (superiorPlayer == null) return null;
        return superiorPlayer.getIsland();
    }

    @Nullable
    private Island getObject(@NotNull String teamName) {
        return SuperiorSkyblockAPI.getIsland(teamName);
    }
}
