package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SaberFactionsTeam extends TeamIntegration {
    private Factions factions;
    private FPlayers fPlayers;

    public SaberFactionsTeam() {
        super("SaberFactions");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.massivecraft.factions.Factions");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean setup() {
        factions = Factions.getInstance();
        if (factions == null) return false;
        fPlayers = FPlayers.getInstance();
        return fPlayers != null;
    }

    @Override
    public String getTeam(@NotNull Player player) {
        FPlayer fplayer = fPlayers.getByPlayer(player);
        if (!fplayer.hasFaction()) return null;
        return fplayer.getFaction().getTag();
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        FPlayer fplayer = fPlayers.getByPlayer(player);
        if (!fplayer.hasFaction()) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (FPlayer member : fplayer.getFaction().getFPlayers()) {
            members.add(member.getPlayer());
        }
        return members;
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Faction faction = factions.getByTag(teamName);
        if (faction == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (FPlayer member : faction.getFPlayers()) {
            members.add(member.getPlayer());
        }
        return members;
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        FPlayer fplayer = fPlayers.getByPlayer(player);
        if (!fplayer.hasFaction()) return List.of();
        return new ArrayList<>(fplayer.getFaction().getOnlinePlayers());
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        Faction faction = factions.getByTag(teamName);
        if (faction == null) return List.of();
        return new ArrayList<>(faction.getOnlinePlayers());
    }
}
