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
    public Number getBalance(Player player) {
        throw new RuntimeException("Feature not supported"); // todo
    }

    @Override
    public boolean giveBalance(Player player, Number amount) {
        throw new RuntimeException("Feature not supported");
    }

    @Override
    public boolean takeBalance(Player player, Number amount) {
        throw new RuntimeException("Feature not supported");
    }

    @NotNull
    @Override
    public CompletableFuture<Number> getBalanceAsync(UUID playerUUID) {
        CompletableFuture<Number> cf = new CompletableFuture<>();
        AxQuestBoardAPI.getQuestPoints(playerUUID).thenAccept(cf::complete);
        return cf;
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalanceAsync(UUID playerUUID, Number amount) {
        return AxQuestBoardAPI.giveQuestPoints(playerUUID, amount.intValue());
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalanceAsync(UUID playerUUID, Number amount) {
        return AxQuestBoardAPI.takeQuestPoints(playerUUID, amount.intValue());
    }
}
