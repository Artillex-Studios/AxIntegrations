package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ShopIntegration;
import me.gypopo.economyshopgui.api.EconomyShopGUIHook;
import me.gypopo.economyshopgui.api.objects.BuyPrice;
import me.gypopo.economyshopgui.api.objects.SellPrice;
import me.gypopo.economyshopgui.objects.ShopItem;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class EconomyShopGUIShop extends ShopIntegration {

    public EconomyShopGUIShop() {
        super("EconomyShopGUI");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("me.gypopo.economyshopgui.api.EconomyShopGUIHook");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Nullable
    @Override
    public Number getBuyPrice(ItemStack item) {
        Double price = EconomyShopGUIHook.getItemBuyPrice(copy(item));
        if (price == null) return null;
        return price * item.getAmount();
    }

    @Nullable
    @Override
    public Number getBuyPrice(UUID uniqueId, ItemStack item) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(uniqueId);
        BuyPrice price = EconomyShopGUIHook.getBuyPrice(player, copy(item)).orElse(null);
        if (price == null) return null;
        return price.getAmount() * item.getAmount();
    }

    @Nullable
    @Override
    public Number getSellPrice(ItemStack item) {
        ShopItem shopItem = EconomyShopGUIHook.getShopItem(copy(item));
        if (shopItem == null) return null;
        return EconomyShopGUIHook.getItemSellPrice(shopItem, copy(item)) * item.getAmount();
    }

    @Nullable
    @Override
    public Number getSellPrice(UUID uniqueId, ItemStack item) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(uniqueId);
        SellPrice price = EconomyShopGUIHook.getSellPrice(player, copy(item)).orElse(null);
        if (price == null) return null;
        return price.getAmount() * item.getAmount();
    }
}
