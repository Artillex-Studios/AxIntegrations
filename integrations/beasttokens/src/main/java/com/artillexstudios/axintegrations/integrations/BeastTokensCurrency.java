package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import me.mraxetv.beasttokens.api.BeastTokensAPI;
import me.mraxetv.beasttokens.api.handlers.BTTokensManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class BeastTokensCurrency extends CurrencyIntegration {
    private BTTokensManager manager;

    public BeastTokensCurrency() {
        super("BeastTokens", null);
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("me.mraxetv.beasttokens.api.BeastTokensAPI");
    }

    @Override
    public boolean setup() {
        manager = BeastTokensAPI.getTokensManager();
        if (manager == null) return false;
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
        return manager.getTokens(player);
    }

    @NotNull
    @Override
    public CompletableFuture<Number> getBalance(@NotNull UUID playerUUID) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        return CompletableFuture.completedFuture(manager.getTokens(offlinePlayer));
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalance(@NotNull UUID playerUUID, @NotNull Number amount) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        manager.addTokens(offlinePlayer, amount.doubleValue());
        return CompletableFuture.completedFuture(true);
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID playerUUID, @NotNull Number amount) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        manager.removeTokens(offlinePlayer, amount.doubleValue());
        return CompletableFuture.completedFuture(true);
    }
}
