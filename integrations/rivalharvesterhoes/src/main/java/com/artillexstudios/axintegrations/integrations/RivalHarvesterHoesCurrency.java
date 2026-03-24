package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import me.rivaldev.harvesterhoes.Main;
import me.rivaldev.harvesterhoes.ecomanager.EconomyManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class RivalHarvesterHoesCurrency extends CurrencyIntegration {
    private Main main;
    private EconomyManager manager;

    public RivalHarvesterHoesCurrency() {
        super("RivalHarvesterHoes", null);
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("me.rivaldev.harvesterhoes.Main");
    }

    @Override
    public boolean setup() {
        main = Main.instance;
        if (main == null) return false;
        manager = main.getEconomy();
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

    @Override
    public double getBalance(@NotNull Player player) {
        return manager.getEconomyAmount(player);
    }

    @NotNull
    @Override
    public CompletableFuture<Double> getBalance(@NotNull UUID playerUUID) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        return CompletableFuture.completedFuture(manager.getEconomyAmount(offlinePlayer));
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> giveBalance(@NotNull UUID playerUUID, double amount) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        manager.giveEconomyAmount(offlinePlayer, amount);
        return CompletableFuture.completedFuture(true);
    }

    @NotNull
    @Override
    public CompletableFuture<Boolean> takeBalance(@NotNull UUID playerUUID, double amount) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
        manager.removeEconomyAmount(offlinePlayer, amount);
        return CompletableFuture.completedFuture(true);
    }
}
