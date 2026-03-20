package com.artillexstudios.axintegrations;

import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import com.artillexstudios.axintegrations.types.ProtectionIntegration;
import com.artillexstudios.axintegrations.types.ShopIntegration;

public enum IntegrationType {
    CURRENCY(CurrencyIntegration.class),
    PROTECTION(ProtectionIntegration.class),
    SHOP(ShopIntegration.class);

    private final Class<? extends Integration> clazz;

    IntegrationType(Class<? extends Integration> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Integration> getClazz() {
        return clazz;
    }
}