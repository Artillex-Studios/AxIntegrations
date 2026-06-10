package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import com.zeltuv.teams.api.ITeamPlugin;
import com.zeltuv.teams.api.ZelTeamsAPI;
import com.zeltuv.teams.api.cache.IMember;
import com.zeltuv.teams.api.cache.ITeam;
import com.zeltuv.teams.api.manager.ITeamManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ZelTeamsTeam extends TeamIntegration {
    private ITeamPlugin api;
    private ITeamManager manager;

    public ZelTeamsTeam() {
        super("ZelTeams");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.zeltuv.teams.api.ITeamPlugin");
    }

    @Override
    public boolean setup() {
        api = ZelTeamsAPI.getInstance();
        if (api == null) return false;
        manager = api.getTeamManager();
        return manager != null;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public String getTeam(@NotNull Player player) {
        ITeam iTeam = manager.getTeam(player).orElse(null);
        if (iTeam == null) return null;
        return iTeam.getName();
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        ITeam iTeam = manager.getTeam(player).orElse(null);
        if (iTeam == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (IMember member : iTeam.getAllMembers()) {
            members.add(Bukkit.getOfflinePlayer(member.getUuid()));
        }
        return members;
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        ITeam iTeam = manager.getTeamByName(teamName).orElse(null);
        if (iTeam == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (IMember member : iTeam.getAllMembers()) {
            members.add(Bukkit.getOfflinePlayer(member.getUuid()));
        }
        return members;
    }
}
