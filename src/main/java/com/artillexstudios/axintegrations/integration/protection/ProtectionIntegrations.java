package com.artillexstudios.axintegrations.integration.protection;

import com.artillexstudios.axintegrations.integration.protection.implementation.BentoBoxProtectionIntegration;
import com.artillexstudios.axintegrations.integration.protection.implementation.GriefPreventionProtectionIntegration;
import com.artillexstudios.axintegrations.integration.protection.implementation.HuskClaimsProtectionIntegration;
import com.artillexstudios.axintegrations.integration.protection.implementation.HuskTownsProtectionIntegration;
import com.artillexstudios.axintegrations.integration.protection.implementation.IridiumSkyBlockProtectionIntegration;
import com.artillexstudios.axintegrations.integration.protection.implementation.KingdomsXProtectionIntegration;
import com.artillexstudios.axintegrations.integration.protection.implementation.LandsProtectionIntegration;
import com.artillexstudios.axintegrations.integration.protection.implementation.LocketteProProtectionIntegration;
import com.artillexstudios.axintegrations.integration.protection.implementation.PlotSquaredProtectionIntegration;
import com.artillexstudios.axintegrations.integration.protection.implementation.ResidenceProtectionIntegration;
import com.artillexstudios.axintegrations.integration.protection.implementation.SaberFactionsProtectionIntegration;
import com.artillexstudios.axintegrations.integration.protection.implementation.SuperiorSkyBlock2ProtectionIntegration;
import com.artillexstudios.axintegrations.integration.protection.implementation.TownyProtectionIntegration;
import com.artillexstudios.axintegrations.integration.protection.implementation.WorldGuardProtectionIntegration;
import com.artillexstudios.axintegrations.registry.IntegrationRegistry;

import java.util.Collection;
import java.util.function.Supplier;

public final class ProtectionIntegrations {
    private static final IntegrationRegistry<ProtectionIntegration> registry = new IntegrationRegistry<>();

    public static void init() {
        register(IridiumSkyBlockProtectionIntegration::new);
        register(KingdomsXProtectionIntegration::new);
        register(LocketteProProtectionIntegration::new);
        register(PlotSquaredProtectionIntegration::new);
        register(ResidenceProtectionIntegration::new);
        register(WorldGuardProtectionIntegration::new);
        register(SuperiorSkyBlock2ProtectionIntegration::new);
        register(GriefPreventionProtectionIntegration::new);
        register(HuskTownsProtectionIntegration::new);
        register(HuskClaimsProtectionIntegration::new);
        register(BentoBoxProtectionIntegration::new);
        register(LandsProtectionIntegration::new);
        register(TownyProtectionIntegration::new);
        register(SaberFactionsProtectionIntegration::new);
    }

    public static Collection<ProtectionIntegration> values() {
        return registry.values();
    }

    public static void register(Supplier<ProtectionIntegration> integration) {
        ProtectionIntegration instance;
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
