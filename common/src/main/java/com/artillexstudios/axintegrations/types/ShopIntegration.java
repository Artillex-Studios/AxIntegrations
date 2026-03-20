package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Rules
 * - handle {@link ItemStack#getAmount()} and include it for the return value
 * - never modify the {@link ItemStack} object, use {@link ItemStack#clone()} if necessary
 * - return null if item is not present in the shop
 * - if the plugin doesn't support per player prices or offline players, just return the regular price of the item
 */
public abstract class ShopIntegration extends Integration {

    public ShopIntegration(String name) {
        super(name, IntegrationType.SHOP);
    }

    @Nullable
    public abstract Number getBuyPrice(ItemStack item);

    @Nullable
    public abstract Number getBuyPrice(UUID uniqueId, ItemStack item);

    @Nullable
    public abstract Number getSellPrice(ItemStack item);

    @Nullable
    public abstract Number getSellPrice(UUID uniqueId, ItemStack item);

    protected ItemStack copy(ItemStack item) {
        ItemStack copy = item.clone();
        copy.setAmount(1);
        return copy;
    }
}
