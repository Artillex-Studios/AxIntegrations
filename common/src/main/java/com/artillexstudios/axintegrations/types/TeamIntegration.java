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
public abstract class TeamIntegration extends Integration {

    /**
     * returns all loaded integrations
     */
    public static List<TeamIntegration> list() {
        return IntegrationManager.getIntegrations(TeamIntegration.class);
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#list()} instead
     */
    @Nullable
    public static TeamIntegration one() {
        return list().stream().findFirst().orElse(null);
    }

    public TeamIntegration(String name) {
        super(name, IntegrationType.TEAM);
    }
}
