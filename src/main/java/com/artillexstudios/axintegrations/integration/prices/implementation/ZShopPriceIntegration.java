package com.artillexstudios.axintegrations.integration.prices.implementation;

import com.artillexstudios.axintegrations.integration.prices.PriceIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import fr.maxlego08.zshop.api.ShopManager;
import fr.maxlego08.zshop.api.buttons.ItemButton;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Optional;

public final class ZShopPriceIntegration implements PriceIntegration {
    private final ShopManager shopManager;

    public ZShopPriceIntegration() {
        RegisteredServiceProvider<ShopManager> shopManager = Bukkit.getServicesManager().getRegistration(ShopManager.class);
        //noinspection DataFlowIssue
        this.shopManager = shopManager.getProvider();
    }

    @Override
    public Double getPrice(ItemStack itemStack) {
        Optional<ItemButton> button = this.shopManager.getItemButton(itemStack.getType());
        if (button.isEmpty()) {
            return null;
        }

        return button.get().getSellPrice(itemStack.getAmount());
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount) {
        Optional<ItemButton> button = this.shopManager.getItemButton(itemStack.getType());
        if (button.isEmpty()) {
            return null;
        }

        return button.get().getSellPrice((int) amount);
    }

    @Override
    public Double getPrice(ItemStack itemStack, OfflinePlayer player) {
        Optional<ItemButton> button = this.shopManager.getItemButton(player.getPlayer(), itemStack);
        if (button.isEmpty()) {
            return null;
        }

        return button.get().getSellPrice(player.getPlayer(), itemStack.getAmount());
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount, OfflinePlayer player) {
        Optional<ItemButton> button = this.shopManager.getItemButton(player.getPlayer(), itemStack);
        if (button.isEmpty()) {
            return null;
        }

        return button.get().getSellPrice(player.getPlayer(), (int) amount);
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("zShop")
        };
    }

    @Override
    public String id() {
        return "zshop";
    }
}
