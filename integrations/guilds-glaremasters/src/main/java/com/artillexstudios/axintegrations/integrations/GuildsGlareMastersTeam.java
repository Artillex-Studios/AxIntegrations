package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import me.glaremasters.guilds.Guilds;
import me.glaremasters.guilds.api.GuildsAPI;
import me.glaremasters.guilds.guild.Guild;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GuildsGlareMastersTeam extends TeamIntegration {
    private GuildsAPI api;

    public GuildsGlareMastersTeam() {
        super("Guilds");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("me.glaremasters.guilds.Guilds");
    }

    @Override
    public boolean setup() {
        api = Guilds.getApi();
        return api != null;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public String getTeam(@NotNull Player player) {
        Guild guild = getObject(player);
        if (guild == null) return null;
        return guild.getName();
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull Player player) {
        Guild guild = getObject(player);
        if (guild == null) return null;
        return guild.getGuildMaster().getAsOfflinePlayer();
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull String teamName) {
        Guild guild = getObject(teamName);
        if (guild == null) return null;
        return guild.getGuildMaster().getAsOfflinePlayer();
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        Guild guild = getObject(player);
        if (guild == null) return List.of();
        return new ArrayList<>(guild.getAllAsPlayers());
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Guild guild = getObject(teamName);
        if (guild == null) return List.of();
        return new ArrayList<>(guild.getAllAsPlayers());
    }

    @NotNull
    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        Guild guild = getObject(player);
        if (guild == null) return List.of();
        return new ArrayList<>(guild.getOnlineAsPlayers());
    }

    @NotNull
    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        Guild guild = getObject(teamName);
        if (guild == null) return List.of();
        return new ArrayList<>(guild.getOnlineAsPlayers());
    }

    @Nullable
    private Guild getObject(@NotNull Player player) {
        return api.getGuildByPlayerId(player.getUniqueId());
    }

    @Nullable
    private Guild getObject(@NotNull String teamName) {
        return api.getGuild(teamName);
    }
}
