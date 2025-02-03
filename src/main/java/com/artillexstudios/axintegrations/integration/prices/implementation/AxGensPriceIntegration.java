package com.artillexstudios.axintegrations.integration.prices.implementation;

import com.artillexstudios.axgens.api.AxGensAPI;
import com.artillexstudios.axintegrations.integration.prices.PriceIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

public final class AxGensPriceIntegration implements PriceIntegration {

    @Override
    public Double getPrice(ItemStack itemStack) {
        return AxGensAPI.getPrice(itemStack);
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount) {
        return AxGensAPI.getPrice(itemStack) * amount;
    }

    @Override
    public Double getPrice(ItemStack itemStack, OfflinePlayer player) {
        return AxGensAPI.getPrice(player.getPlayer(), itemStack);
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount, OfflinePlayer player) {
        return AxGensAPI.getPrice(player.getPlayer(), itemStack) * amount;
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
