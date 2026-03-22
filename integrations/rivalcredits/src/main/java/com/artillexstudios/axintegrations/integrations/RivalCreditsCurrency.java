package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import me.rivaldev.credits.CreditAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class RivalCreditsCurrency extends CurrencyIntegration {
    private CreditAPI api;

    public RivalCreditsCurrency() {
        super("RivalCredits", null);
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("me.rivaldev.credits.CreditAPI");
    }

    @Override
    public boolean setup() {
        api = CreditAPI.getInstance();
        if (api == null) return false;
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

    @NotNull
    @Override
    public Number getBalance(@NotNull Player player) {
        return api.getCredits(player);
    }

    @NotNull
    @Override
    public CompletableFuture<Number> getBalance(@NotNull UUID playerUUID) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        return CompletableFuture.completedFuture(api.getCredits(offlinePlayer));
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalance(@NotNull UUID playerUUID, @NotNull Number amount) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        api.addCredits(offlinePlayer, amount.doubleValue());
        return CompletableFuture.completedFuture(true);
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID playerUUID, @NotNull Number amount) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        api.removeCredits(offlinePlayer, amount.doubleValue());
        return CompletableFuture.completedFuture(true);
    }
}
