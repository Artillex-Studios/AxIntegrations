package com.artillexstudios.axintegrations;

import com.artillexstudios.axintegrations.functions.CurrencySetupFunction;
import com.artillexstudios.axintegrations.functions.EnableFunction;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class IntegrationSetup {
    protected JavaPlugin javaPlugin;
    protected final List<IntegrationType> enabledTypes = new ArrayList<>();
    protected CurrencySetupFunction currencySetupFunction;
    protected EnableFunction currencyEnableFunction;
    protected EnableFunction protectionEnableFunction;
    protected EnableFunction shopEnableFunction;

    protected IntegrationSetup() {}

    public static IntegrationSetup builder() {
        IntegrationSetup setup = new IntegrationSetup();
        setup.javaPlugin = JavaPlugin.getProvidingPlugin(setup.getClass());
        return setup;
    }

    public IntegrationSetup enableCurrencyIntegrations(EnableFunction enableFunction, CurrencySetupFunction currencySetupFunction) {
        enabledTypes.add(IntegrationType.CURRENCY);
        this.currencyEnableFunction = enableFunction;
        this.currencySetupFunction = currencySetupFunction;
        return this;
    }

    public IntegrationSetup enableProtectionIntegrations(EnableFunction enableFunction) {
        enabledTypes.add(IntegrationType.PROTECTION);
        this.protectionEnableFunction = enableFunction;
        return this;
    }

    public IntegrationSetup enableShopIntegrations(EnableFunction enableFunction) {
        enabledTypes.add(IntegrationType.SHOP);
        this.shopEnableFunction = enableFunction;
        return this;
    }

    public void setup() {
        IntegrationManager.setup(this);
    }
}
