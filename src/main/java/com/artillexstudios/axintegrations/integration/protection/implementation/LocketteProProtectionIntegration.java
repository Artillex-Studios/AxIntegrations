package com.artillexstudios.axintegrations.integration.protection.implementation;

import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import me.crafter.mc.lockettepro.LocketteProAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class LocketteProProtectionIntegration implements ProtectionIntegration {

    @Override
    public boolean canBuild(Player player, Location location) {
        return true;
    }

    @Override
    public boolean canPlace(Player player, Location location) {
        return true;
    }

    @Override
    public boolean canBreak(Player player, Location location) {
        return true;
    }

    @Override
    public boolean canInteract(Player player, Location location) {
        return true;
    }

    @Override
    public boolean canOpen(Player player, Location location) {
        return LocketteProAPI.mayInterfere(location.getBlock(), player);
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("LockettePro")
        };
    }

    @Override
    public String id() {
        return "lockettepro";
    }
}
