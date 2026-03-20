package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ShopIntegration;
import net.brcdev.shopgui.ShopGuiPlusApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ShopGUIPlusShop extends ShopIntegration {

    public ShopGUIPlusShop() {
        super("ShopGUIPlus");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("net.brcdev.shopgui.ShopGuiPlusApi");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Nullable
    @Override
    public Number getBuyPrice(ItemStack item) {
        double price = ShopGuiPlusApi.getItemStackPriceBuy(copy(item));
        if (price == -1.0D) return null;
        return price * item.getAmount();
    }

    @Nullable
    @Override
    public Number getBuyPrice(UUID uniqueId, ItemStack item) {
        Player player = Bukkit.getPlayer(uniqueId);
        if (player == null) return getBuyPrice(item);
        double price = ShopGuiPlusApi.getItemStackPriceBuy(player, copy(item));
        if (price == -1.0D) return null;
        return price * item.getAmount();
    }

    @Nullable
    @Override
    public Number getSellPrice(ItemStack item) {
        double price = ShopGuiPlusApi.getItemStackPriceSell(copy(item));
        if (price == -1.0D) return null;
        return price * item.getAmount();
    }

    @Nullable
    @Override
    public Number getSellPrice(UUID uniqueId, ItemStack item) {
        Player player = Bukkit.getPlayer(uniqueId);
        if (player == null) return getBuyPrice(item);
        double price = ShopGuiPlusApi.getItemStackPriceSell(player, copy(item));
        if (price == -1.0D) return null;
        return price * item.getAmount();
    }
}
