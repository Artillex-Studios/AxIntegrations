package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import net.brcdev.gangs.GangsPlusApi;
import net.brcdev.gangs.gang.Gang;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GangsPlusTeam extends TeamIntegration {

    public GangsPlusTeam() {
        super("GangsPlus");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("net.brcdev.gangs.GangsPlusApi");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public String getTeam(@NotNull Player player) {
        Gang gang = getObject(player);
        if (gang == null) return null;
        return gang.getName();
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull Player player) {
        Gang gang = getObject(player);
        if (gang == null) return null;
        return gang.getOwner();
    }

    @Nullable
    @Override
    public OfflinePlayer getLeader(@NotNull String teamName) {
        Gang gang = getObject(teamName);
        if (gang == null) return null;
        return gang.getOwner();
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull Player player) {
        Gang gang = getObject(player);
        if (gang == null) return List.of();
        return new ArrayList<>(gang.getAllMembers());
    }

    @NotNull
    @Override
    public List<OfflinePlayer> getMembers(@NotNull String teamName) {
        Gang gang = getObject(teamName);
        if (gang == null) return List.of();
        return new ArrayList<>(gang.getAllMembers());
    }

    @Nullable
    private Gang getObject(@NotNull Player player) {
        return GangsPlusApi.getPlayersGang(player);
    }

    @Nullable
    private Gang getObject(@NotNull String teamName) {
        for (Gang gang : GangsPlusApi.getAllGangs()) {
            if (gang.getName().equals(teamName)) return gang;
        }
        return null;
    }
}
