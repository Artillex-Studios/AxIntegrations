package com.artillexstudios.axintegrations.integration.economy;

import com.artillexstudios.axintegrations.integration.Integration;
import org.bukkit.OfflinePlayer;

import java.util.concurrent.CompletableFuture;

public abstract class EconomyIntegration implements Integration {
    protected boolean loaded = false;

    public boolean loaded() {
        return this.loaded;
    }

    public abstract CompletableFuture<Boolean> give(OfflinePlayer player, double amount);

    public abstract CompletableFuture<Boolean> has(OfflinePlayer offlinePlayer, double amount);

    public abstract CompletableFuture<Double> balance(OfflinePlayer offlinePlayer);

    public CompletableFuture<Boolean> take(OfflinePlayer player, double amount) {
        return this.give(player, -amount);
    }
}
