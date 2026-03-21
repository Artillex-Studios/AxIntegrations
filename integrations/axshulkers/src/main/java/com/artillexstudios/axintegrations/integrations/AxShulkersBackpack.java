package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.BackpackIntegration;
import com.artillexstudios.axshulkers.utils.ShulkerUtils;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AxShulkersBackpack extends BackpackIntegration {

    public AxShulkersBackpack() {
        super("AxShulkers");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.artillexstudios.axshulkers.utils.ShulkerUtils");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean isBackpack(@NotNull ItemStack item) {
        return ShulkerUtils.getShulkerUUID(item) != null;
    }

    @NotNull
    @Override
    public List<ItemStack> getContents(@NotNull ItemStack item) {
        return List.of(ShulkerUtils.getShulkerItems(item));
    }
}
