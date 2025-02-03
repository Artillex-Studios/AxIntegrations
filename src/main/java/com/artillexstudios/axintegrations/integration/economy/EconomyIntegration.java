package com.artillexstudios.axintegrations.integration.economy;

import com.artillexstudios.axintegrations.integration.Integration;
import org.bukkit.OfflinePlayer;

import java.util.concurrent.CompletableFuture;

public interface EconomyIntegration extends Integration {

    CompletableFuture<Boolean> give(OfflinePlayer player, double amount);

    CompletableFuture<Boolean> has(OfflinePlayer offlinePlayer, double amount);

    CompletableFuture<Double> balance(OfflinePlayer offlinePlayer);

    default CompletableFuture<Boolean> take(OfflinePlayer player, double amount) {
        return this.give(player, -amount);
    }
}