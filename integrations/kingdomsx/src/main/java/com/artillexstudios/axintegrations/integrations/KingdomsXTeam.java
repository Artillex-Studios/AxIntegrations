package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kingdoms.constants.group.Kingdom;
import org.kingdoms.constants.player.KingdomPlayer;

import java.util.ArrayList;
import java.util.List;

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
        KingdomPlayer kingdomPlayer = KingdomPlayer.getKingdomPlayer(player);
        if (kingdomPlayer == null) return null;
        Kingdom kingdom = kingdomPlayer.getKingdom();
        if (kingdom == null) return null;
        return kingdom.getName();
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        KingdomPlayer kingdomPlayer = KingdomPlayer.getKingdomPlayer(player);
        if (kingdomPlayer == null) return List.of();
        Kingdom kingdom = kingdomPlayer.getKingdom();
        if (kingdom == null) return List.of();
        return new ArrayList<>(kingdom.getPlayerMembers());
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Kingdom kingdom = Kingdom.getKingdom(teamName);
        if (kingdom == null) return List.of();
        return new ArrayList<>(kingdom.getPlayerMembers());
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        KingdomPlayer kingdomPlayer = KingdomPlayer.getKingdomPlayer(player);
        if (kingdomPlayer == null) return List.of();
        Kingdom kingdom = kingdomPlayer.getKingdom();
        if (kingdom == null) return List.of();
        return new ArrayList<>(kingdom.getOnlineMembers());
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        Kingdom kingdom = Kingdom.getKingdom(teamName);
        if (kingdom == null) return List.of();
        return new ArrayList<>(kingdom.getOnlineMembers());
    }
}
