package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.IntegrationType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

/**
 * Rules
 * - handle {@link ItemStack#getAmount()} and include it for the return value
 * - never modify the {@link ItemStack} object, use {@link ItemStack#clone()} if necessary
 * - return null if item is not present in the shop
 * - if the plugin doesn't support per player prices or offline players, just return the regular price of the item
 */
public abstract class ShopIntegration extends Integration {

    /**
     * returns all loaded integrations
     */
    public static List<ShopIntegration> list() {
        return IntegrationManager.getIntegrations(ShopIntegration.class);
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#list()} instead
     */
    @Nullable
    public static ShopIntegration one() {
        return list().stream().findFirst().orElse(null);
    }

    public ShopIntegration(String name) {
        super(name, IntegrationType.SHOP);
    }

    @Nullable
    public abstract Number getBuyPrice(@NotNull ItemStack item);

    @Nullable
    public abstract Number getBuyPrice(UUID playerUUID, @NotNull ItemStack item);

    @Nullable
    public abstract Number getSellPrice(@NotNull ItemStack item);

    @Nullable
    public abstract Number getSellPrice(UUID playerUUID, @NotNull ItemStack item);

    protected ItemStack copy(@NotNull ItemStack item) {
        ItemStack copy = item.clone();
        copy.setAmount(1);
        return copy;
    }
}
