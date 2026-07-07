package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.IntegrationType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Rules
 * - if the plugin can't answer {@link this#isVanished(Player)}, return false
 * - if the plugin can't answer {@link this#canVanish(Player)} (Player, Player)}, return false
 * - if the plugin can't answer {@link this#canSee(Player, Player)}, return true
 * - if the plugin can't answer {@link this#canPickup(Player)}, return true
 * - the {@link this#getVanishPriority(Player)} and {@link this#getViewPriority(Player)} must return a higher number for higher priority and it should return 0 if the player can't vanish
 * - if the plugin doesn't have vanish or view priorities, return 1 if {@link this#canVanish(Player)} is true otherwise 0
 * - if the plugin doesn't have a different priority for vanish and view, return the same priority for both
 */
public abstract class VanishIntegration extends Integration {

    /**
     * returns all loaded integrations
     */
    public static List<VanishIntegration> list() {
        return IntegrationManager.getIntegrations(VanishIntegration.class);
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#list()} instead
     */
    @Nullable
    public static VanishIntegration one() {
        return list().stream().findFirst().orElse(null);
    }

    public VanishIntegration(String name) {
        super(name, IntegrationType.VANISH);
    }

    public static boolean isPlayerVanished(@NotNull Player player) {
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        for (VanishIntegration integration : list()) {
            if (integration.isVanished(player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canPlayerVanish(@NotNull Player player) {
        for (VanishIntegration integration : list()) {
            if (integration.canVanish(player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canPlayerPickup(@NotNull Player player) {
        for (VanishIntegration integration : list()) {
            if (integration.canPickup(player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canPlayerSee(@NotNull Player viewer, @NotNull Player viewed) {
        for (VanishIntegration integration : list()) {
            if (!integration.canSee(viewer, viewed)) {
                return false;
            }
        }
        return true;
    }

    public enum PriorityType {
        VANISH,
        VIEW
    }

    public static int getPlayerPriority(@NotNull Player player, @NotNull PriorityType priorityType) {
        return switch (priorityType) {
            case VANISH -> getPlayerVanishPriority(player);
            case VIEW -> getPlayerViewPriority(player);
        };
    }

    public static int getPlayerVanishPriority(@NotNull Player player) {
        int value = 0;
        for (VanishIntegration integration : list()) {
            value = Math.max(value, integration.getVanishPriority(player));
        }
        return value;
    }

    public static int getPlayerViewPriority(@NotNull Player player) {
        int value = 0;
        for (VanishIntegration integration : list()) {
            value = Math.max(value, integration.getViewPriority(player));
        }
        return value;
    }

    public abstract boolean isVanished(@NotNull Player player);

    public abstract boolean canVanish(@NotNull Player player);

    public abstract void showPlayer(@NotNull Player player);

    public abstract void hidePlayer(@NotNull Player player);

    public abstract boolean canSee(@NotNull Player viewer, @NotNull Player viewed);

    public abstract boolean canPickup(@NotNull Player player);

    public abstract int getVanishPriority(@NotNull Player player);

    public abstract int getViewPriority(@NotNull Player player);
}
