package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import me.glaremasters.guilds.Guilds;
import me.glaremasters.guilds.api.GuildsAPI;
import me.glaremasters.guilds.guild.Guild;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
        Guild guild = api.getGuildByPlayerId(player.getUniqueId());
        if (guild == null) return null;
        return guild.getName();
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        Guild guild = api.getGuildByPlayerId(player.getUniqueId());
        if (guild == null) return List.of();
        return new ArrayList<>(guild.getAllAsPlayers());
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Guild guild = api.getGuild(teamName);
        if (guild == null) return List.of();
        return new ArrayList<>(guild.getAllAsPlayers());
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        Guild guild = api.getGuildByPlayerId(player.getUniqueId());
        if (guild == null) return List.of();
        return new ArrayList<>(guild.getOnlineAsPlayers());
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        Guild guild = api.getGuild(teamName);
        if (guild == null) return List.of();
        return new ArrayList<>(guild.getOnlineAsPlayers());
    }
}
