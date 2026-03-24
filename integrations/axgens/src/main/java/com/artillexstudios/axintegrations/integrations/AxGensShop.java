package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axgens.api.AxGensAPI;
import com.artillexstudios.axintegrations.types.ShopIntegration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class AxGensShop extends ShopIntegration {

    public AxGensShop() {
        super("AxGens");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.artillexstudios.axgens.api.AxGensAPI");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Nullable
    @Override
    public Double getBuyPrice(@NotNull ItemStack item) {
        return getSellPrice(item);
    }

    @Nullable
    @Override
    public Double getBuyPrice(UUID playerUUID, @NotNull ItemStack item) {
        return getSellPrice(playerUUID, item);
    }

    @Nullable
    @Override
    public Double getSellPrice(@NotNull ItemStack item) {
        double price = AxGensAPI.getPrice(copy(item));
        if (price == -1.0D) return null;
        return price * item.getAmount();
    }

    @Nullable
    @Override
    public Double getSellPrice(UUID playerUUID, @NotNull ItemStack item) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) return getBuyPrice(item);
        double price = AxGensAPI.getPrice(player, copy(item));
        if (price == -1.0D) return null;
        return price * item.getAmount();
    }
}
