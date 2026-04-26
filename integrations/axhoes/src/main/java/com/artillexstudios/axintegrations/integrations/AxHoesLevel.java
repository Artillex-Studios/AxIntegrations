package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axhoes.api.AxHoesAPI;
import com.artillexstudios.axhoes.hooks.xp.XPHook;
import com.artillexstudios.axintegrations.types.LevelIntegration;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AxHoesLevel extends LevelIntegration {
    private XPHook api;

    public AxHoesLevel() {
        super("AxHoes");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.artillexstudios.axhoes.api.AxHoesAPI");
    }

    @Override
    public boolean setup() {
        api = AxHoesAPI.getPlayerXPHook();
        if (api == null) return false;
        return true;
    }

    @Override
    public long getLevel(@NotNull UUID playerUUID) {
        return api.getLevel(playerUUID);
    }

    @Override
    public double getXP(@NotNull UUID playerUUID) {
        return api.getLevel(playerUUID);
    }

    @Override
    public double getRequiredXP(@NotNull UUID playerUUID) {
        return api.getRequiredXP(playerUUID);
    }

    @Override
    public double getRemainingXP(@NotNull UUID playerUUID) {
        return api.getRemainingXP(playerUUID);
    }

    @Override
    public CompletableFuture<Boolean> setLevel(@NotNull UUID playerUUID, long amount) {
        return api.setLevel(playerUUID, amount);
    }

    @Override
    public CompletableFuture<Boolean> setXP(@NotNull UUID playerUUID, double amount) {
        return api.setXP(playerUUID, amount);
    }
}
