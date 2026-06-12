package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import me.ulrich.clans.Clans;
import me.ulrich.clans.api.ClanAPIManager;
import me.ulrich.clans.api.PlayerAPIManager;
import me.ulrich.clans.data.ClanData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UltimateClansTeam extends TeamIntegration {
    private Clans instance;
    private ClanAPIManager clanManager;
    private PlayerAPIManager playerManager;

    public UltimateClansTeam() {
        super("UltimateClans");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("me.ulrich.clans.Clans");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean setup() {
        instance = (Clans) Bukkit.getPluginManager().getPlugin("UltimateClans");
        if (instance == null) return false;
        clanManager = instance.getClanAPI();
        if (clanManager == null) return false;
        playerManager = instance.getPlayerAPI();
        return playerManager != null;
    }

    @Override
    public String getTeam(@NotNull Player player) {
        ClanData clanData = getObject(player);
        if (clanData == null) return null;
        return clanData.getTagNoColor();
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull Player player) {
        ClanData clanData = getObject(player);
        if (clanData == null) return null;
        return Bukkit.getOfflinePlayer(clanData.getLeader());
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull String teamName) {
        ClanData clanData = getObject(teamName);
        if (clanData == null) return null;
        return Bukkit.getOfflinePlayer(clanData.getLeader());
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        ClanData clanData = getObject(player);
        if (clanData == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (UUID uuid : clanData.getMembers()) {
            members.add(Bukkit.getOfflinePlayer(uuid));
        }
        return members;
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        ClanData clanData = getObject(teamName);
        if (clanData == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (UUID uuid : clanData.getMembers()) {
            members.add(Bukkit.getOfflinePlayer(uuid));
        }
        return members;
    }

    @NotNull
    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        ClanData clanData = getObject(player);
        if (clanData == null) return List.of();
        List<Player> members = new ArrayList<>();
        for (UUID uuid : clanData.getOnlineMembers()) {
            members.add(Bukkit.getPlayer(uuid));
        }
        return members;
    }

    @NotNull
    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        ClanData clanData = getObject(teamName);
        if (clanData == null) return List.of();
        List<Player> members = new ArrayList<>();
        for (UUID uuid : clanData.getOnlineMembers()) {
            members.add(Bukkit.getPlayer(uuid));
        }
        return members;
    }

    @Nullable
    private ClanData getObject(@NotNull Player player) {
        return playerManager.getPlayerClan(player.getUniqueId()).orElse(null);
    }

    @Nullable
    private ClanData getObject(@NotNull String teamName) {
        return clanManager.getClanDataByTag(teamName).orElse(null);
    }
}
