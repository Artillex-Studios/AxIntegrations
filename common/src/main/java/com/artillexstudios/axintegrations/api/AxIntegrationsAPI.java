package com.artillexstudios.axintegrations.api;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.IntegrationType;

import java.util.List;
import java.util.Map;

public class AxIntegrationsAPI {

    /**
     * provides a new integration
     * this method doesn't automatically enable the integration, it is the same as adding a new integration class to axintegrations directly
     * recommended use case: for public plugin developers who want their plugin to be an option in ax plugins without forcing the integration, so the user can pick it
     *
     * @throws com.artillexstudios.axintegrations.exceptions.IntegrationsLockedException if the method is executed after the {@link com.artillexstudios.axintegrations.api.events.AxIntegrationsLoadEvent}
     */
    public static <T extends Integration> void provideIntegration(Class<T> integration) {
        IntegrationManager.provideIntegration(integration);
    }

    /**
     * registers a new integration
     * this method forcefully enables this integration without checking if the user has enabled it
     * recommended use case: for private servers and developers who need their plugin to work with ax plugins without having to set up anything
     * warning: this option can cause issues if the plugin only expects 1 active integration, but because of this multiple can get enabled
     * generally it is recommended to use {@link this#provideIntegration(Class)} instead
     *
     * @throws com.artillexstudios.axintegrations.exceptions.IntegrationsLockedException if the method is executed after the {@link com.artillexstudios.axintegrations.api.events.AxIntegrationsLoadEvent}
     */
    public static <T extends Integration> void registerIntegration(T integration) {
        IntegrationManager.registerIntegration(integration);
    }

    /**
     * returns a Map of all integration names and formatted names
     */
    public static Map<String, String> listAvailableIntegrations(IntegrationType type) {
        return IntegrationManager.listAvailableIntegrations(type);
    }

    /**
     * returns all loaded integrations by the class
     */
    public static <T extends Integration> List<T> getIntegrations(Class<T> clazz) {
        return IntegrationManager.getIntegrations(clazz);
    }
}
