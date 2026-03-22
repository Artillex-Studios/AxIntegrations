package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.BankIntegration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kingdoms.constants.group.Kingdom;
import org.kingdoms.constants.player.KingdomPlayer;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class KingdomsXBank extends BankIntegration {

    public KingdomsXBank() {
        super("KingdomsX");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("org.kingdoms.constants.group.Kingdom");
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
        Kingdom kingdom = getKingdom(playerUUID);
        if (kingdom == null) return CompletableFuture.completedFuture(0);
        return CompletableFuture.completedFuture(kingdom.getBank().get());
    }

    @Override
    public CompletableFuture<Boolean> deposit(@NotNull UUID playerUUID, @NotNull Number amount) {
        Kingdom kingdom = getKingdom(playerUUID);
        if (kingdom == null) return CompletableFuture.completedFuture(false);
        kingdom.getBank().add(amount);
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CompletableFuture<Boolean> withdraw(@NotNull UUID playerUUID, @NotNull Number amount) {
        Kingdom kingdom = getKingdom(playerUUID);
        if (kingdom == null) return CompletableFuture.completedFuture(false);
        kingdom.getBank().subtract(amount);
        return CompletableFuture.completedFuture(true);
    }

    @Nullable
    private Kingdom getKingdom(UUID playerUUID) {
        KingdomPlayer kingdomPlayer = KingdomPlayer.getKingdomPlayer(playerUUID);
        if (kingdomPlayer == null) return null;
        Kingdom kingdom = kingdomPlayer.getKingdom();
        if (kingdom == null) return null;
        return kingdom;
    }
}
