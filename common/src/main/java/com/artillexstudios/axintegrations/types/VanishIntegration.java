package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.IntegrationType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Rules
 * - if the plugin can't answer {@link this#isVanished(Player)}, return false
 * - if the plugin can't answer {@link this#canSee(Player, Player)}, return true
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
        for (VanishIntegration integration : list()) {
            if (integration.isVanished(player)) {
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

    public abstract boolean isVanished(@NotNull Player player);

    public abstract void showPlayer(@NotNull Player player);

    public abstract void hidePlayer(@NotNull Player player);

    public abstract boolean canSee(@NotNull Player viewer, @NotNull Player viewed);
}
