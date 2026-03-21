package com.artillexstudios.axintegrations;

import com.artillexstudios.axintegrations.types.BackpackIntegration;
import com.artillexstudios.axintegrations.types.BankIntegration;
import com.artillexstudios.axintegrations.types.ContainerIntegration;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import com.artillexstudios.axintegrations.types.CustomBlockIntegration;
import com.artillexstudios.axintegrations.types.LevelIntegration;
import com.artillexstudios.axintegrations.types.ProtectionIntegration;
import com.artillexstudios.axintegrations.types.ShopIntegration;
import com.artillexstudios.axintegrations.types.StackerIntegration;
import com.artillexstudios.axintegrations.types.TeamIntegration;
import com.artillexstudios.axintegrations.types.VanishIntegration;

public enum IntegrationType {
    BACKPACK(BackpackIntegration.class),
    BANK(BankIntegration.class),
    CONTAINER(ContainerIntegration.class),
    CURRENCY(CurrencyIntegration.class),
    CUSTOM_BLOCK(CustomBlockIntegration.class),
    LEVEL(LevelIntegration.class),
    PROTECTION(ProtectionIntegration.class),
    SHOP(ShopIntegration.class),
    STACKER(StackerIntegration.class),
    TEAM(TeamIntegration.class),
    VANISH(VanishIntegration.class);

    private final Class<? extends Integration> clazz;

    IntegrationType(Class<? extends Integration> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Integration> getClazz() {
        return clazz;
    }
}