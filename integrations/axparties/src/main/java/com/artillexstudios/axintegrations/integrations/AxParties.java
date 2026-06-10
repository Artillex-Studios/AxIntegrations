package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import com.artillexstudios.axparties.api.AxPartiesAPI;
import com.artillexstudios.axparties.party.Party;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AxParties extends TeamIntegration {

    public AxParties() {
        super("AxParties");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.artillexstudios.axparties.api.AxPartiesAPI");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public String getTeam(@NotNull Player player) {
        Party party = AxPartiesAPI.getPartyOf(player).orElse(null);
        if (party == null) return null;
        return party.getName();
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        Party party = AxPartiesAPI.getPartyOf(player).orElse(null);
        if (party == null) return List.of();
        return new ArrayList<>(party.getMembers());
    }

    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Party party = AxPartiesAPI.getPartyByName(teamName).orElse(null);
        if (party == null) return List.of();
        return new ArrayList<>(party.getMembers());
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        Party party = AxPartiesAPI.getPartyOf(player).orElse(null);
        if (party == null) return List.of();
        return new ArrayList<>(party.getOnlineMembers());
    }

    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        Party party = AxPartiesAPI.getPartyByName(teamName).orElse(null);
        if (party == null) return List.of();
        return new ArrayList<>(party.getOnlineMembers());
    }
}
