package com.artillexstudios.axintegrations.integration.prices.implementation;

import com.artillexstudios.axintegrations.integration.prices.PriceIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import su.nightexpress.nexshop.ShopAPI;
import su.nightexpress.nexshop.api.shop.type.TradeType;
import su.nightexpress.nexshop.shop.virtual.impl.VirtualProduct;

public final class ExcellentShopPriceIntegration implements PriceIntegration {

    @Override
    public Double getPrice(ItemStack itemStack) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        VirtualProduct product = ShopAPI.getVirtualShop().getBestProductFor(copy, TradeType.SELL);
        if (product == null || !product.isSellable()) {
            return null;
        }

        return product.getPrice(TradeType.SELL) / product.getUnitAmount() * itemStack.getAmount();
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        VirtualProduct product = ShopAPI.getVirtualShop().getBestProductFor(copy, TradeType.SELL);
        if (product == null || !product.isSellable()) {
            return null;
        }

        return product.getPrice(TradeType.SELL) / product.getUnitAmount() * amount;
    }

    @Override
    public Double getPrice(ItemStack itemStack, OfflinePlayer player) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        VirtualProduct product = ShopAPI.getVirtualShop().getBestProductFor(copy, TradeType.SELL, player.getPlayer());
        if (product == null || !product.isSellable()) {
            return null;
        }

        return product.getPrice(TradeType.SELL, player.getPlayer()) / product.getUnitAmount() * itemStack.getAmount();
    }

    @Override
    public Double getPrice(ItemStack itemStack, long amount, OfflinePlayer player) {
        ItemStack copy = itemStack.clone();
        copy.setAmount(1);
        VirtualProduct product = ShopAPI.getVirtualShop().getBestProductFor(copy, TradeType.SELL, player.getPlayer());
        if (product == null || !product.isSellable()) {
            return null;
        }

        return product.getPrice(TradeType.SELL, player.getPlayer()) / product.getUnitAmount() * amount;
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("ExcellentShop")
        };
    }

    @Override
    public String id() {
        return "excellentshop";
    }
}
