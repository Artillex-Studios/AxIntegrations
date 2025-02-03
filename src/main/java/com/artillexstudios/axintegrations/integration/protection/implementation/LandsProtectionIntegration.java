package com.artillexstudios.axintegrations.integration.protection.implementation;

import com.artillexstudios.axintegrations.AxIntegrations;
import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.flags.type.Flags;
import me.angeschossen.lands.api.land.LandWorld;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class LandsProtectionIntegration implements ProtectionIntegration {
    private final LandsIntegration instance;

    public LandsProtectionIntegration() {
        this.instance = LandsIntegration.of(JavaPlugin.getProvidingPlugin(AxIntegrations.class));
    }

    @Override
    public boolean canBuild(Player player, Location location) {
        final LandWorld world = this.instance.getWorld(location.getWorld());
        if (world == null) {
            return true;
        }

        return world.hasRoleFlag(player.getUniqueId(), location, Flags.BLOCK_PLACE);
    }

    @Override
    public boolean canPlace(Player player, Location location) {
        final LandWorld world = this.instance.getWorld(location.getWorld());
        if (world == null) {
            return true;
        }

        return world.hasRoleFlag(player.getUniqueId(), location, Flags.BLOCK_PLACE);
    }

    @Override
    public boolean canBreak(Player player, Location location) {
        final LandWorld world = this.instance.getWorld(location.getWorld());
        if (world == null) {
            return true;
        }

        return world.hasRoleFlag(player.getUniqueId(), location, Flags.BLOCK_BREAK);
    }

    @Override
    public boolean canInteract(Player player, Location location) {
        final LandWorld world = this.instance.getWorld(location.getWorld());
        if (world == null) {
            return true;
        }

        return world.hasRoleFlag(player.getUniqueId(), location, Flags.INTERACT_GENERAL);
    }

    @Override
    public boolean canOpen(Player player, Location location) {
        final LandWorld world = this.instance.getWorld(location.getWorld());
        if (world == null) {
            return true;
        }

        return world.hasRoleFlag(player.getUniqueId(), location, Flags.INTERACT_CONTAINER);
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("Lands")
        };
    }

    @Override
    public String id() {
        return "lands";
    }
}
