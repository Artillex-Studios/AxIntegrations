package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.BankIntegration;
import com.artillexstudios.axintegrations.utils.NumberTool;
import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.enums.BankAction;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.island.bank.BankTransaction;
import com.bgsoftware.superiorskyblock.api.island.bank.IslandBank;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SuperiorSkyBlock2Bank extends BankIntegration {

    public SuperiorSkyBlock2Bank() {
        super("SuperiorSkyBlock2");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI");
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
    public CompletableFuture<Number> getBalance(@NotNull UUID playerUUID) {
        IslandBank bank = getIslandBank(playerUUID);
        if (bank == null) return CompletableFuture.completedFuture(0);
        return CompletableFuture.completedFuture(bank.getBalance());
    }

    @Override
    public CompletableFuture<Boolean> deposit(@NotNull UUID playerUUID, @NotNull Number amount) {
        IslandBank bank = getIslandBank(playerUUID);
        if (bank == null) return CompletableFuture.completedFuture(false);
        BigDecimal value = NumberTool.toBigDecimal(amount);
        if (bank.canDepositMoney(value)) {
            BankTransaction transaction = bank.depositAdminMoney(Bukkit.getConsoleSender(), value);
            return CompletableFuture.completedFuture(transaction.getAction() == BankAction.DEPOSIT_COMPLETED);
        }
        return CompletableFuture.completedFuture(false);
    }

    @Override
    public CompletableFuture<Boolean> withdraw(@NotNull UUID playerUUID, @NotNull Number amount) {
        IslandBank bank = getIslandBank(playerUUID);
        if (bank == null) return CompletableFuture.completedFuture(false);
        BigDecimal value = NumberTool.toBigDecimal(amount);
        BankTransaction transaction = bank.withdrawAdminMoney(Bukkit.getConsoleSender(), value);
        return CompletableFuture.completedFuture(transaction.getAction() == BankAction.WITHDRAW_COMPLETED);
    }

    @Nullable
    private IslandBank getIslandBank(UUID playerUUID) {
        SuperiorPlayer superiorPlayer = SuperiorSkyblockAPI.getPlayer(playerUUID);
        if (superiorPlayer == null) return null;
        Island island = superiorPlayer.getIsland();
        if (island == null) return null;
        IslandBank bank = island.getIslandBank();
        if (bank == null) return null;
        return bank;
    }
}
