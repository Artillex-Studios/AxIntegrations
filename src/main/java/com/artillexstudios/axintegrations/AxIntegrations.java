package com.artillexstudios.axintegrations;

import com.artillexstudios.axintegrations.integration.economy.EconomyIntegrations;
import com.artillexstudios.axintegrations.integration.prices.PriceIntegrations;
import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegrations;

public enum AxIntegrations {
    INSTANCE;

    public void init() {
        ProtectionIntegrations.init();
        EconomyIntegrations.init();
        PriceIntegrations.init();
    }
}
