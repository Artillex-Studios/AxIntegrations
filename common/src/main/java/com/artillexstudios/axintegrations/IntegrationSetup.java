package com.artillexstudios.axintegrations;

import com.artillexstudios.axapi.scheduler.Scheduler;
import com.artillexstudios.axintegrations.api.events.AxIntegrationsLoadEvent;
import com.artillexstudios.axintegrations.functions.CurrencySetupFunction;
import com.artillexstudios.axintegrations.functions.EnableFunction;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegrationSetup {
    protected JavaPlugin javaPlugin;
    protected final List<IntegrationType> enabledTypes = new ArrayList<>();
    protected Map<IntegrationType, EnableFunction> enableFunctionMap = new HashMap<>();
    protected CurrencySetupFunction currencySetupFunction;
    protected Runnable runAfterLoad;
    protected Runnable runAfterSetup;

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

    /**
     * @param runAfterLoad called before the setup is finished, but after the {@link AxIntegrationsLoadEvent} is called, it's recommended to generate configuration or to list integrations at this point
     */
    public IntegrationSetup runAfterLoad(Runnable runAfterLoad) {
        this.runAfterLoad = runAfterLoad;
        return this;
    }

    /**
     * @param runAfterSetup called after the {@link AxIntegrationsLoadEvent} finishes, at this point everything is ready and functional
     */
    public IntegrationSetup runAfterSetup(Runnable runAfterSetup) {
        this.runAfterSetup = runAfterSetup;
        return this;
    }

    /**
     * finishes up the setup and locks the integration registry
     */
    public void setup() {
        // run a tick later to make sure that all plugins have loaded
        Scheduler.get().runLater(() -> {
            AxIntegrationsLoadEvent event = new AxIntegrationsLoadEvent();
            Bukkit.getPluginManager().callEvent(event);
            if (runAfterLoad != null) runAfterLoad.run();
            IntegrationManager.setup(this);
            if (runAfterSetup != null) runAfterSetup.run();
        }, 1);
    }
}
