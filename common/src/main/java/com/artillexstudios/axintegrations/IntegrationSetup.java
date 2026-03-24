package com.artillexstudios.axintegrations;

import com.artillexstudios.axapi.scheduler.Scheduler;
import com.artillexstudios.axintegrations.functions.CurrencySetupFunction;
import com.artillexstudios.axintegrations.functions.EnableFunction;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class IntegrationSetup {
    protected JavaPlugin javaPlugin;
    protected final List<IntegrationType> enabledTypes = new ArrayList<>();
    protected Map<IntegrationType, EnableFunction> enableFunctionMap = new HashMap<>();
    protected CurrencySetupFunction currencySetupFunction;

    protected IntegrationSetup() {}

    public static IntegrationSetup builder() {
        IntegrationSetup setup = new IntegrationSetup();
        setup.javaPlugin = JavaPlugin.getProvidingPlugin(setup.getClass());
        return setup;
    }

    public IntegrationSetup enableBackpackIntegrations(EnableFunction enableFunction) {
        enable(IntegrationType.BACKPACK, enableFunction);
        return this;
    }

    public IntegrationSetup enableBankIntegrations(EnableFunction enableFunction) {
        enable(IntegrationType.BANK, enableFunction);
        return this;
    }

    public IntegrationSetup enableCurrencyIntegrations(EnableFunction enableFunction, CurrencySetupFunction currencySetupFunction) {
        this.currencySetupFunction = currencySetupFunction;
        enable(IntegrationType.CURRENCY, enableFunction);
        return this;
    }

    public IntegrationSetup enableCustomBlockIntegrations(EnableFunction enableFunction) {
        enable(IntegrationType.CUSTOM_BLOCK, enableFunction);
        return this;
    }

    public IntegrationSetup enableLevelIntegrations(EnableFunction enableFunction) {
        enable(IntegrationType.LEVEL, enableFunction);
        return this;
    }

    public IntegrationSetup enableProtectionIntegrations(EnableFunction enableFunction) {
        enable(IntegrationType.PROTECTION, enableFunction);
        return this;
    }

    public IntegrationSetup enableShopIntegrations(EnableFunction enableFunction) {
        enable(IntegrationType.SHOP, enableFunction);
        return this;
    }

    public IntegrationSetup enableStackerIntegrations(EnableFunction enableFunction) {
        enable(IntegrationType.STACKER, enableFunction);
        return this;
    }

    public IntegrationSetup enableTeamIntegrations(EnableFunction enableFunction) {
        enable(IntegrationType.TEAM, enableFunction);
        return this;
    }

    public IntegrationSetup enableVanishIntegrations(EnableFunction enableFunction) {
        enable(IntegrationType.VANISH, enableFunction);
        return this;
    }

    private void enable(IntegrationType integrationType, EnableFunction enableFunction) {
        enabledTypes.add(integrationType);
        enableFunctionMap.put(integrationType, enableFunction);
    }

    public CompletableFuture<Void> setup() {
        CompletableFuture<Void> cf = new CompletableFuture<>();
        // run a tick later to make sure that all plugins have loaded
        Scheduler.get().runLater(() -> {
            IntegrationManager.setup(this);
            cf.complete(null);
        }, 1);
        return cf;
    }
}
