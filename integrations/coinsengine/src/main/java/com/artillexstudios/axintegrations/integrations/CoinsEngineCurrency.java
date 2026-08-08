package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import su.nightexpress.coinsengine.api.CoinsEngineAPI;
import su.nightexpress.coinsengine.api.currency.Currency;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class CoinsEngineCurrency extends CurrencyIntegration {
    private Currency currency;

    public CoinsEngineCurrency(String currency) {
        super("CoinsEngine", currency);
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("su.nightexpress.coinsengine.api.CoinsEngineAPI");
    }

    @Override
    public boolean setup() {
        String name = getCurrency();
        if (name == null) {
            IntegrationManager.print(false, "Failed to register %s! Currency not set!".formatted(getName()));
            return false;
        }
        currency = CoinsEngineAPI.getCurrency(name);
        if (currency == null) {
            IntegrationManager.print(false, "Failed to register %s! Currency '%s' not found!".formatted(getName(), name));
            return false;
        }
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

    @Override
    public boolean usesDecimals() {
        return currency.isDecimal();
    }

    @Override
    public double getBalance(@NotNull Player player) {
        return CoinsEngineAPI.getBalance(player, currency);
    }

    @NotNull
    @Override
    public CompletableFuture<Double> getBalance(@NotNull UUID playerUUID) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) return CompletableFuture.completedFuture(0D);
        return CompletableFuture.completedFuture(getBalance(player));
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalance(@NotNull UUID playerUUID, double amount) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) return CompletableFuture.completedFuture(false);
        CoinsEngineAPI.addBalance(player, currency, amount);
        return CompletableFuture.completedFuture(true);
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID playerUUID, double amount) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) return CompletableFuture.completedFuture(false);
        CoinsEngineAPI.removeBalance(player, currency, amount);
        return CompletableFuture.completedFuture(true);
    }
}
