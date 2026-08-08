package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axapi.scheduler.Scheduler;
import com.artillexstudios.axintegrations.types.CustomBlockIntegration;
import io.th0rgal.oraxen.api.OraxenBlocks;
import io.th0rgal.oraxen.api.OraxenItems;
import io.th0rgal.oraxen.utils.drops.Drop;
import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class OraxenCustomBlock extends CustomBlockIntegration {

    public OraxenCustomBlock() {
        super("Oraxen");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("io.th0rgal.oraxen.api.OraxenBlocks");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean isCustomBlock(@NotNull String itemId) {
        return OraxenBlocks.isOraxenBlock(itemId);
    }

    @Override
    public boolean isCustomBlock(@NotNull Location location) {
        return OraxenBlocks.isOraxenBlock(location.getBlock());
    }

    @Override
    public boolean place(@NotNull String itemId, @NotNull Location location) {
        boolean note = OraxenItems.hasMechanic(itemId, "noteblock");
        boolean string = OraxenItems.hasMechanic(itemId, "stringblock");
        if (!note && !string) return false;

        Scheduler.get().executeAt(location, () -> {
            if (note) {
                location.getBlock().setType(Material.NOTE_BLOCK);
            } else {
                location.getBlock().setType(Material.TRIPWIRE);
            }
            Scheduler.get().runLaterAt(location, () -> {
                OraxenBlocks.place(itemId, location);
            }, 1);
        });
        return true;
    }

    @Override
    public boolean remove(@NotNull Location location) {
        if (!isCustomBlock(location)) return false;
        OraxenBlocks.remove(location, null, Drop.emptyDrop());
        return true;
    }
}
