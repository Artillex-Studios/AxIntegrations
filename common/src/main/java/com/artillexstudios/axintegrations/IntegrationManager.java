package com.artillexstudios.axintegrations;

import com.artillexstudios.axapi.utils.StringUtils;
import com.artillexstudios.axintegrations.functions.EnableFunction;
import com.artillexstudios.axintegrations.types.CurrencyIntegration;
import com.artillexstudios.axintegrations.types.ProtectionIntegration;
import com.artillexstudios.axintegrations.types.ShopIntegration;
import com.artillexstudios.axintegrations.utils.PackageScanner;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class IntegrationManager {
    private static final Map<IntegrationType, List<? extends Integration>> integrations = new HashMap<>();
    private static IntegrationSetup setup;

    /**
     * used to set the types of integrations that should be loaded
     */
    protected static void setup(IntegrationSetup setup) {
        IntegrationManager.setup = setup;
        reload();
    }

    public static void reload() {
        disable();
        for (IntegrationType enabledType : setup.enabledTypes) {
            switch (enabledType) {
                case CURRENCY -> reloadCurrencyIntegrations(setup.currencyEnableFunction);
                case PROTECTION -> reloadProtectionIntegrations(setup.protectionEnableFunction);
                case SHOP -> reloadShopIntegrations(setup.shopEnableFunction);
            }
        }
        printLoaded();
    }

    public static List<IntegrationType> getEnabledTypes() {
        return setup.enabledTypes;
    }

    private static void reloadCurrencyIntegrations(EnableFunction function) {
        for (Class<? extends Integration> clazz : PackageScanner.scan(IntegrationType.CURRENCY)) {
            try {
                Constructor<Integration> constructor = (Constructor<Integration>) clazz.getDeclaredConstructor(String.class);
                Integration instance = constructor.newInstance((Object) null);
                List<String> currencies = setup.currencySetupFunction.getCurrencyList(instance.getName());
                if (currencies == null) currencies = new ArrayList<>();
                if (currencies.isEmpty()) currencies.add(null);
                for (String currency : currencies) {
                    loadIntegration(constructor.newInstance(currency), function);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void reloadProtectionIntegrations(EnableFunction function) {
        for (Class<? extends Integration> clazz : PackageScanner.scan(IntegrationType.PROTECTION)) {
            try {
                Constructor<Integration> constructor = (Constructor<Integration>) clazz.getDeclaredConstructor();
                loadIntegration(constructor.newInstance(), function);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void reloadShopIntegrations(EnableFunction function) {
        for (Class<? extends Integration> clazz : PackageScanner.scan(IntegrationType.SHOP)) {
            try {
                Constructor<Integration> constructor = (Constructor<Integration>) clazz.getDeclaredConstructor();
                loadIntegration(constructor.newInstance(), function);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * returns all loaded integrations
     */
    public static List<CurrencyIntegration> getCurrencyIntegrations() {
        return Collections.unmodifiableList((List<CurrencyIntegration>) integrations.get(IntegrationType.CURRENCY));
    }

    /**
     * returns all loaded integrations
     */
    public static List<ProtectionIntegration> getProtectionIntegrations() {
        return Collections.unmodifiableList((List<ProtectionIntegration>) integrations.get(IntegrationType.PROTECTION));
    }

    /**
     * returns all loaded integrations
     */
    public static List<ShopIntegration> getShopIntegrations() {
        return Collections.unmodifiableList((List<ShopIntegration>) integrations.get(IntegrationType.SHOP));
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#getCurrencyIntegrations()} ()} instead
     */
    @Nullable
    public static CurrencyIntegration getCurrencyIntegration() {
        return (CurrencyIntegration) integrations.get(IntegrationType.CURRENCY).getFirst();
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#getProtectionIntegrations()} ()} instead
     */
    @Nullable
    public static ProtectionIntegration getProtectionIntegration() {
        return (ProtectionIntegration) integrations.get(IntegrationType.PROTECTION).getFirst();
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#getShopIntegrations()} instead
     */
    @Nullable
    public static ShopIntegration getShopIntegration() {
        return (ShopIntegration) integrations.get(IntegrationType.SHOP).getFirst();
    }

    /**
     * returns all loaded integrations by the class
     */
    public static <T extends Integration> List<T> getIntegrations(Class<T> clazz) {
        for (IntegrationType value : IntegrationType.values()) {
            if (!value.getClazz().equals(clazz)) continue;
            return Collections.unmodifiableList((List<T>) integrations.getOrDefault(value, new ArrayList<>()));
        }
        throw new RuntimeException("Invalid class");
    }

    private static <T extends Integration> void loadIntegration(T integration, EnableFunction function) {
        if (!integration.canLoad()) return;
        if (!function.isEnabled(integration.getName())) return;
        if (!integration.setup()) return;
        List<T> list = (List<T>) integrations.computeIfAbsent(integration.getType(), type -> new ArrayList<>());
        list.add(integration);
    }

    public static void disable() {
        for (List<? extends Integration> list : integrations.values()) {
            for (Iterator<? extends Integration> it = list.iterator(); it.hasNext(); ) {
                Integration integration = it.next();
                integration.disable();
                // don't remove third party hooks as it is controlled externally
                if (!integration.isBuiltin()) continue;
                it.remove();
            }
        }
        integrations.clear();
    }

    private static void printLoaded() {
        for (IntegrationType type : IntegrationManager.getEnabledTypes()) {
            List<? extends Integration> integrations = IntegrationManager.getIntegrations(type.getClazz());
            print(true, "Loaded %s integrations: %s".formatted(
                    type.name().toLowerCase(Locale.ENGLISH),
                    integrations.isEmpty() ? "---" : integrations
                            .stream()
                            .map(Integration::getFormattedName)
                            .collect(Collectors.joining(", "))
            ));
        }
    }

    public static void print(boolean success, String msg) {
        Bukkit.getConsoleSender().sendMessage(StringUtils.formatToString(
                "%s[%s] %s".formatted(
                        success ? "&#00FF00" : "&#FF0000",
                        setup.javaPlugin.getName(),
                        msg
                )
        ));
    }
}
