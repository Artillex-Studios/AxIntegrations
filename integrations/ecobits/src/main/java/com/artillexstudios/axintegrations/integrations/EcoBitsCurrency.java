package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import com.artillexstudios.axintegrations.utils.NumberTool;
import com.willfp.ecobits.currencies.Currencies;
import com.willfp.ecobits.currencies.Currency;
import com.willfp.ecobits.currencies.CurrencyUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class EcoBitsCurrency extends CurrencyIntegration {
    private Currency currency;

    public EcoBitsCurrency(String currency) {
        super("EcoBits", currency);
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.willfp.ecobits.currencies.Currency");
    }

    @Override
    public boolean setup() {
        String name = getCurrency();
        if (name == null) {
            IntegrationManager.print(false, "Failed to register %s! Currency not set!".formatted(getName()));
            return false;
        }
        currency = Currencies.getByID(name);
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
        return true;
    }

    @Override
    public boolean usesDecimals() {
        return currency.isDecimal();
    }

    @Override
    public double getBalance(@NotNull Player player) {
        return CurrencyUtils.getBalance(player, currency).doubleValue();
    }

    @NotNull
    @Override
    public CompletableFuture<Double> getBalance(@NotNull UUID playerUUID) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        return CompletableFuture.completedFuture(CurrencyUtils.getBalance(offlinePlayer, currency).doubleValue());
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalance(@NotNull UUID playerUUID, double amount) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        CurrencyUtils.adjustBalance(offlinePlayer, currency, NumberTool.toBigDecimal(amount));
        return CompletableFuture.completedFuture(true);
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID playerUUID, double amount) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        CurrencyUtils.adjustBalance(offlinePlayer, currency, NumberTool.toBigDecimal(amount).negate());
        return CompletableFuture.completedFuture(false);
    }
}
