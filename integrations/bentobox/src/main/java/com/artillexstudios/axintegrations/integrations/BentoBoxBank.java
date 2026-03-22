package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.BankIntegration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import world.bentobox.bank.Bank;
import world.bentobox.bank.BankManager;
import world.bentobox.bank.BankResponse;
import world.bentobox.bank.data.Money;
import world.bentobox.bank.data.TxType;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.user.User;
import world.bentobox.bentobox.database.objects.Island;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class BentoBoxBank extends BankIntegration {
    private BentoBox api;
    private Bank bank;
    private BankManager manager;

    public BentoBoxBank() {
        super("BentoBox");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("world.bentobox.bank.Bank");
    }

    @Override
    public boolean setup() {
        api = BentoBox.getInstance();
        if (api == null) return false;
        bank = (Bank) BentoBox.getInstance().getAddonsManager().getAddonByMainClassName("world.bentobox.bank.Bank").orElse(null);
        if (bank == null) return false;
        manager = bank.getBankManager();
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
    public CompletableFuture<Number> getBalance(@NotNull UUID playerUUID) {
        Island island = getIsland(playerUUID);
        if (island == null) return CompletableFuture.completedFuture(0);
        return CompletableFuture.completedFuture(manager.getBalance(island).getValue());
    }

    @Override
    public CompletableFuture<Boolean> deposit(@NotNull UUID playerUUID, @NotNull Number amount) {
        Island island = getIsland(playerUUID);
        if (island == null) return CompletableFuture.completedFuture(false);
        CompletableFuture<Boolean> cf = new CompletableFuture<>();
        manager.deposit(User.getInstance(playerUUID), island, new Money(toBigDecimal(amount)), TxType.DEPOSIT).thenAccept(response -> {
            cf.complete(response == BankResponse.SUCCESS);
        });
        return cf;
    }

    @Override
    public CompletableFuture<Boolean> withdraw(@NotNull UUID playerUUID, @NotNull Number amount) {
        Island island = getIsland(playerUUID);
        if (island == null) return CompletableFuture.completedFuture(false);
        CompletableFuture<Boolean> cf = new CompletableFuture<>();
        manager.withdraw(User.getInstance(playerUUID), island, new Money(toBigDecimal(amount)), TxType.WITHDRAW).thenAccept(response -> {
            cf.complete(response == BankResponse.SUCCESS);
        });
        return cf;
    }

    @Nullable
    private Island getIsland(UUID playerUUID) {
        for (Island island : api.getIslandsManager().getIslands()) {
            if (island.getMembers().containsKey(playerUUID)) {
                return island;
            }
        }
        return null;
    }

    private BigDecimal toBigDecimal(Number number) {
        if (number == null) return null;
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        } else if (number instanceof Long || number instanceof Integer) {
            return BigDecimal.valueOf(number.longValue());
        } else if (number instanceof Double || number instanceof Float) {
            return BigDecimal.valueOf(number.doubleValue());
        } else {
            return new BigDecimal(number.toString());
        }
    }
}
