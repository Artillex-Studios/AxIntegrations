package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.IntegrationType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Rules
 * - every method must return true if the player has bypass permission
 */
public abstract class ProtectionIntegration extends Integration {

    /**
     * returns all loaded integrations
     */
    public static List<ProtectionIntegration> list() {
        return IntegrationManager.getIntegrations(ProtectionIntegration.class);
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#list()} instead
     */
    @Nullable
    public static ProtectionIntegration one() {
        return list().stream().findFirst().orElse(null);
    }

    public ProtectionIntegration(String name) {
        super(name, IntegrationType.PROTECTION);
    }

    public abstract boolean canPlace(@NotNull Player player, @NotNull Location location);

    public abstract boolean canBreak(@NotNull Player player, @NotNull Location location);

    public abstract boolean canInteract(@NotNull Player player, @NotNull Location location);

    public abstract boolean canOpenContainer(@NotNull Player player, @NotNull Location location);
}
