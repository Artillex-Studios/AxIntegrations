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
import java.util.UUID;

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

    @Nullable
    public abstract String getTeam(@NotNull Player player);

    public boolean isMember(@NotNull Player player, @NotNull String teamName) {
        return Objects.equals(getTeam(player), teamName);
    }

    public boolean isSameTeam(@NotNull Player one, @NotNull Player two) {
        String team1 = getTeam(one);
        if (team1 == null) return false;
        return team1.equals(getTeam(two));
    }

    @Nullable
    public abstract OfflinePlayer getLeader(@NotNull Player player);

    @Nullable
    public abstract OfflinePlayer getLeader(@NotNull String teamName);

    public boolean isLeader(@NotNull Player player) {
        OfflinePlayer leader = getLeader(player);
        if (leader == null) return false;
        return leader.getUniqueId().equals(player.getUniqueId());
    }

    @NotNull
    public abstract List<OfflinePlayer> getMembers(@NotNull Player player);

    @NotNull
    public abstract List<OfflinePlayer> getMembers(@NotNull String teamName);

    @NotNull
    public List<Player> getOnlineMembers(@NotNull Player player) {
        return getMembers(player).stream()
                .map(OfflinePlayer::getPlayer)
                .filter(Objects::nonNull)
                .toList();
    }

    @NotNull
    public List<Player> getOnlineMembers(@NotNull String teamName) {
        return getMembers(teamName).stream()
                .map(OfflinePlayer::getPlayer)
                .filter(Objects::nonNull)
                .toList();
    }
}
