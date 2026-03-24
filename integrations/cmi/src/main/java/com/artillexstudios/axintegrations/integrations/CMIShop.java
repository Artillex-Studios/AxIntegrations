package com.artillexstudios.axintegrations.integrations;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Modules.Worth.WorthItem;
import com.Zrips.CMI.Modules.Worth.WorthManager;
import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ShopIntegration;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CMIShop extends ShopIntegration {
    private CMI api;
    private WorthManager manager;

    public CMIShop() {
        super("CMI");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.Zrips.CMI.CMI");
    }

    @Override
    public boolean setup() {
        api = CMI.getInstance();
        if (api == null) return false;
        manager = api.getWorthManager();
        if (manager == null) return false;
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Nullable
    @Override
    public Double getBuyPrice(@NotNull ItemStack item) {
        WorthItem worthItem = manager.getWorth(copy(item));
        if (worthItem == null) return null;
        if (worthItem.getBuyPrice() == null) return null;
        return worthItem.getBuyPrice() * item.getAmount();
    }

    @Nullable
    @Override
    public Double getBuyPrice(UUID playerUUID, @NotNull ItemStack item) {
        return getBuyPrice(item);
    }

    @Nullable
    @Override
    public Double getSellPrice(@NotNull ItemStack item) {
        WorthItem worthItem = manager.getWorth(copy(item));
        if (worthItem == null) return null;
        if (worthItem.getSellPrice() == null) return null;
        return worthItem.getSellPrice() * item.getAmount();
    }

    @Nullable
    @Override
    public Double getSellPrice(UUID playerUUID, @NotNull ItemStack item) {
        return getSellPrice(item);
    }
}
