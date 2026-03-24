package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.StackerIntegration;
import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.handlers.StackedBlocksManager;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SuperiorSkyBlock2Stacker extends StackerIntegration {
    private StackedBlocksManager manager;

    public SuperiorSkyBlock2Stacker() {
        super("SuperiorSkyBlock2");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI");
    }

    @Override
    public boolean setup() {
        manager = SuperiorSkyblockAPI.getStackedBlocks();
        if (manager == null) return false;
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean isStacked(@NotNull Item item) {
        return false;
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull Item item) {
        return null;
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull Item item) {
        return null;
    }

    @Override
    public boolean setStackSize(@NotNull Item item, @NotNull Number amount) {
        return false;
    }

    @Override
    public boolean isStacked(@NotNull LivingEntity entity) {
        return false;
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull LivingEntity entity) {
        return null;
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull LivingEntity entity) {
        return null;
    }

    @Override
    public boolean setStackSize(@NotNull LivingEntity entity, @NotNull Number amount) {
        return false;
    }

    @Override
    public boolean isStacked(@NotNull CreatureSpawner spawner) {
        return false;
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull CreatureSpawner spawner) {
        return null;
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull CreatureSpawner spawner) {
        return null;
    }

    @Override
    public boolean setStackSize(@NotNull CreatureSpawner spawner, @NotNull Number amount) {
        return false;
    }

    @Override
    public boolean isStacked(@NotNull Block block) {
        return manager.getStackedBlockAmount(block) > 0;
    }

    @Nullable
    @Override
    public Number getStackSize(@NotNull Block block) {
        int amount = manager.getStackedBlockAmount(block);
        if (amount <= 0) return null;
        return amount;
    }

    @Nullable
    @Override
    public Number getMaxStackSize(@NotNull Block block) {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean setStackSize(@NotNull Block block, @NotNull Number amount) {
        if (manager.getStackedBlockAmount(block) <= 0) return false;
        return manager.setStackedBlock(block, amount.intValue());
    }
}
