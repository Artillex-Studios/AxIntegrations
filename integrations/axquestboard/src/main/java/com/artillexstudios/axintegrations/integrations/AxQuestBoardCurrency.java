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

    @NotNull
    @Override
    public Number getBalance(@NotNull Player player) {
        return AxQuestBoardAPI.getPoints(player.getUniqueId()); // todo: add better method
    }

    @NotNull
    @Override
    public CompletableFuture<Number> getBalance(@NotNull UUID playerUUID) {
        CompletableFuture<Number> cf = new CompletableFuture<>();
        AxQuestBoardAPI.getQuestPoints(playerUUID).thenAccept(cf::complete);
        return cf;
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalance(@NotNull UUID playerUUID, @NotNull Number amount) {
        return AxQuestBoardAPI.giveQuestPoints(playerUUID, amount.intValue());
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID playerUUID, @NotNull Number amount) {
        return AxQuestBoardAPI.takeQuestPoints(playerUUID, amount.intValue());
    }
}
