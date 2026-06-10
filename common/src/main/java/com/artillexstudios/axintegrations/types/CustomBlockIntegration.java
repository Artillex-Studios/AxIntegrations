package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.IntegrationType;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
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

    public static boolean isBlockCustom(@NotNull String itemId) {
        for (CustomBlockIntegration integration : list()) {
            if (integration.isCustomBlock(itemId)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBlockCustom(@NotNull Location location) {
        for (CustomBlockIntegration integration : list()) {
            if (integration.isCustomBlock(location)) {
                return true;
            }
        }
        return false;
    }

    public abstract boolean isCustomBlock(@NotNull String itemId);

    public abstract boolean isCustomBlock(@NotNull Location location);

    public abstract boolean place(@NotNull String itemId, @NotNull Location location);

    public abstract boolean remove(@NotNull Location location);
}
