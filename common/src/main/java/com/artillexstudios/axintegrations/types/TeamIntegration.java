package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.IntegrationType;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * Rules
 * -
 */
public abstract class TeamIntegration extends Integration {

    /**
     * returns all loaded integrations
     */
    public static List<TeamIntegration> list() {
        return IntegrationManager.getIntegrations(TeamIntegration.class);
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#list()} instead
     */
    @Nullable
    public static TeamIntegration one() {
        return list().stream().findFirst().orElse(null);
    }

    public TeamIntegration(String name) {
        super(name, IntegrationType.TEAM);
    }

    public abstract String getTeam(@NotNull Player player);

    public boolean isMember(@NotNull Player player, @NotNull String teamName) {
        return getTeam(player).equals(teamName);
    }

    public abstract List<OfflinePlayer> getMembers(@NotNull Player player);

    public abstract List<OfflinePlayer> getMembers(@NotNull String teamName);

    public List<Player> getOnlineMembers(@NotNull Player player) {
        return getMembers(player).stream()
                .map(OfflinePlayer::getPlayer)
                .filter(Objects::nonNull)
                .toList();
    }

    public List<Player> getOnlineMembers(@NotNull String teamName) {
        return getMembers(teamName).stream()
                .map(OfflinePlayer::getPlayer)
                .filter(Objects::nonNull)
                .toList();
    }
}
