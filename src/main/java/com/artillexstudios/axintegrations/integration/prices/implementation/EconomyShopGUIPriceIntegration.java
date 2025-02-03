package com.artillexstudios.axintegrations.integration.prices.implementation;

import com.artillexstudios.axintegrations.integration.prices.PriceIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import me.gypopo.economyshopgui.api.EconomyShopGUIHook;
import me.gypopo.economyshopgui.objects.ShopItem;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

public final class EconomyShopGUIPriceIntegration implements PriceIntegration {

    @Override
    public Double getPrice(ItemStack itemStack) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        ShopItem shopItem = EconomyShopGUIHook.getShopItem(copy);
        if (shopItem == null) {
            return null;
        }

        return EconomyShopGUIHook.getItemSellPrice(shopItem, copy) * itemStack.getAmount();
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        ShopItem shopItem = EconomyShopGUIHook.getShopItem(copy);
        if (shopItem == null) {
            return null;
        }

        return EconomyShopGUIHook.getItemSellPrice(shopItem, copy) * amount;
    }

    @Override
    public Double getPrice(ItemStack itemStack, OfflinePlayer player) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        ShopItem shopItem = EconomyShopGUIHook.getShopItem(copy);
        if (shopItem == null) {
            return null;
        }

        return EconomyShopGUIHook.getItemSellPrice(shopItem, copy, player.getPlayer()) * itemStack.getAmount();
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount, OfflinePlayer player) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        ShopItem shopItem = EconomyShopGUIHook.getShopItem(copy);
        if (shopItem == null) {
            return null;
        }

        return EconomyShopGUIHook.getItemSellPrice(shopItem, copy, player.getPlayer()) * amount;
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("EconomyShopGUI", "EconomyShopGUI-Premium")
        };
    }

    @Override
    public String id() {
        return "economyshopgui";
    }
}
