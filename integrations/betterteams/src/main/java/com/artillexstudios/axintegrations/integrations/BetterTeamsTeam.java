package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import com.booksaw.betterTeams.Team;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
        Team team = Team.getTeam(player);
        if (team == null) return null;
        return team.getName();
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        Team team = Team.getTeam(player);
        if (team == null) return List.of();
        return new ArrayList<>(team.getMembers().getOfflinePlayers());
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Team team = Team.getTeamByName(teamName);
        if (team == null) return List.of();
        return new ArrayList<>(team.getMembers().getOfflinePlayers());
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        Team team = Team.getTeam(player);
        if (team == null) return List.of();
        return new ArrayList<>(team.getOnlineMembers());
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        Team team = Team.getTeamByName(teamName);
        if (team == null) return List.of();
        return new ArrayList<>(team.getOnlineMembers());
    }
}
