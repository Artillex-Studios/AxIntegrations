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
public abstract class StackerIntegration extends Integration {

    /**
     * returns all loaded integrations
     */
    public static List<StackerIntegration> list() {
        return IntegrationManager.getIntegrations(StackerIntegration.class);
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#list()} instead
     */
    @Nullable
    public static StackerIntegration one() {
        return list().stream().findFirst().orElse(null);
    }

    public StackerIntegration(String name) {
        super(name, IntegrationType.STACKER);
    }
}
