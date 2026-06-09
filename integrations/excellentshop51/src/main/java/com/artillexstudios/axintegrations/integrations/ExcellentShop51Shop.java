package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ShopIntegration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.nightexpress.excellentshop.ShopPlugin;
import su.nightexpress.excellentshop.api.product.TradeType;
import su.nightexpress.excellentshop.virtualshop.VirtualShopModule;
import su.nightexpress.excellentshop.virtualshop.product.VirtualProduct;

import java.util.UUID;

public class ExcellentShop51Shop extends ShopIntegration {
    private VirtualShopModule module;

    public ExcellentShop51Shop() {
        super("ExcellentShop");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("su.nightexpress.excellentshop.virtualshop.VirtualShopModule");
    }

    @Override
    public boolean setup() {
        ShopPlugin plugin = (ShopPlugin) Bukkit.getPluginManager().getPlugin("ExcellentShop");
        if (plugin == null) return false;
        module = plugin.getModuleRegistry().byType(VirtualShopModule.class).orElse(null);
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
        VirtualProduct product = module.getBestProductFor(copy(item), TradeType.BUY);
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
        VirtualProduct product = module.getBestProductFor(copy(item), TradeType.SELL);
        if (product == null) return null;
        return product.getFinalPrice(TradeType.SELL, item.getAmount(), player);
    }
}
