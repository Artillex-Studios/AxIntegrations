package com.artillexstudios.axintegrations.integration.prices.implementation;

import com.artillexstudios.axintegrations.integration.prices.PriceIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import com.earth2me.essentials.IEssentials;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;

public final class EssentialsPriceIntegration implements PriceIntegration {
    private final IEssentials essentials;

    public EssentialsPriceIntegration() {
        this.essentials = (IEssentials) Bukkit.getPluginManager().getPlugin("Essentials");
    }

    @Override
    public Double getPrice(ItemStack itemStack) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        BigDecimal price = this.essentials.getWorth().getPrice(this.essentials, copy);
        if (price == null) {
            return null;
        }

        return price.doubleValue() * itemStack.getAmount();
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        BigDecimal price = this.essentials.getWorth().getPrice(this.essentials, copy);
        if (price == null) {
            return null;
        }

        return price.doubleValue() * amount;
    }

    @Override
    public Double getPrice(ItemStack itemStack, OfflinePlayer player) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        BigDecimal price = this.essentials.getWorth().getPrice(this.essentials, copy);
        if (price == null) {
            return null;
        }

        return price.doubleValue() * itemStack.getAmount();
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount, OfflinePlayer player) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        BigDecimal price = this.essentials.getWorth().getPrice(this.essentials, copy);
        if (price == null) {
            return null;
        }

        return price.doubleValue() * amount;
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("Essentials")
        };
    }

    @Override
    public String id() {
        return "essentials";
    }
}
