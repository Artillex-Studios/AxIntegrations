package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.IntegrationType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Rules
 * - getContents must return the real contents in a modifiable list
 */
public abstract class BackpackIntegration extends Integration {

    /**
     * returns all loaded integrations
     */
    public static List<BackpackIntegration> list() {
        return IntegrationManager.getIntegrations(BackpackIntegration.class);
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#list()} instead
     */
    @Nullable
    public static BackpackIntegration one() {
        return list().stream().findFirst().orElse(null);
    }

    public BackpackIntegration(String name) {
        super(name, IntegrationType.BACKPACK);
    }

    public abstract boolean isBackpack(@NotNull ItemStack item);

    @NotNull
    public abstract List<ItemStack> getContents(@NotNull ItemStack item);
}
