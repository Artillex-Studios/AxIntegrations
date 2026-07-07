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
import org.jetbrains.annotations.Nullable;

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
        ITeam iTeam = getObject(player);
        if (iTeam == null) return null;
        return iTeam.getTag();
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull Player player) {
        ITeam iTeam = getObject(player);
        if (iTeam == null) return null;
        return Bukkit.getOfflinePlayer(iTeam.getOwner().getUuid());
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull String teamName) {
        ITeam iTeam = getObject(teamName);
        if (iTeam == null) return null;
        return Bukkit.getOfflinePlayer(iTeam.getOwner().getUuid());
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        ITeam iTeam = getObject(player);
        if (iTeam == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (IMember member : iTeam.getAllMembers()) {
            members.add(Bukkit.getOfflinePlayer(member.getUuid()));
        }
        return members;
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        ITeam iTeam = getObject(teamName);
        if (iTeam == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (IMember member : iTeam.getAllMembers()) {
            members.add(Bukkit.getOfflinePlayer(member.getUuid()));
        }
        return members;
    }

    @Nullable
    private ITeam getObject(@NotNull Player player) {
        return manager.getTeam(player).orElse(null);
    }

    @Nullable
    private ITeam getObject(@NotNull String teamName) {
        return manager.getByTag(teamName).orElse(null);
    }
}
