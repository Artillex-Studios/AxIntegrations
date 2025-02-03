package com.artillexstudios.axintegrations.integration.protection.implementation;

import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class GriefPreventionProtectionIntegration implements ProtectionIntegration {

    @Override
    public boolean canBuild(Player player, Location location) {
        return GriefPrevention.instance.allowBuild(player, location) == null;
    }

    @Override
    public boolean canPlace(Player player, Location location) {
        return GriefPrevention.instance.allowBuild(player, location) == null;
    }

    @Override
    public boolean canBreak(Player player, Location location) {
        return GriefPrevention.instance.allowBreak(player, location.getBlock(), location) == null;
    }

    @Override
    public boolean canInteract(Player player, Location location) {
        return GriefPrevention.instance.allowBuild(player, location) == null;
    }

    @Override
    public boolean canOpen(Player player, Location location) {
        return GriefPrevention.instance.allowBuild(player, location) == null;
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("GriefPrevention")
        };
    }

    @Override
    public String id() {
        return "griefprevention";
    }
}
