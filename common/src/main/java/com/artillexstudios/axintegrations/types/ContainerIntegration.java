package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.IntegrationType;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Rules
 * - getContents must return the real contents in a modifiable list
 * - the contents should always include AIR items if they exist
 */
public abstract class ContainerIntegration extends Integration {

    /**
     * returns all loaded integrations
     */
    public static List<ContainerIntegration> list() {
        return IntegrationManager.getIntegrations(ContainerIntegration.class);
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#list()} instead
     */
    @Nullable
    public static ContainerIntegration one() {
        return list().stream().findFirst().orElse(null);
    }

    /**
     * returns a loaded integration by name
     */
    @Nullable
    public static ContainerIntegration one(String name) {
        return list().stream().filter(i -> {
            return i.getName().equalsIgnoreCase(name) || i.getFormattedName().equalsIgnoreCase(name);
        }).findFirst().orElse(null);
    }

    @Nullable
    public static ContainerIntegration getContainerIntegration(@NotNull Block block) {
        for (ContainerIntegration integration : list()) {
            if (!integration.isContainer(block)) continue;
            return integration;
        }
        return null;
    }

    public static boolean isContainerBlock(@NotNull Block block) {
        for (ContainerIntegration integration : list()) {
            if (!integration.isContainer(block)) continue;
            return true;
        }
        return false;
    }

    @NotNull
    public static List<ItemStack> getContainerItems(@NotNull Block block) {
        for (ContainerIntegration integration : list()) {
            if (!integration.isContainer(block)) continue;
            return integration.getContents(block);
        }
        return new ArrayList<>();
    }

    public ContainerIntegration(String name) {
        super(name, IntegrationType.CONTAINER);
    }

    public abstract boolean isContainer(@NotNull Block block);

    @NotNull
    public abstract List<ItemStack> getContents(@NotNull Block block);
}
