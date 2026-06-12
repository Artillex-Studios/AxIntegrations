package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import com.booksaw.betterTeams.PlayerRank;
import com.booksaw.betterTeams.Team;
import com.booksaw.betterTeams.TeamPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class BetterTeamsTeam extends TeamIntegration {

    public BetterTeamsTeam() {
        super("BetterTeams");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.booksaw.betterTeams.Team");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public String getTeam(@NotNull Player player) {
        Team team = getObject(player);
        if (team == null) return null;
        return team.getName();
    }

    @Override
    public OfflinePlayer getLeader(@NonNull Player player) {
        Team team = getObject(player);
        if (team == null) return null;
        for (TeamPlayer teamPlayer : team.getRank(PlayerRank.OWNER)) {
            return teamPlayer.getPlayer();
        }
        return null;
    }

    @Override
    public OfflinePlayer getLeader(@NonNull String teamName) {
        Team team = getObject(teamName);
        if (team == null) return null;
        for (TeamPlayer teamPlayer : team.getRank(PlayerRank.OWNER)) {
            return teamPlayer.getPlayer();
        }
        return null;
    }

    @Override
    public boolean isLeader(@NonNull Player player) {
        Team team = getObject(player);
        if (team == null) return false;
        TeamPlayer teamPlayer = team.getTeamPlayer(player);
        if (teamPlayer == null) return false;
        return teamPlayer.getRank() == PlayerRank.OWNER;
    }

    @Override
    public @NonNull List<OfflinePlayer> getMembers(@NotNull Player player) {
        Team team = getObject(player);
        if (team == null) return List.of();
        return new ArrayList<>(team.getMembers().getOfflinePlayers());
    }

    @Override
    public @NonNull List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Team team = getObject(teamName);
        if (team == null) return List.of();
        return new ArrayList<>(team.getMembers().getOfflinePlayers());
    }

    @Override
    public @NonNull List<Player> getOnlineMembers(@NotNull Player player) {
        Team team = getObject(player);
        if (team == null) return List.of();
        return new ArrayList<>(team.getOnlineMembers());
    }

    @Override
    public @NonNull List<Player> getOnlineMembers(@NotNull String teamName) {
        Team team = getObject(teamName);
        if (team == null) return List.of();
        return new ArrayList<>(team.getOnlineMembers());
    }

    @Nullable
    private Team getObject(@NotNull Player player) {
        return Team.getTeam(player);
    }

    @Nullable
    private Team getObject(@NotNull String teamName) {
        return Team.getTeamByName(teamName);
    }
}
