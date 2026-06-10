package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import dev.kitteh.factions.FPlayer;
import dev.kitteh.factions.FPlayers;
import dev.kitteh.factions.Faction;
import dev.kitteh.factions.Factions;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FactionsUUIDTeam extends TeamIntegration {

    public FactionsUUIDTeam() {
        super("FactionsUUID");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("dev.kitteh.factions.FPlayer");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public String getTeam(@NotNull Player player) {
        FPlayer fplayer = FPlayers.fPlayers().get(player);
        if (!fplayer.hasFaction()) return null;
        return fplayer.faction().tag();
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        FPlayer fplayer = FPlayers.fPlayers().get(player);
        if (!fplayer.hasFaction()) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (FPlayer member : fplayer.faction().members()) {
            members.add(Bukkit.getOfflinePlayer(member.uniqueId()));
        }
        return members;
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Faction faction = Factions.factions().get(teamName);
        if (faction == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (FPlayer member : faction.members()) {
            members.add(Bukkit.getOfflinePlayer(member.uniqueId()));
        }
        return members;
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        FPlayer fplayer = FPlayers.fPlayers().get(player);
        if (!fplayer.hasFaction()) return List.of();
        return new ArrayList<>(fplayer.faction().membersOnlineAsPlayers());
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        Faction faction = Factions.factions().get(teamName);
        if (faction == null) return List.of();
        return new ArrayList<>(faction.membersOnlineAsPlayers());
    }
}
