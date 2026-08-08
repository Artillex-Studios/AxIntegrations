package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class VaultCurrency extends CurrencyIntegration {
    private Economy api;

    public VaultCurrency() {
        super("Vault", null);
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("net.milkbowl.vault.economy.Economy");
    }

    @Override
    public boolean setup() {
        RegisteredServiceProvider<Economy> provider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (provider == null) return false;
        api = provider.getProvider();
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
        return true;
    }

    @Override
    public double getBalance(@NotNull Player player) {
        return api.getBalance(player);
    }

    @NotNull
    @Override
    public CompletableFuture<Double> getBalance(@NotNull UUID playerUUID) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        return CompletableFuture.completedFuture(api.getBalance(offlinePlayer));
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalance(@NotNull UUID playerUUID, double amount) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        EconomyResponse response = api.depositPlayer(offlinePlayer, amount);
        return CompletableFuture.completedFuture(response.transactionSuccess());
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID playerUUID, double amount) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        EconomyResponse response = api.withdrawPlayer(offlinePlayer, amount);
        return CompletableFuture.completedFuture(response.transactionSuccess());
    }
}
