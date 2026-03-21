package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.IntegrationType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Rules
 * -
 */
public abstract class CustomBlockIntegration extends Integration {

    /**
     * returns all loaded integrations
     */
    public static List<CustomBlockIntegration> list() {
        return IntegrationManager.getIntegrations(CustomBlockIntegration.class);
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#list()} instead
     */
    @Nullable
    public static CustomBlockIntegration one() {
        return list().stream().findFirst().orElse(null);
    }

    public CustomBlockIntegration(String name) {
        super(name, IntegrationType.CUSTOM_BLOCK);
    }
}
