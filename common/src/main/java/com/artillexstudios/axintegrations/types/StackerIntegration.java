package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationManager;
import com.artillexstudios.axintegrations.IntegrationType;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Rules
 * - if the stacker plugin doesn't support something, return false for isStacked and return null for the stack size
 * - the getMaxStackSize should return null if there is no stack limit (BigInteger/BigDecimal) - otherwise return actual max value, like Integer.MAX_VALUE
 */
public abstract class StackerIntegration extends Integration {

    /**
     * returns all loaded integrations
     */
    public static List<StackerIntegration> list() {
        return IntegrationManager.getIntegrations(StackerIntegration.class);
    }

    /**
     * returns a loaded integration
     * if you register multiple, you should use {@link this#list()} instead
     */
    @Nullable
    public static StackerIntegration one() {
        return list().stream().findFirst().orElse(null);
    }

    public StackerIntegration(String name) {
        super(name, IntegrationType.STACKER);
    }

    // item stacker
    public abstract boolean isStacked(@NotNull Item item);

    @Nullable
    public abstract Number getStackSize(@NotNull Item item);

    @Nullable
    public abstract Number getMaxStackSize(@NotNull Item item);

    public abstract boolean setStackSize(@NotNull Item item, @NotNull Number amount);

    // entity stacker
    public abstract boolean isStacked(@NotNull LivingEntity entity);

    @Nullable
    public abstract Number getStackSize(@NotNull LivingEntity entity);

    @Nullable
    public abstract Number getMaxStackSize(@NotNull LivingEntity entity);

    public abstract boolean setStackSize(@NotNull LivingEntity entity, @NotNull Number amount);

    // spawner stacker
    public abstract boolean isStacked(@NotNull CreatureSpawner spawner);

    @Nullable
    public abstract Number getStackSize(@NotNull CreatureSpawner spawner);

    @Nullable
    public abstract Number getMaxStackSize(@NotNull CreatureSpawner spawner);

    public abstract boolean setStackSize(@NotNull CreatureSpawner spawner, @NotNull Number amount);

    // block stacker
    public abstract boolean isStacked(@NotNull Block block);

    @Nullable
    public abstract Number getStackSize(@NotNull Block block);

    @Nullable
    public abstract Number getMaxStackSize(@NotNull Block block);

    public abstract boolean setStackSize(@NotNull Block block, @NotNull Number amount);
}
