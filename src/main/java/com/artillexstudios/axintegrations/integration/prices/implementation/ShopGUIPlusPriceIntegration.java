package com.artillexstudios.axintegrations.integration.prices.implementation;

import com.artillexstudios.axintegrations.integration.prices.PriceIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import net.brcdev.shopgui.ShopGuiPlusApi;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

public final class ShopGUIPlusPriceIntegration implements PriceIntegration {

    @Override
    public Double getPrice(ItemStack itemStack) {
        return ShopGuiPlusApi.getItemStackPriceSell(itemStack);
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        return ShopGuiPlusApi.getItemStackPriceSell(copy) * amount;
    }

    @Override
    public Double getPrice(ItemStack itemStack, OfflinePlayer player) {
        return ShopGuiPlusApi.getItemStackPriceSell(player.getPlayer(), itemStack);
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount, OfflinePlayer player) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        return ShopGuiPlusApi.getItemStackPriceSell(player.getPlayer(), copy) * amount;
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("ShopGUIPlus")
        };
    }

    @Override
    public String id() {
        return "shopguiplus";
    }
}
