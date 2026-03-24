package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.StackerIntegration;
import dev.rosewood.rosestacker.api.RoseStackerAPI;
import dev.rosewood.rosestacker.stack.StackedBlock;
import dev.rosewood.rosestacker.stack.StackedEntity;
import dev.rosewood.rosestacker.stack.StackedItem;
import dev.rosewood.rosestacker.stack.StackedSpawner;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RoseStackerStacker extends StackerIntegration {
    private RoseStackerAPI api;

    public RoseStackerStacker() {
        super("RoseStacker");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("dev.rosewood.rosestacker.api.RoseStackerAPI");
    }

    @Override
    public boolean setup() {
        api = RoseStackerAPI.getInstance();
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean isStacked(@NotNull Item item) {
        return api.isItemStacked(item);
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull Item item) {
        StackedItem stackedItem = api.getStackedItem(item);
        if (stackedItem == null) return null;
        return stackedItem.getStackSize();
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull Item item) {
        StackedItem stackedItem = api.getStackedItem(item);
        if (stackedItem == null) return null;
        return getLimit(stackedItem.getStackSettings().getMaxStackSize());
    }

    @Override
    public boolean setStackSize(@NotNull Item item, @NotNull Number amount) {
        StackedItem stackedItem = api.getStackedItem(item);
        if (stackedItem == null) return false;
        stackedItem.setStackSize(amount.intValue());
        return true;
    }

    @Override
    public boolean isStacked(@NotNull LivingEntity entity) {
        return api.isEntityStacked(entity);
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull LivingEntity entity) {
        StackedEntity stackedEntity = api.getStackedEntity(entity);
        if (stackedEntity == null) return null;
        return stackedEntity.getStackSize();
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull LivingEntity entity) {
        StackedEntity stackedEntity = api.getStackedEntity(entity);
        if (stackedEntity == null) return null;
        return getLimit(stackedEntity.getStackSettings().getMaxStackSize());
    }

    @Override
    public boolean setStackSize(@NotNull LivingEntity entity, @NotNull Number amount) {
        StackedEntity stackedEntity = api.getStackedEntity(entity);
        if (stackedEntity == null) return false;
        int current = stackedEntity.getStackSize();
        int target = amount.intValue();
        if (target > current) {
            stackedEntity.increaseStackSize(target - current, true);
        } else if (target < current) {
            for (int i = 0; i < current - target; i++) {
                stackedEntity.decreaseStackSize();
            }
        }
        return true;
    }

    @Override
    public boolean isStacked(@NotNull CreatureSpawner spawner) {
        return api.isSpawnerStacked(spawner.getBlock());
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull CreatureSpawner spawner) {
        StackedSpawner stackedSpawner = api.getStackedSpawner(spawner.getBlock());
        if (stackedSpawner == null) return null;
        return stackedSpawner.getStackSize();
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull CreatureSpawner spawner) {
        StackedSpawner stackedSpawner = api.getStackedSpawner(spawner.getBlock());
        if (stackedSpawner == null) return null;
        return getLimit(stackedSpawner.getStackSettings().getMaxStackSize());
    }

    @Override
    public boolean setStackSize(@NotNull CreatureSpawner spawner, @NotNull Number amount) {
        StackedSpawner stackedSpawner = api.getStackedSpawner(spawner.getBlock());
        if (stackedSpawner == null) return false;
        stackedSpawner.setStackSize(amount.intValue());
        return true;
    }

    @Override
    public boolean isStacked(@NotNull Block block) {
        return api.isBlockStacked(block);
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull Block block) {
        StackedBlock stackedBlock = api.getStackedBlock(block);
        if (stackedBlock == null) return null;
        return stackedBlock.getStackSize();
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull Block block) {
        StackedBlock stackedBlock = api.getStackedBlock(block);
        if (stackedBlock == null) return null;
        return getLimit(stackedBlock.getStackSettings().getMaxStackSize());
    }

    @Override
    public boolean setStackSize(@NotNull Block block, @NotNull Number amount) {
        StackedBlock stackedBlock = api.getStackedBlock(block);
        if (stackedBlock == null) return false;
        stackedBlock.setStackSize(amount.intValue());
        return true;
    }

    private Number getLimit(int value) {
        if (value == -1) return Integer.MAX_VALUE;
        return value;
    }
}
