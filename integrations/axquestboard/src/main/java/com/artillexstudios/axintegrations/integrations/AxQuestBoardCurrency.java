package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import com.artillexstudios.axquestboard.api.AxQuestBoardAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AxQuestBoardCurrency extends CurrencyIntegration {

    public AxQuestBoardCurrency() {
        super("AxQuestBoard", null);
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.artillexstudios.axquestboard.api.AxQuestBoardAPI");
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
        return AxQuestBoardAPI.getPoints(player.getUniqueId()); // todo: add better method
    }

    @NotNull
    @Override
    public CompletableFuture<Double> getBalance(@NotNull UUID playerUUID) {
        CompletableFuture<Double> cf = new CompletableFuture<>();
        AxQuestBoardAPI.getQuestPoints(playerUUID).thenAccept(integer -> {
            cf.complete(integer.doubleValue());
        });
        return cf;
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalance(@NotNull UUID playerUUID, double amount) {
        return AxQuestBoardAPI.giveQuestPoints(playerUUID, (int) amount);
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID playerUUID, double amount) {
        return AxQuestBoardAPI.takeQuestPoints(playerUUID, (int) amount);
    }
}
