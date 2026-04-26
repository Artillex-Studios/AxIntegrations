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

    public enum PurchaseType {
        BUY,
        SELL
    }

    @Nullable
    public static Double getSellPrice(@Nullable ItemStack item, @Nullable UUID playerUUID) {
        return getPrice(PurchaseType.SELL, item, playerUUID);
    }

    @Nullable
    public static Double getBuyPrice(@Nullable ItemStack item, @Nullable UUID playerUUID) {
        return getPrice(PurchaseType.BUY, item, playerUUID);
    }

    @Nullable
    public static Double getPrice(@NotNull PurchaseType purchaseType, @Nullable ItemStack item, @Nullable UUID playerUUID) {
        if (item == null) return null;
        Double highest = null;
        for (ShopIntegration shopIntegration : list()) {
            Double current;
            if (purchaseType == PurchaseType.BUY) {
                if (playerUUID == null) current = shopIntegration.getBuyPrice(item);
                else current = shopIntegration.getBuyPrice(playerUUID, item);
            } else {
                if (playerUUID == null) current = shopIntegration.getSellPrice(item);
                else current = shopIntegration.getSellPrice(playerUUID, item);
            }
            if (current == null) continue;
            if (highest == null) {
                highest = current;
                continue;
            }
            highest = Math.max(highest, current);
        }
        return highest;
    }

    public ShopIntegration(String name) {
        super(name, IntegrationType.SHOP);
    }

    @Nullable
    public abstract Double getBuyPrice(@NotNull ItemStack item);

    @Nullable
    public abstract Double getBuyPrice(UUID playerUUID, @NotNull ItemStack item);

    @Nullable
    public abstract Double getSellPrice(@NotNull ItemStack item);

    @Nullable
    public abstract Double getSellPrice(UUID playerUUID, @NotNull ItemStack item);

    protected ItemStack copy(@NotNull ItemStack item) {
        ItemStack copy = item.clone();
        copy.setAmount(1);
        return copy;
    }
}
