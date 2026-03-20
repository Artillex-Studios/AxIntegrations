package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Rules
 * - currency is null unless it is a multi currency supporting plugin
 */
public abstract class CurrencyIntegration extends Integration {
    private final String currency;

    public CurrencyIntegration(String name, String currency) {
        super(name, IntegrationType.CURRENCY);
        this.currency = currency;
    }

    @Nullable
    public String getCurrency() {
        return currency;
    }

    @Override
    @NotNull
    public final String getFormattedName() {
        return "%s-%s".formatted(getName(), currency);
    }

    public abstract boolean worksOffline();

    @NotNull
    public abstract Number getBalance(Player player);

    public abstract boolean giveBalance(Player player, Number amount);

    public abstract boolean takeBalance(Player player, Number amount);

    @NotNull
    public abstract CompletableFuture<Number> getBalanceAsync(UUID uniqueId);

    @NotNull
    public abstract CompletableFuture<Boolean> giveBalanceAsync(UUID uniqueId, Number amount);

    @NotNull
    public abstract CompletableFuture<Boolean> takeBalanceAsync(UUID uniqueId, Number amount);
}
