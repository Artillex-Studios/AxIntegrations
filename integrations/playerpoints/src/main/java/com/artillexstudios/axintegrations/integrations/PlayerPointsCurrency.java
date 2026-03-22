package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PlayerPointsCurrency extends CurrencyIntegration {
    private PlayerPoints instance;
    private PlayerPointsAPI api;

    public PlayerPointsCurrency() {
        super("PlayerPoints", null);
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("org.black_ixx.playerpoints.PlayerPoints");
    }

    @Override
    public boolean setup() {
        instance = PlayerPoints.getInstance();
        if (instance == null) return false;
        api = instance.getAPI();
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
        return api.look(player.getUniqueId());
    }

    @NotNull
    @Override
    public CompletableFuture<Number> getBalance(@NotNull UUID playerUUID) {
        return CompletableFuture.completedFuture(api.look(playerUUID));
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalance(@NotNull UUID playerUUID, @NotNull Number amount) {
        return CompletableFuture.completedFuture(api.give(playerUUID, amount.intValue()));
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID playerUUID, @NotNull Number amount) {
        return CompletableFuture.completedFuture(api.take(playerUUID, amount.intValue()));
    }
}
