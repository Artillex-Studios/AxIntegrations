package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
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

    @NotNull
    @Override
    public Number getBalance(Player player) {
        return CoinsEngineAPI.getBalance(player, currency);
    }

    @Override
    public boolean giveBalance(Player player, Number amount) {
        CoinsEngineAPI.addBalance(player, currency, amount.doubleValue());
        return true;
    }

    @Override
    public boolean takeBalance(Player player, Number amount) {
        CoinsEngineAPI.removeBalance(player, currency, amount.doubleValue());
        return true;
    }

    @NotNull
    @Override
    public CompletableFuture<Number> getBalanceAsync(UUID playerUUID) {
        throw new RuntimeException("Feature not supported");
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalanceAsync(UUID playerUUID, Number amount) {
        throw new RuntimeException("Feature not supported");
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalanceAsync(UUID playerUUID, Number amount) {
        throw new RuntimeException("Feature not supported");
    }
}
