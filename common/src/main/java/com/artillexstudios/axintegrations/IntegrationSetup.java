package com.artillexstudios.axintegrations;

import com.artillexstudios.axintegrations.functions.CurrencySetupFunction;
import com.artillexstudios.axintegrations.functions.EnableFunction;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class IntegrationSetup {
    protected JavaPlugin javaPlugin;
    protected final List<IntegrationType> enabledTypes = new ArrayList<>();
    protected EnableFunction backpackEnableFunction;
    protected EnableFunction bankEnableFunction;
    protected EnableFunction currencyEnableFunction;
    protected CurrencySetupFunction currencySetupFunction;
    protected EnableFunction customBlockEnableFunction;
    protected EnableFunction levelEnableFunction;
    protected EnableFunction protectionEnableFunction;
    protected EnableFunction shopEnableFunction;
    protected EnableFunction stackerEnableFunction;
    protected EnableFunction teamEnableFunction;
    protected EnableFunction vanishEnableFunction;

    protected IntegrationSetup() {}

    public static IntegrationSetup builder() {
        IntegrationSetup setup = new IntegrationSetup();
        setup.javaPlugin = JavaPlugin.getProvidingPlugin(setup.getClass());
        return setup;
    }

    public IntegrationSetup enableBackpackIntegrations(EnableFunction enableFunction) {
        enabledTypes.add(IntegrationType.BACKPACK);
        this.backpackEnableFunction = enableFunction;
        return this;
    }

    public IntegrationSetup enableBankIntegrations(EnableFunction enableFunction) {
        enabledTypes.add(IntegrationType.BANK);
        this.bankEnableFunction = enableFunction;
        return this;
    }

    public IntegrationSetup enableCurrencyIntegrations(EnableFunction enableFunction, CurrencySetupFunction currencySetupFunction) {
        enabledTypes.add(IntegrationType.CURRENCY);
        this.currencyEnableFunction = enableFunction;
        this.currencySetupFunction = currencySetupFunction;
        return this;
    }

    public IntegrationSetup enableCustomBlockIntegrations(EnableFunction enableFunction) {
        enabledTypes.add(IntegrationType.CUSTOM_BLOCK);
        this.customBlockEnableFunction = enableFunction;
        return this;
    }

    public IntegrationSetup enableLevelIntegrations(EnableFunction enableFunction) {
        enabledTypes.add(IntegrationType.LEVEL);
        this.levelEnableFunction = enableFunction;
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

    public IntegrationSetup enableStackerIntegrations(EnableFunction enableFunction) {
        enabledTypes.add(IntegrationType.STACKER);
        this.stackerEnableFunction = enableFunction;
        return this;
    }

    public IntegrationSetup enableTeamIntegrations(EnableFunction enableFunction) {
        enabledTypes.add(IntegrationType.TEAM);
        this.teamEnableFunction = enableFunction;
        return this;
    }

    public IntegrationSetup enableVanishIntegrations(EnableFunction enableFunction) {
        enabledTypes.add(IntegrationType.VANISH);
        this.vanishEnableFunction = enableFunction;
        return this;
    }

    public void setup() {
        IntegrationManager.setup(this);
    }
}
