package com.artillexstudios.axintegrations.integration.economy;

import com.artillexstudios.axintegrations.integration.economy.implementation.VaultEconomyIntegration;
import com.artillexstudios.axintegrations.registry.IntegrationRegistry;

import java.util.Collection;
import java.util.function.Supplier;

public final class EconomyIntegrations {
    private static final IntegrationRegistry<EconomyIntegration> registry = new IntegrationRegistry<>();

    public static void init() {
        register(VaultEconomyIntegration::new);
    }

    public static Collection<EconomyIntegration> values() {
        return registry.values();
    }

    public static void register(Supplier<EconomyIntegration> integration) {
        EconomyIntegration instance;
        try {
            instance = integration.get();
        } catch (Throwable throwable) {
            return;
        }

        if (!instance.canRegister()) {
            return;
        }

        registry.register(instance);
        instance.register();
    }
}
