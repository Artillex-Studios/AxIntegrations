package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;
import su.nightexpress.excellenteconomy.api.ExcellentEconomyAPI;
import su.nightexpress.excellenteconomy.api.currency.ExcellentCurrency;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ExcellentEconomyCurrency extends CurrencyIntegration {
    private ExcellentEconomyAPI api;
    private ExcellentCurrency currency;

    public ExcellentEconomyCurrency(String currency) {
        super("ExcellentEconomy", currency);
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("su.nightexpress.excellenteconomy.api.ExcellentEconomyAPI");
    }

    @Override
    public boolean setup() {
        RegisteredServiceProvider<ExcellentEconomyAPI> provider = Bukkit.getServer().getServicesManager().getRegistration(ExcellentEconomyAPI.class);
        if (provider == null) return false;
        api = provider.getProvider();
        String name = getCurrency();
        if (name == null) {
            IntegrationManager.print(false, "Failed to register %s! Currency not set!".formatted(getName()));
            return false;
        }
        currency = api.getCurrency(name);
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
    public double getBalance(@NotNull Player player) {
        return api.getBalance(player, currency);
    }

    @NotNull
    @Override
    public CompletableFuture<Double> getBalance(@NotNull UUID playerUUID) {
        CompletableFuture<Double> cf = new CompletableFuture<>();
        api.getBalanceAsync(playerUUID, currency).thenAccept(cf::complete);
        return cf;
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalance(@NotNull UUID playerUUID, double amount) {
        CompletableFuture<Boolean> cf = new CompletableFuture<>();
        api.depositAsync(playerUUID, currency, amount).thenAccept(result -> {
            cf.complete(result.success());
        });
        return cf;
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID playerUUID, double amount) {
        CompletableFuture<Boolean> cf = new CompletableFuture<>();
        api.withdrawAsync(playerUUID, currency, amount).thenAccept(result -> {
            cf.complete(result.success());
        });
        return cf;
    }
}
