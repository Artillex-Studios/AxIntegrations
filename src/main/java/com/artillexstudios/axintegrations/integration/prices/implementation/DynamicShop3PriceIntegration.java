package com.artillexstudios.axintegrations.integration.prices.implementation;

import com.artillexstudios.axintegrations.integration.prices.PriceIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import me.sat7.dynamicshop.DynaShopAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

public final class DynamicShop3PriceIntegration implements PriceIntegration {

    @Override
    public Double getPrice(ItemStack itemStack) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        double max = -1;
        for (String shop : DynaShopAPI.getShops()) {
            max = Math.max(max, DynaShopAPI.getSellPrice(shop, copy));
        }

        if (max == -1) {
            return max;
        }

        return max * itemStack.getAmount();
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        double max = -1;
        for (String shop : DynaShopAPI.getShops()) {
            max = Math.max(max, DynaShopAPI.getSellPrice(shop, copy));
        }

        if (max == -1) {
            return max;
        }

        return max * amount;
    }

    @Override
    public Double getPrice(ItemStack itemStack, OfflinePlayer player) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        double max = -1;
        for (String shop : DynaShopAPI.getShops()) {
            max = Math.max(max, DynaShopAPI.getSellPrice(shop, copy));
        }

        if (max == -1) {
            return max;
        }

        return max * itemStack.getAmount();
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount, OfflinePlayer player) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        double max = -1;
        for (String shop : DynaShopAPI.getShops()) {
            max = Math.max(max, DynaShopAPI.getSellPrice(shop, copy));
        }

        if (max == -1) {
            return max;
        }

        return max * amount;
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("DynamicShop")
        };
    }

    @Override
    public String id() {
        return "dynamicshop3";
    }
}
