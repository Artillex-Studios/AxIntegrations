package com.artillexstudios.axintegrations.integration.prices.implementation;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Modules.Worth.WorthItem;
import com.artillexstudios.axintegrations.integration.prices.PriceIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

public final class CMIPriceIntegration implements PriceIntegration {
    private final CMI cmi;

    public CMIPriceIntegration() {
        this.cmi = CMI.getInstance();
    }

    @Override
    public Double getPrice(ItemStack itemStack) {
        WorthItem worth = this.cmi.getWorthManager().getWorth(itemStack);
        return worth.getSellPrice();
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount) {
        WorthItem worth = this.cmi.getWorthManager().getWorth(itemStack);
        return worth.getSellPrice() * amount;
    }

    @Override
    public Double getPrice(ItemStack itemStack, OfflinePlayer player) {
        WorthItem worth = this.cmi.getWorthManager().getWorth(itemStack);
        return worth.getSellPrice();
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount, OfflinePlayer player) {
        WorthItem worth = this.cmi.getWorthManager().getWorth(itemStack);
        return worth.getSellPrice() * amount;
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("CMI")
        };
    }

    @Override
    public String id() {
        return "cmi";
    }
}
