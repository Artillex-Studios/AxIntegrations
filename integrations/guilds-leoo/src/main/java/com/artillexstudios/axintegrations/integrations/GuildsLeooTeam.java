package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import me.leoo.guilds.api.objects.GuildAPI;
import me.leoo.guilds.api.objects.guild.GuildProvider;
import me.leoo.guilds.api.objects.guild.GuildView;
import me.leoo.guilds.api.objects.player.PlayerView;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GuildsLeooTeam extends TeamIntegration {
    private GuildAPI api;
    private GuildProvider provider;

    public GuildsLeooTeam() {
        super("Guilds");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("me.leoo.guilds.api.objects.GuildAPI");
    }

    @Override
    public boolean setup() {
        api = GuildAPI.get();
        if (api == null) return false;
        provider = api.getGuildProvider();
        return provider != null;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public String getTeam(@NotNull Player player) {
        GuildView guild = provider.getByPlayer(player);
        if (guild == null) return null;
        return guild.getName();
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        GuildView guild = provider.getByPlayer(player);
        if (guild == null) return List.of();
        Set<OfflinePlayer> members = new HashSet<>();
        for (PlayerView member : guild.getOfflinePlayers()) {
            members.add(member.getOfflinePlayer());
        }
        for (PlayerView member : guild.getOnlinePlayers()) {
            members.add(member.getOfflinePlayer());
        }
        return new ArrayList<>(members);
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        GuildView guild = provider.getByName(teamName);
        if (guild == null) return List.of();
        Set<OfflinePlayer> members = new HashSet<>();
        for (PlayerView member : guild.getOfflinePlayers()) {
            members.add(member.getOfflinePlayer());
        }
        for (PlayerView member : guild.getOnlinePlayers()) {
            members.add(member.getOfflinePlayer());
        }
        return new ArrayList<>(members);
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        GuildView guild = provider.getByPlayer(player);
        if (guild == null) return List.of();
        List<Player> members = new ArrayList<>();
        for (PlayerView member : guild.getOnlinePlayers()) {
            members.add(member.getOnlinePlayer());
        }
        return new ArrayList<>(members);
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        GuildView guild = provider.getByName(teamName);
        if (guild == null) return List.of();
        List<Player> members = new ArrayList<>();
        for (PlayerView member : guild.getOnlinePlayers()) {
            members.add(member.getOnlinePlayer());
        }
        return new ArrayList<>(members);
    }
}
