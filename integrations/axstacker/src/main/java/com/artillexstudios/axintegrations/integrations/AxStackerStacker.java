package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.StackerIntegration;
import com.artillexstudios.axintegrations.utils.NumberTool;
import com.artillexstudios.axstacker.api.AxStackerAPI;
import com.artillexstudios.axstacker.config.Config;
import com.artillexstudios.axstacker.stack.entity.StackedEntity;
import com.artillexstudios.axstacker.stack.item.StackedItem;
import com.artillexstudios.axstacker.stack.spawner.StackedSpawner;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigInteger;

public class AxStackerStacker extends StackerIntegration {

    public AxStackerStacker() {
        super("AxStacker");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.artillexstudios.axstacker.api.AxStackerAPI");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean isStacked(@NotNull Item item) {
        return AxStackerAPI.getItemStack(item) != null;
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull Item item) {
        return AxStackerAPI.getItemStackSize(item);
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull Item item) {
        return getLimit(Config.ITEM_MAX_STACK_SIZE);
    }

    @Override
    public boolean setStackSize(@NotNull Item item, @NotNull Number amount) {
        StackedItem stackedItem = AxStackerAPI.getItemStack(item);
        if (stackedItem == null) return false;
        stackedItem.setStackSize(NumberTool.toBigInteger(amount));
        return true;
    }

    @Override
    public boolean isStacked(@NotNull LivingEntity entity) {
        return AxStackerAPI.getEntityStack(entity) != null;
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull LivingEntity entity) {
        return AxStackerAPI.getEntityStackSize(entity);
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull LivingEntity entity) {
        return getLimit(Config.ENTITY_MAX_STACK_SIZE);
    }

    @Override
    public boolean setStackSize(@NotNull LivingEntity entity, @NotNull Number amount) {
        StackedEntity stackedEntity = AxStackerAPI.getEntityStack(entity);
        if (stackedEntity == null) return false;
        stackedEntity.setStackSize(NumberTool.toBigInteger(amount));
        return true;
    }

    @Override
    public boolean isStacked(@NotNull CreatureSpawner spawner) {
        return AxStackerAPI.getSpawnerStack(spawner.getBlock()) != null;
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull CreatureSpawner spawner) {
        return AxStackerAPI.getSpawnerStackSize(spawner.getBlock());
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull CreatureSpawner spawner) {
        return getLimit(Config.SPAWNER_MAX_STACK_SIZE);
    }

    @Override
    public boolean setStackSize(@NotNull CreatureSpawner spawner, @NotNull Number amount) {
        StackedSpawner stackedSpawner = AxStackerAPI.getSpawnerStack(spawner.getBlock());
        if (stackedSpawner == null) return false;
        stackedSpawner.setStackSize(NumberTool.toBigInteger(amount));
        return true;
    }

    @Override
    public boolean isStacked(@NotNull Block block) {
        return false;
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull Block block) {
        return null;
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull Block block) {
        return null;
    }

    @Override
    public boolean setStackSize(@NotNull Block block, @NotNull Number amount) {
        return false;
    }

    private Number getLimit(BigInteger value) {
        if (value.compareTo(BigInteger.ZERO) <= 0) return null;
        return value;
    }
}
