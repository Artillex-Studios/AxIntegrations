package com.artillexstudios.axintegrations.integration.prices.implementation;

import com.artillexstudios.axgens.api.AxGensAPI;
import com.artillexstudios.axintegrations.integration.prices.PriceIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

public final class AxGensPriceIntegration implements PriceIntegration {

    @Override
    public Double getPrice(ItemStack itemStack) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        return AxGensAPI.getPrice(copy) * itemStack.getAmount();
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        return AxGensAPI.getPrice(copy) * amount;
    }

    @Override
    public Double getPrice(ItemStack itemStack, OfflinePlayer player) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        return AxGensAPI.getPrice(player.getPlayer(), copy) * itemStack.getAmount();
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount, OfflinePlayer player) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        return AxGensAPI.getPrice(player.getPlayer(), copy) * amount;
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("AxGens")
        };
    }

    @Override
    public String id() {
        return "axgens";
    }
}
