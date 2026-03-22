package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.IntegrationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Rules
 * - if the plugin doesn't support XP, return 0
 */
public abstract class LevelIntegration extends Integration {

    /**
     * returns all loaded integrations
     */
    public static List<LevelIntegration> list() {
        return IntegrationManager.getIntegrations(LevelIntegration.class);
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#list()} instead
     */
    @Nullable
    public static LevelIntegration one() {
        return list().stream().findFirst().orElse(null);
    }

    public LevelIntegration(String name) {
        super(name, IntegrationType.LEVEL);
    }

    public abstract long getLevel(@NotNull UUID player);

    public abstract double getXP(@NotNull UUID player);

    /**
     * required xp for the next level
     */
    public abstract double getRequiredXP(@NotNull UUID player);

    /**
     * the amount of xp the player needs to get to level up
     */
    public abstract double getRemainingXP(@NotNull UUID player);

    /**
     * reset level and xp
     */
    public CompletableFuture<Boolean> reset(@NotNull UUID player) {
        setXP(player, 0);
        return setLevel(player, 0);
    }

    public abstract CompletableFuture<Boolean> setLevel(@NotNull UUID playerUUID, long amount);

    public CompletableFuture<Boolean> giveLevel(@NotNull UUID playerUUID, long amount) {
        return setLevel(playerUUID, getLevel(playerUUID) + amount);
    }

    public CompletableFuture<Boolean> takeLevel(@NotNull UUID playerUUID, long amount) {
        return setLevel(playerUUID, getLevel(playerUUID) - amount);
    }

    public abstract CompletableFuture<Boolean> setXP(@NotNull UUID playerUUID, double amount);

    public CompletableFuture<Boolean> giveXP(@NotNull UUID playerUUID, double amount) {
        return setXP(playerUUID, getXP(playerUUID) + amount);
    }

    public CompletableFuture<Boolean> takeXP(@NotNull UUID playerUUID, double amount) {
        return setXP(playerUUID, getXP(playerUUID) - amount);
    }
}
