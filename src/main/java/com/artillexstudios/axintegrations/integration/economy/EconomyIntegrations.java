package com.artillexstudios.axintegrations.integration.economy;

import com.artillexstudios.axintegrations.integration.economy.implementation.VaultEconomyIntegration;
import com.artillexstudios.axintegrations.registry.IntegrationRegistry;

public final class EconomyIntegrations {
    private static final IntegrationRegistry<EconomyIntegration> registry = new IntegrationRegistry<>();

    public static void init() {
        register(new VaultEconomyIntegration());
    }

    public static void register(EconomyIntegration integration) {
        if (!integration.loaded()) {
            return;
        }

        registry.register(integration);
    }
}
