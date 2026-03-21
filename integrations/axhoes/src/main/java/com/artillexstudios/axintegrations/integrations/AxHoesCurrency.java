package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axhoes.api.AxHoesAPI;
import com.artillexstudios.axhoes.hooks.currency.CurrencyHook;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AxHoesCurrency extends CurrencyIntegration {
    private CurrencyHook api;

    public AxHoesCurrency() {
        super("AxHoes", null);
    }

    @Override
    public boolean canLoad() {

        return ClassUtils.INSTANCE.classExists("com.artillexstudios.axhoes.api.AxHoesAPI");
    }

    @Override
    public boolean setup() {
        api = AxHoesAPI.getEssenceHook();
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
    public Number getBalance(Player player) {
        return api.getBalance(player.getUniqueId());
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
        return CompletableFuture.completedFuture(api.getBalance(playerUUID));
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalanceAsync(UUID playerUUID, Number amount) {
        return api.giveBalance(playerUUID, amount.doubleValue());
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalanceAsync(UUID playerUUID, Number amount) {
        return api.takeBalance(playerUUID, amount.doubleValue());
    }
}
