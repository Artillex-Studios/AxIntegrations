package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kingdoms.constants.group.Kingdom;
import org.kingdoms.constants.player.KingdomPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class KingdomsXTeam extends TeamIntegration {

    public KingdomsXTeam() {
        super("KingdomsX");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("org.kingdoms.constants.group.Kingdom");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public String getTeam(@NotNull Player player) {
        Kingdom kingdom = getObject(player);
        if (kingdom == null) return null;
        return kingdom.getName();
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull Player player) {
        Kingdom kingdom = getObject(player);
        if (kingdom == null) return null;
        UUID owner = kingdom.getOwnerId();
        if (owner == null) return null;
        return Bukkit.getOfflinePlayer(owner);
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull String teamName) {
        Kingdom kingdom = getObject(teamName);
        if (kingdom == null) return null;
        UUID owner = kingdom.getOwnerId();
        if (owner == null) return null;
        return Bukkit.getOfflinePlayer(owner);
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        Kingdom kingdom = getObject(player);
        if (kingdom == null) return List.of();
        return new ArrayList<>(kingdom.getPlayerMembers());
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Kingdom kingdom = getObject(teamName);
        if (kingdom == null) return List.of();
        return new ArrayList<>(kingdom.getPlayerMembers());
    }

    @NotNull
    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        Kingdom kingdom = getObject(player);
        if (kingdom == null) return List.of();
        return new ArrayList<>(kingdom.getOnlineMembers());
    }

    @NotNull
    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        Kingdom kingdom = getObject(teamName);
        if (kingdom == null) return List.of();
        return new ArrayList<>(kingdom.getOnlineMembers());
    }

    @Nullable
    private Kingdom getObject(@NotNull Player player) {
        KingdomPlayer kingdomPlayer = KingdomPlayer.getKingdomPlayer(player);
        if (kingdomPlayer == null) return null;
        return kingdomPlayer.getKingdom();
    }

    @Nullable
    private Kingdom getObject(@NotNull String teamName) {
        return Kingdom.getKingdom(teamName);
    }
}
