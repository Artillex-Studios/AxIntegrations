package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import com.artillexstudios.axparties.api.AxPartiesAPI;
import com.artillexstudios.axparties.party.Party;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        Party party = getObject(player);
        if (party == null) return null;
        return party.getName();
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull Player player) {
        Party party = getObject(player);
        if (party == null) return null;
        return party.getOwner();
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull String teamName) {
        Party party = getObject(teamName);
        if (party == null) return null;
        return party.getOwner();
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        Party party = getObject(player);
        if (party == null) return List.of();
        return new ArrayList<>(party.getMembers());
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Party party = getObject(teamName);
        if (party == null) return List.of();
        return new ArrayList<>(party.getMembers());
    }

    @NotNull
    @Override
    public List<Player> getOnlineMembers(@NotNull Player player) {
        Party party = getObject(player);
        if (party == null) return List.of();
        return new ArrayList<>(party.getOnlineMembers());
    }

    @NotNull
    @Override
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        Party party = getObject(teamName);
        if (party == null) return List.of();
        return new ArrayList<>(party.getOnlineMembers());
    }

    @Nullable
    private Party getObject(@NotNull Player player) {
        return AxPartiesAPI.getPartyOf(player).orElse(null);
    }

    @Nullable
    private Party getObject(@NotNull String teamName) {
        return AxPartiesAPI.getPartyByName(teamName).orElse(null);
    }
}
