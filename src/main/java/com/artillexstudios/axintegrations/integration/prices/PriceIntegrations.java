package com.artillexstudios.axintegrations.integration.prices;

import com.artillexstudios.axintegrations.integration.prices.implementation.AxGensPriceIntegration;
import com.artillexstudios.axintegrations.integration.prices.implementation.CMIPriceIntegration;
import com.artillexstudios.axintegrations.integration.prices.implementation.DynamicShop3PriceIntegration;
import com.artillexstudios.axintegrations.integration.prices.implementation.EconomyShopGUIPriceIntegration;
import com.artillexstudios.axintegrations.integration.prices.implementation.EssentialsPriceIntegration;
//import com.artillexstudios.axintegrations.integration.prices.implementation.ExcellentShopPriceIntegration;
import com.artillexstudios.axintegrations.integration.prices.implementation.ExcellentShopPriceIntegration;
import com.artillexstudios.axintegrations.integration.prices.implementation.ShopGUIPlusPriceIntegration;
import com.artillexstudios.axintegrations.integration.prices.implementation.ZShopPriceIntegration;
import com.artillexstudios.axintegrations.registry.IntegrationRegistry;

import java.util.Collection;
import java.util.function.Supplier;

public final class PriceIntegrations {
    private static final IntegrationRegistry<PriceIntegration> registry = new IntegrationRegistry<>();

    public static void init() {
        register(AxGensPriceIntegration::new);
        register(CMIPriceIntegration::new);
        register(DynamicShop3PriceIntegration::new);
        register(EconomyShopGUIPriceIntegration::new);
        register(EssentialsPriceIntegration::new);
        register(ExcellentShopPriceIntegration::new);
        register(ShopGUIPlusPriceIntegration::new);
        register(ZShopPriceIntegration::new);
    }

    public static Collection<PriceIntegration> values() {
        return registry.values();
    }

    public static void register(Supplier<PriceIntegration> integration) {
        PriceIntegration instance;
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
