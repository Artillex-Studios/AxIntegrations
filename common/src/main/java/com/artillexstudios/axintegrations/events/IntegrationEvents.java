package com.artillexstudios.axintegrations.events;

import com.artillexstudios.axintegrations.events.impl.IntegrationEvent;
import com.artillexstudios.axintegrations.events.impl.VanishEvent;

import java.util.ArrayList;
import java.util.List;

public class IntegrationEvents {
    private final static List<IntegrationAdapter> adapters = new ArrayList<>();

    public static void register(IntegrationAdapter adapter) {
        adapters.add(adapter);
    }

    public static void unregister(IntegrationAdapter adapter) {
        adapters.remove(adapter);
    }

    public static List<IntegrationAdapter> getAdapters() {
        return adapters;
    }

    public static void unregisterAll() {
        adapters.clear();
    }

    public static void callEvent(IntegrationEvent integrationEvent) {
        for (IntegrationAdapter adapter : adapters) {
            switch (integrationEvent) {
                case VanishEvent event -> adapter.onVanish(event);
                default -> {}
            }
        }
    }
}
