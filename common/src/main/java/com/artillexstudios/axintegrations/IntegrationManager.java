package com.artillexstudios.axintegrations;

import com.artillexstudios.axapi.utils.StringUtils;
import com.artillexstudios.axintegrations.api.events.AxIntegrationsReloadEvent;
import com.artillexstudios.axintegrations.exceptions.IntegrationsLockedException;
import com.artillexstudios.axintegrations.functions.EnableFunction;
import com.artillexstudios.axintegrations.utils.PackageScanner;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

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
    private static JavaPlugin plugin;
    private static final Map<IntegrationType, List<? extends Integration>> registeredIntegrations = new HashMap<>();
    private static IntegrationSetup setup;
    // can new integrations be registered?
    private static boolean locked = false;
    private static final Map<IntegrationType, List<Class<? extends Integration>>> providedIntegrations = new HashMap<>();

    public static <T extends Integration> void provideIntegration(Class<T> integration) {
        if (locked) throw new IntegrationsLockedException("The integration manager is locked. Please register your integrations by listening to AxIntegrationsLoadEvent.");

        IntegrationType type = null;
        for (IntegrationType integrationType : IntegrationType.values()) {
            if (!integrationType.getClazz().isAssignableFrom(integration)) continue;
            type = integrationType;
            break;
        }
        if (type == null) {
            throw new RuntimeException("Invalid integration type!");
        }

        List<Class<? extends Integration>> list = providedIntegrations.computeIfAbsent(type, i -> new ArrayList<>());
        list.add(integration);
    }

    public static <T extends Integration> void registerIntegration(T integration) {
        if (locked) throw new IntegrationsLockedException("The integration manager is locked. Please register your integrations by listening to AxIntegrationsLoadEvent.");
        List<T> list = (List<T>) registeredIntegrations.computeIfAbsent(integration.getType(), type -> new ArrayList<>());
        list.add(integration);
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static void setPlugin(JavaPlugin plugin) {
        IntegrationManager.plugin = plugin;
    }

    /**
     * used to set the types of integrations that should be loaded
     */
    protected static void setup(IntegrationSetup setup) {
        IntegrationManager.setup = setup;
        IntegrationManager.plugin = setup.javaPlugin;
        IntegrationManager.locked = true;
        reload(true);
    }


    public static void reload() {
        reload(false);
    }

    private static void reload(boolean startup) {
        if (!startup) {
            disable();
            locked = false;
            AxIntegrationsReloadEvent event = new AxIntegrationsReloadEvent();
            Bukkit.getPluginManager().callEvent(event);
            locked = true;
        }
        for (IntegrationType enabledType : setup.enabledTypes) {
            EnableFunction enableFunction = setup.enableFunctionMap.get(enabledType);
            switch (enabledType) {
                case CURRENCY -> reloadCurrencyIntegrations(enableFunction);
                case BACKPACK, BANK, CUSTOM_BLOCK, LEVEL, PROTECTION, SHOP, STACKER, TEAM, VANISH -> reloadGenericIntegration(enabledType, enableFunction);
            }
        }
        printLoaded();
    }

    public static List<IntegrationType> getEnabledTypes() {
        return setup.enabledTypes;
    }

    private static List<Class<? extends Integration>> fetchIntegrations(IntegrationType type) {
        List<Class<? extends Integration>> list = PackageScanner.scan(type);
        list.addAll(providedIntegrations.getOrDefault(type, List.of()));
        return list;
    }

    private static void reloadCurrencyIntegrations(EnableFunction function) {
        for (Class<? extends Integration> clazz : fetchIntegrations(IntegrationType.CURRENCY)) {
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
                try {
                    Constructor<Integration> constructor = (Constructor<Integration>) clazz.getDeclaredConstructor();
                    loadIntegration(constructor.newInstance(), function);
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }

    private static void reloadGenericIntegration(IntegrationType type, EnableFunction function) {
        for (Class<? extends Integration> clazz : fetchIntegrations(type)) {
            try {
                Constructor<Integration> constructor = (Constructor<Integration>) clazz.getDeclaredConstructor();
                loadIntegration(constructor.newInstance(), function);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Map<String, String> listAvailableIntegrations(IntegrationType type) {
        Map<String, String> list = new HashMap<>();
        for (Class<? extends Integration> clazz : fetchIntegrations(type)) {
            try {
                Constructor<Integration> constructor = (Constructor<Integration>) clazz.getDeclaredConstructors()[0];
                Class<?>[] paramTypes = constructor.getParameterTypes();
                Object[] argsArray = new Object[paramTypes.length];
                if (argsArray.length > 0) {
                    switch (type) { // apply placeholder if integration requires
                        case CURRENCY -> argsArray[0] = "<currency>";
                    }
                }
                Integration integration = constructor.newInstance(argsArray);
                list.put(integration.getName(), integration.getFormattedName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    public static <T extends Integration> List<T> getIntegrations(Class<T> clazz) {
        for (IntegrationType value : IntegrationType.values()) {
            if (!value.getClazz().equals(clazz)) continue;
            return Collections.unmodifiableList((List<T>) registeredIntegrations.getOrDefault(value, new ArrayList<>()));
        }
        throw new RuntimeException("Invalid class");
    }

    private static <T extends Integration> void loadIntegration(T integration, EnableFunction function) {
        try {
            if (!integration.canLoad()) return;
            if (!function.isEnabled(integration.getName())) return;
            if (!integration.setup()) return;
            List<T> list = (List<T>) registeredIntegrations.computeIfAbsent(integration.getType(), type -> new ArrayList<>());
            list.add(integration);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void disable() {
        for (List<? extends Integration> list : registeredIntegrations.values()) {
            for (Iterator<? extends Integration> it = list.iterator(); it.hasNext(); ) {
                Integration integration = it.next();
                integration.disable();
                // don't remove third party hooks as they are controlled externally
                if (!integration.isBuiltin()) continue;
                it.remove();
            }
        }
        registeredIntegrations.clear();
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
