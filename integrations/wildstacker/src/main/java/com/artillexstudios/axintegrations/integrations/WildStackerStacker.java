package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.StackerIntegration;
import com.bgsoftware.wildstacker.api.WildStackerAPI;
import com.bgsoftware.wildstacker.api.objects.StackedBarrel;
import com.bgsoftware.wildstacker.api.objects.StackedEntity;
import com.bgsoftware.wildstacker.api.objects.StackedItem;
import com.bgsoftware.wildstacker.api.objects.StackedSpawner;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WildStackerStacker extends StackerIntegration {

    public WildStackerStacker() {
        super("WildStacker");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.bgsoftware.wildstacker.api.WildStackerAPI");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean isStacked(@NotNull Item item) {
        return WildStackerAPI.getStackedItem(item) != null;
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull Item item) {
        return WildStackerAPI.getItemAmount(item);
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull Item item) {
        StackedItem stackedItem = WildStackerAPI.getStackedItem(item);
        if (stackedItem == null) return null;
        return stackedItem.getStackLimit();
    }

    @Override
    public boolean setStackSize(@NotNull Item item, @NotNull Number amount) {
        StackedItem stackedItem = WildStackerAPI.getStackedItem(item);
        if (stackedItem == null) return false;
        stackedItem.setStackAmount(amount.intValue(), true);
        return true;
    }

    @Override
    public boolean isStacked(@NotNull LivingEntity entity) {
        return WildStackerAPI.getStackedEntity(entity) != null;
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull LivingEntity entity) {
        return WildStackerAPI.getEntityAmount(entity);
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull LivingEntity entity) {
        StackedEntity stackedEntity = WildStackerAPI.getStackedEntity(entity);
        if (stackedEntity == null) return null;
        return stackedEntity.getStackLimit();
    }

    @Override
    public boolean setStackSize(@NotNull LivingEntity entity, @NotNull Number amount) {
        StackedEntity stackedEntity = WildStackerAPI.getStackedEntity(entity);
        if (stackedEntity == null) return false;
        stackedEntity.setStackAmount(amount.intValue(), true);
        return true;
    }

    @Override
    public boolean isStacked(@NotNull CreatureSpawner spawner) {
        return WildStackerAPI.getStackedSpawner(spawner) != null;
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull CreatureSpawner spawner) {
        return WildStackerAPI.getSpawnersAmount(spawner);
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull CreatureSpawner spawner) {
        StackedSpawner stackedSpawner = WildStackerAPI.getStackedSpawner(spawner);
        if (stackedSpawner == null) return null;
        return stackedSpawner.getStackLimit();
    }

    @Override
    public boolean setStackSize(@NotNull CreatureSpawner spawner, @NotNull Number amount) {
        StackedSpawner stackedSpawner = WildStackerAPI.getStackedSpawner(spawner);
        if (stackedSpawner == null) return false;
        stackedSpawner.setStackAmount(amount.intValue(), true);
        return true;
    }

    @Override
    public boolean isStacked(@NotNull Block block) {
        return WildStackerAPI.getStackedBarrel(block) != null;
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull Block block) {
        return WildStackerAPI.getBarrelAmount(block);
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull Block block) {
        StackedBarrel stackedBarrel = WildStackerAPI.getStackedBarrel(block);
        if (stackedBarrel == null) return null;
        return stackedBarrel.getStackLimit();
    }

    @Override
    public boolean setStackSize(@NotNull Block block, @NotNull Number amount) {
        StackedBarrel stackedBarrel = WildStackerAPI.getStackedBarrel(block);
        if (stackedBarrel == null) return false;
        stackedBarrel.setStackAmount(amount.intValue(), true);
        return true;
    }
}
