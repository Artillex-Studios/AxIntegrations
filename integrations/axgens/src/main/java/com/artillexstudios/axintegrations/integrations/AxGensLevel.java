package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axgens.AxGens;
import com.artillexstudios.axgens.api.AxGensAPI;
import com.artillexstudios.axgens.user.AxGensUser;
import com.artillexstudios.axgens.user.Users;
import com.artillexstudios.axintegrations.types.LevelIntegration;
import com.artillexstudios.axintegrations.types.ShopIntegration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.artillexstudios.axgens.AxGens.MESSAGEUTILS;

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
    public long getLevel(@NotNull UUID player) {
        return AxGensAPI.getLevel(player);
    }

    @Override
    public double getXP(@NotNull UUID player) {
        return 0;
    }

    @Override
    public double getRequiredXP(@NotNull UUID player) {
        return 0;
    }

    @Override
    public double getRemainingXP(@NotNull UUID player) {
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
