package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import me.TechsCode.UltraEconomy.UltraEconomy;
import me.TechsCode.UltraEconomy.UltraEconomyAPI;
import me.TechsCode.UltraEconomy.objects.Account;
import me.TechsCode.UltraEconomy.objects.Balance;
import me.TechsCode.UltraEconomy.objects.Currency;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class UltraEconomyCurrency extends CurrencyIntegration {
    private UltraEconomyAPI api;
    private Currency currency;

    public UltraEconomyCurrency(String currency) {
        super("UltraEconomy", currency);
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("me.TechsCode.UltraEconomy.UltraEconomyAPI");
    }

    @Override
    public boolean setup() {
        api = UltraEconomy.getAPI();
        if (api == null) return false;
        String name = getCurrency();
        if (name == null) {
            IntegrationManager.print(false, "Failed to register %s! Currency not set!".formatted(getName()));
            return false;
        }
        currency = api.getCurrencies().name(name).orElse(null);
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
        Balance balance = getPlayerBalance(player.getUniqueId());
        if (balance == null) return 0D;
        return balance.getOnHand();
    }

    @NotNull
    @Override
    public CompletableFuture<Double> getBalance(@NotNull UUID playerUUID) {
        Balance balance = getPlayerBalance(playerUUID);
        if (balance == null) return CompletableFuture.completedFuture(0D);
        return CompletableFuture.completedFuture(balance.getOnHand());
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalance(@NotNull UUID playerUUID, double amount) {
        Balance balance = getPlayerBalance(playerUUID);
        if (balance == null) return CompletableFuture.completedFuture(false);
        balance.addHand(amount);
        return CompletableFuture.completedFuture(true);
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID playerUUID, double amount) {
        Balance balance = getPlayerBalance(playerUUID);
        if (balance == null) return CompletableFuture.completedFuture(false);
        balance.removeHand(amount);
        return CompletableFuture.completedFuture(true);
    }

    @Nullable
    private Balance getPlayerBalance(UUID playerUUID) {
        Account account = api.getAccounts().uuid(playerUUID).orElse(null);
        if (account == null) return null;
        return account.getBalance(currency);
    }
}
