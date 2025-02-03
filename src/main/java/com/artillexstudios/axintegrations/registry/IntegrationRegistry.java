package com.artillexstudios.axintegrations.registry;

import com.artillexstudios.axintegrations.integration.Integration;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class IntegrationRegistry<T extends Integration> {
    private final Map<String, T> integrations = new ConcurrentHashMap<>();

    public void register(T integration) {
        integrations.put(integration.id(), integration);
    }

    public Collection<T> values() {
        return this.integrations.values();
    }
}
