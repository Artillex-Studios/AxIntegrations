package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import me.swanis.mobcoins.MobCoinsAPI;
import me.swanis.mobcoins.profile.Profile;
import me.swanis.mobcoins.profile.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SuperMobCoinsCurrency extends CurrencyIntegration {
    private ProfileManager manager;

    public SuperMobCoinsCurrency() {
        super("SuperMobCoins", null);
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("me.swanis.mobcoins.MobCoinsAPI");
    }

    @Override
    public boolean setup() {
        manager = MobCoinsAPI.getProfileManager();
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
        Profile profile = manager.getProfile(player);
        if (profile == null) return 0;
        return profile.getMobCoins();
    }

    @NotNull
    @Override
    public CompletableFuture<Number> getBalance(@NotNull UUID playerUUID) {
        Profile profile = manager.getProfile(playerUUID);
        if (profile == null) return CompletableFuture.completedFuture(0);
        return CompletableFuture.completedFuture(profile.getMobCoins());
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalance(@NotNull UUID playerUUID, @NotNull Number amount) {
        Profile profile = manager.getProfile(playerUUID);
        if (profile == null) return CompletableFuture.completedFuture(false);
        profile.setMobCoins(profile.getMobCoins() + amount.longValue());
        return CompletableFuture.completedFuture(true);
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID playerUUID, @NotNull Number amount) {
        Profile profile = manager.getProfile(playerUUID);
        if (profile == null) return CompletableFuture.completedFuture(false);
        profile.setMobCoins(profile.getMobCoins() - amount.longValue());
        return CompletableFuture.completedFuture(true);
    }
}
