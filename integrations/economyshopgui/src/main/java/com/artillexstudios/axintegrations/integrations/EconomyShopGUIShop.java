package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ShopIntegration;
import me.gypopo.economyshopgui.api.EconomyShopGUIHook;
import me.gypopo.economyshopgui.api.objects.BuyPrice;
import me.gypopo.economyshopgui.api.objects.SellPrice;
import me.gypopo.economyshopgui.objects.ShopItem;
import me.gypopo.economyshopgui.util.EcoType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public class EconomyShopGUIShop extends ShopIntegration {

    public EconomyShopGUIShop() {
        super("EconomyShopGUI");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("me.gypopo.economyshopgui.api.EconomyShopGUIHook");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Nullable
    @Override
    public Double getBuyPrice(@NotNull ItemStack item) {
        ShopItem shopItem = EconomyShopGUIHook.getShopItem(copy(item));
        if (shopItem == null) return null;
        double price = EconomyShopGUIHook.getItemBuyPrice(shopItem, 1) * item.getAmount();
        if (price <= 0) return null;
        return price;
    }

    @Nullable
    @Override
    public Double getBuyPrice(UUID playerUUID, @NotNull ItemStack item) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);
        BuyPrice price = EconomyShopGUIHook.getBuyPrice(player, copy(item)).orElse(null);
        if (price == null) return null;
        for (Map.Entry<EcoType, Double> entry : price.getPrices().entrySet()) {
            return entry.getValue() * item.getAmount();
        }
        return null;
    }

    @Nullable
    @Override
    public Double getSellPrice(@NotNull ItemStack item) {
        ItemStack copy = copy(item);
        ShopItem shopItem = EconomyShopGUIHook.getShopItem(copy);
        if (shopItem == null) return null;
        return EconomyShopGUIHook.getItemSellPrice(shopItem, copy) * item.getAmount();
    }

    @Nullable
    @Override
    public Double getSellPrice(UUID playerUUID, @NotNull ItemStack item) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);
        SellPrice price = EconomyShopGUIHook.getSellPrice(player, copy(item)).orElse(null);
        if (price == null) return null;
        for (Map.Entry<EcoType, Double> entry : price.getPrices().entrySet()) {
            return entry.getValue() * item.getAmount();
        }
        return null;
    }
}
