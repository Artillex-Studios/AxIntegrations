package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ShopIntegration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.nightexpress.nexshop.ShopAPI;
import su.nightexpress.nexshop.api.shop.type.TradeType;
import su.nightexpress.nexshop.shop.virtual.VirtualShopModule;
import su.nightexpress.nexshop.shop.virtual.impl.VirtualProduct;

import java.util.UUID;

public class ExcellentShop4Shop extends ShopIntegration {
    private VirtualShopModule module;

    public ExcellentShop4Shop() {
        super("ExcellentShop");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("su.nightexpress.nexshop.ShopAPI");
    }

    @Override
    public boolean setup() {
        module = ShopAPI.getVirtualShop();
        if (module == null) return false;
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Nullable
    @Override
    public Double getBuyPrice(@NotNull ItemStack item) {
        VirtualProduct product = module.getBestProductFor(copy(item), TradeType.BUY);
        if (product == null) return null;
        return product.getFinalPrice(TradeType.BUY, item.getAmount());
    }

    @Nullable
    @Override
    public Double getBuyPrice(UUID playerUUID, @NotNull ItemStack item) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) return getBuyPrice(item);
        VirtualProduct product = module.getBestProductFor(copy(item), TradeType.BUY, player);
        if (product == null) return null;
        return product.getFinalPrice(TradeType.BUY, item.getAmount(), player);
    }

    @Nullable
    @Override
    public Double getSellPrice(@NotNull ItemStack item) {
        VirtualProduct product = module.getBestProductFor(copy(item), TradeType.SELL);
        if (product == null) return null;
        return product.getFinalPrice(TradeType.SELL, item.getAmount());
    }

    @Nullable
    @Override
    public Double getSellPrice(UUID playerUUID, @NotNull ItemStack item) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) return getSellPrice(item);
        VirtualProduct product = module.getBestProductFor(copy(item), TradeType.SELL, player);
        if (product == null) return null;
        return product.getFinalPrice(TradeType.SELL, item.getAmount(), player);
    }
}
