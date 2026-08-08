package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axapi.scheduler.Scheduler;
import com.artillexstudios.axintegrations.types.CustomBlockIntegration;
import com.nexomc.nexo.api.NexoBlocks;
import com.nexomc.nexo.api.NexoItems;
import com.nexomc.nexo.utils.drops.Drop;
import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class NexoCustomBlock extends CustomBlockIntegration {

    public NexoCustomBlock() {
        super("Nexo");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.nexomc.nexo.api.NexoBlocks");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean isCustomBlock(@NotNull String itemId) {
        return NexoBlocks.isCustomBlock(itemId);
    }

    @Override
    public boolean isCustomBlock(@NotNull Location location) {
        return NexoBlocks.isCustomBlock(location.getBlock());
    }

    @Override
    public boolean place(@NotNull String itemId, @NotNull Location location) {
        boolean note = NexoItems.hasMechanic(itemId, "noteblock");
        boolean string = NexoItems.hasMechanic(itemId, "stringblock");
        if (!note && !string) return false;

        Scheduler.get().executeAt(location, () -> {
            if (note) {
                location.getBlock().setType(Material.NOTE_BLOCK);
            } else {
                location.getBlock().setType(Material.TRIPWIRE);
            }
            Scheduler.get().runLaterAt(location, () -> {
                NexoBlocks.place(itemId, location);
            }, 1);
        });
        return true;
    }

    @Override
    public boolean remove(@NotNull Location location) {
        if (!isCustomBlock(location)) return false;
        NexoBlocks.remove(location, null, Drop.emptyDrop());
        return true;
    }
}
