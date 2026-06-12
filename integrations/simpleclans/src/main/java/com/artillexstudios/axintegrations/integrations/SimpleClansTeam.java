package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.managers.ClanManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimpleClansTeam extends TeamIntegration {
    private SimpleClans instance;
    private ClanManager manager;

    public SimpleClansTeam() {
        super("SimpleClans");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("net.sacredlabyrinth.phaed.simpleclans.managers.ClanManager");
    }

    @Override
    public boolean setup() {
        instance = SimpleClans.getInstance();
        if (instance == null) return false;
        manager = SimpleClans.getInstance().getClanManager();
        return manager != null;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public String getTeam(@NotNull Player player) {
        Clan clan = getObject(player);
        if (clan == null) return null;
        return clan.getTag();
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull Player player) {
        Clan clan = getObject(player);
        if (clan == null) return null;
        for (ClanPlayer leader : clan.getLeaders()) {
            return Bukkit.getOfflinePlayer(leader.getUniqueId());
        }
        return null;
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull String teamName) {
        Clan clan = getObject(teamName);
        if (clan == null) return null;
        for (ClanPlayer leader : clan.getLeaders()) {
            return Bukkit.getOfflinePlayer(leader.getUniqueId());
        }
        return null;
    }

    @Override
    public boolean isLeader(@NotNull Player player) {
        Clan clan = getObject(player);
        if (clan == null) return false;
        return clan.isLeader(player);
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        Clan clan = getObject(player);
        if (clan == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (ClanPlayer member : clan.getMembers()) {
            members.add(Bukkit.getOfflinePlayer(member.getUniqueId()));
        }
        return members;
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Clan clan = getObject(teamName);
        if (clan == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (ClanPlayer member : clan.getMembers()) {
            members.add(Bukkit.getOfflinePlayer(member.getUniqueId()));
        }
        return members;
    }

    @NotNull
    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        Clan clan = getObject(player);
        if (clan == null) return List.of();
        List<Player> members = new ArrayList<>();
        for (ClanPlayer member : clan.getMembers()) {
            members.add(Bukkit.getPlayer(member.getUniqueId()));
        }
        return members;
    }

    @NotNull
    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        Clan clan = getObject(teamName);
        if (clan == null) return List.of();
        List<Player> members = new ArrayList<>();
        for (ClanPlayer member : clan.getMembers()) {
            members.add(Bukkit.getPlayer(member.getUniqueId()));
        }
        return members;
    }

    @Nullable
    private Clan getObject(@NotNull Player player) {
        ClanPlayer clanPlayer = manager.getClanPlayer(player);
        if (clanPlayer == null) return null;
        return clanPlayer.getClan();
    }

    @Nullable
    private Clan getObject(@NotNull String teamName) {
        return manager.getClan(teamName);
    }
}
