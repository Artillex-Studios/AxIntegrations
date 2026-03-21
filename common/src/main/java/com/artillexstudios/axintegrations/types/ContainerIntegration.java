package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.IntegrationType;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Rules
 * - getContents must return the real contents in a modifiable list
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

    public ContainerIntegration(String name) {
        super(name, IntegrationType.CONTAINER);
    }

    public abstract boolean isContainer(@NotNull Block block);

    @NotNull
    public abstract List<ItemStack> getContents(@NotNull ItemStack item);
}
