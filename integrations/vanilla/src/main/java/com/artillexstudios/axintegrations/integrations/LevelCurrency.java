package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class LevelCurrency extends CurrencyIntegration {

    public LevelCurrency() {
        super("Level", null);
    }

    @Override
    public boolean canLoad() {
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean worksOffline() {
        return false;
    }

    @NotNull
    @Override
    public Number getBalance(@NotNull Player player) {
        return player.getLevel();
    }

    @NotNull
    @Override
    public CompletableFuture<Number> getBalance(@NotNull UUID playerUUID) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) return CompletableFuture.completedFuture(0);
        return CompletableFuture.completedFuture(getBalance(player));
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalance(@NotNull UUID playerUUID, @NotNull Number amount) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) return CompletableFuture.completedFuture(false);
        player.setLevel(player.getLevel() + amount.intValue());
        return CompletableFuture.completedFuture(true);
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID playerUUID, @NotNull Number amount) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) return CompletableFuture.completedFuture(false);
        player.setLevel(player.getLevel() - amount.intValue());
        return CompletableFuture.completedFuture(true);
    }
}
