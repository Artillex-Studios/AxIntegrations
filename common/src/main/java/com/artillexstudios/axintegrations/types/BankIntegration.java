package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.IntegrationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Rules
 * - if the balance can't be retrieved, return 0
 * - if {@link this#worksOffline()} is false but an offline player is queried or not found, return false or 0 with {@link CompletableFuture}
 */
public abstract class BankIntegration extends Integration {

    /**
     * returns all loaded integrations
     */
    public static List<BankIntegration> list() {
        return IntegrationManager.getIntegrations(BankIntegration.class);
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#list()} instead
     */
    @Nullable
    public static BankIntegration one() {
        return list().stream().findFirst().orElse(null);
    }

    public BankIntegration(String name) {
        super(name, IntegrationType.BANK);
    }

    public abstract boolean worksOffline();

    @NotNull
    public abstract CompletableFuture<Number> getBalance(@NotNull UUID playerUUID);

    public abstract CompletableFuture<Boolean> deposit(@NotNull UUID playerUUID, @NotNull Number amount);

    public abstract CompletableFuture<Boolean> withdraw(@NotNull UUID playerUUID, @NotNull Number amount);
}
