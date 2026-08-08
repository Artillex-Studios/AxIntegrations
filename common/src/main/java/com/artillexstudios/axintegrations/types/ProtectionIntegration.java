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

    /**
     * returns a loaded integration by name
     */
    @Nullable
    public static ProtectionIntegration one(String name) {
        return list().stream().filter(i -> {
            return i.getName().equalsIgnoreCase(name) || i.getFormattedName().equalsIgnoreCase(name);
        }).findFirst().orElse(null);
    }

    public enum Permission {
        PLACE,
        BREAK,
        INTERACT,
        OPEN_CONTAINER
    }

    public static boolean hasPermission(@NotNull Player player, @NotNull Location location, @NotNull Permission permission) {
        for (ProtectionIntegration protectionIntegration : list()) {
            switch (permission) {
                case PLACE -> {
                    if (!protectionIntegration.canPlace(player, location)) return false;
                }
                case BREAK -> {
                    if (!protectionIntegration.canBreak(player, location)) return false;
                }
                case INTERACT -> {
                    if (!protectionIntegration.canInteract(player, location)) return false;
                }
                case OPEN_CONTAINER -> {
                    if (!protectionIntegration.canOpenContainer(player, location)) return false;
                }
            }
        }
        return true;
    }

    public ProtectionIntegration(String name) {
        super(name, IntegrationType.PROTECTION);
    }

    public abstract boolean canPlace(@NotNull Player player, @NotNull Location location);

    public abstract boolean canBreak(@NotNull Player player, @NotNull Location location);

    public abstract boolean canInteract(@NotNull Player player, @NotNull Location location);

    public abstract boolean canOpenContainer(@NotNull Player player, @NotNull Location location);
}
