package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axgens.AxGens;
import com.artillexstudios.axgens.api.AxGensAPI;
import com.artillexstudios.axgens.user.AxGensUser;
import com.artillexstudios.axgens.user.Users;
import com.artillexstudios.axintegrations.types.LevelIntegration;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AxGensLevel extends LevelIntegration {

    public AxGensLevel() {
        super("AxGens");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.artillexstudios.axgens.api.AxGensAPI");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public long getLevel(@NotNull UUID playerUUID) {
        return AxGensAPI.getLevel(playerUUID);
    }

    @Override
    public double getXP(@NotNull UUID playerUUID) {
        return 0;
    }

    @Override
    public double getRequiredXP(@NotNull UUID playerUUID) {
        return 0;
    }

    @Override
    public double getRemainingXP(@NotNull UUID playerUUID) {
        return 0;
    }

    @Override
    public CompletableFuture<Boolean> setLevel(@NotNull UUID playerUUID, long amount) {
        AxGensUser user = Users.getUser(playerUUID);
        if (user == null) CompletableFuture.completedFuture(false);
        user.setLevel(amount);
        AxGens.getThreadedQueue().submit(() -> {
            AxGens.getDatabase().updateUser(playerUUID, amount);
        });
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CompletableFuture<Boolean> setXP(@NotNull UUID playerUUID, double amount) {
        return CompletableFuture.completedFuture(true);
    }
}
