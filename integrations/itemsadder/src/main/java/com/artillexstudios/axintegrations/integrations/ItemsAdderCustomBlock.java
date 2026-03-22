package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axapi.scheduler.Scheduler;
import com.artillexstudios.axintegrations.types.CustomBlockIntegration;
import dev.lone.itemsadder.api.CustomBlock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class ItemsAdderCustomBlock extends CustomBlockIntegration {

    public ItemsAdderCustomBlock() {
        super("ItemsAdder");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("dev.lone.itemsadder.api.CustomBlock");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean isCustomBlock(@NotNull String itemId) {
        return CustomBlock.getInstance(itemId) != null;
    }

    @Override
    public boolean isCustomBlock(@NotNull Location location) {
        return CustomBlock.byAlreadyPlaced(location.getBlock()) != null;
    }

    @Override
    public boolean place(@NotNull String itemId, @NotNull Location location) {
        CustomBlock block = CustomBlock.getInstance(itemId);
        if (block == null) return false;

        Scheduler.get().executeAt(location, () -> {
            location.getBlock().setType(Material.NOTE_BLOCK);
            Scheduler.get().runLaterAt(location, () -> {
                block.place(itemId, location);
            }, 1);
        });
        return true;
    }

    @Override
    public boolean remove(@NotNull Location location) {
        final CustomBlock customBlock = CustomBlock.byAlreadyPlaced(location.getBlock());
        if (customBlock == null) return false;
        customBlock.remove();
        return true;
    }
}
