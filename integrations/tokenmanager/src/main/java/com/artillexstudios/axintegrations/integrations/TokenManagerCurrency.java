package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import me.realized.tokenmanager.api.TokenManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class TokenManagerCurrency extends CurrencyIntegration {
    private TokenManager api;

    public TokenManagerCurrency() {
        super("TokenManager", null);
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("me.realized.tokenmanager.api.TokenManager");
    }

    @Override
    public boolean setup() {
        api = (TokenManager) Bukkit.getServer().getPluginManager().getPlugin("TokenManager");
        if (api == null) return false;
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
        return false;
    }

    @Override
    public double getBalance(@NotNull Player player) {
        return (double) api.getTokens(player).orElse(0);
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
        return CompletableFuture.completedFuture(api.addTokens(player, (long) amount));
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID playerUUID, double amount) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) return CompletableFuture.completedFuture(false);
        return CompletableFuture.completedFuture(api.removeTokens(player, (long) amount));
    }
}
