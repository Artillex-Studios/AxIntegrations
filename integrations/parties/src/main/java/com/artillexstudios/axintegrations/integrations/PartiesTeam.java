package com.artillexstudios.axintegrations.integrations;

import com.alessiodp.parties.api.interfaces.PartiesAPI;
import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PartiesTeam extends TeamIntegration {
    private PartiesAPI api;

    public PartiesTeam() {
        super("Parties");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.alessiodp.parties.api.interfaces.PartiesAPI");
    }

    @Override
    public boolean setup() {
        api = com.alessiodp.parties.api.Parties.getApi();
        return api != null;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public String getTeam(@NotNull Player player) {
        Party party = api.getPartyOfPlayer(player.getUniqueId());
        if (party == null) return null;
        return party.getName();
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        Party party = api.getPartyOfPlayer(player.getUniqueId());
        if (party == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (UUID uuid : party.getMembers()) {
            members.add(Bukkit.getOfflinePlayer(uuid));
        }
        return members;
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Party party = api.getParty(teamName);
        if (party == null) return List.of();
        List<OfflinePlayer> members = new ArrayList<>();
        for (UUID uuid : party.getMembers()) {
            members.add(Bukkit.getOfflinePlayer(uuid));
        }
        return members;
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        Party party = api.getPartyOfPlayer(player.getUniqueId());
        if (party == null) return List.of();
        List<Player> members = new ArrayList<>();
        for (PartyPlayer member : party.getOnlineMembers()) {
            members.add(Bukkit.getPlayer(member.getPlayerUUID()));
        }
        return members;
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        Party party = api.getParty(teamName);
        if (party == null) return List.of();
        List<Player> members = new ArrayList<>();
        for (PartyPlayer member : party.getOnlineMembers()) {
            members.add(Bukkit.getPlayer(member.getPlayerUUID()));
        }
        return members;
    }
}
