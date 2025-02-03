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
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        WorthItem worth = this.cmi.getWorthManager().getWorth(copy);
        return worth.getSellPrice() * itemStack.getAmount();
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        WorthItem worth = this.cmi.getWorthManager().getWorth(copy);
        return worth.getSellPrice() * amount;
    }

    @Override
    public Double getPrice(ItemStack itemStack, OfflinePlayer player) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        WorthItem worth = this.cmi.getWorthManager().getWorth(copy);
        return worth.getSellPrice() * itemStack.getAmount();
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount, OfflinePlayer player) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        WorthItem worth = this.cmi.getWorthManager().getWorth(copy);
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
