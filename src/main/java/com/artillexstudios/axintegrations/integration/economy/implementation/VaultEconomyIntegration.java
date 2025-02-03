package com.artillexstudios.axintegrations.integration.economy.implementation;

import com.artillexstudios.axintegrations.integration.economy.EconomyIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.concurrent.CompletableFuture;

public final class VaultEconomyIntegration extends EconomyIntegration {
    private final Economy economy;

    public VaultEconomyIntegration() {
        RegisteredServiceProvider<Economy> provider = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (provider == null) {
            this.economy = null;
        } else {
            this.loaded = true;
            this.economy = provider.getProvider();
        }
    }

    @Override
    public CompletableFuture<Boolean> give(OfflinePlayer player, double amount) {
        return CompletableFuture.completedFuture(this.economy.depositPlayer(player, amount).transactionSuccess());
    }

    @Override
    public CompletableFuture<Boolean> has(OfflinePlayer offlinePlayer, double amount) {
        return CompletableFuture.completedFuture(this.economy.has(offlinePlayer, amount));
    }

    @Override
    public CompletableFuture<Double> balance(OfflinePlayer offlinePlayer) {
        return CompletableFuture.completedFuture(this.economy.getBalance(offlinePlayer));
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("Vault")
        };
    }

    @Override
    public String id() {
        return "vault";
    }
}
