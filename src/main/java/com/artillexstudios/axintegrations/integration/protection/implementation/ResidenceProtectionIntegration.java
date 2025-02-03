package com.artillexstudios.axintegrations.integration.protection.implementation;

import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import com.bekvon.bukkit.residence.containers.ResidencePlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class ResidenceProtectionIntegration implements ProtectionIntegration {

    @Override
    public boolean canBuild(Player player, Location location) {
        ResidencePlayer residencePlayer = ResidencePlayer.get(player);
        return residencePlayer.canPlaceBlock(location.getBlock(), false);
    }

    @Override
    public boolean canPlace(Player player, Location location) {
        ResidencePlayer residencePlayer = ResidencePlayer.get(player);
        return residencePlayer.canPlaceBlock(location.getBlock(), false);
    }

    @Override
    public boolean canBreak(Player player, Location location) {
        ResidencePlayer residencePlayer = ResidencePlayer.get(player);
        return residencePlayer.canBreakBlock(location.getBlock(), false);
    }

    @Override
    public boolean canInteract(Player player, Location location) {
        ResidencePlayer residencePlayer = ResidencePlayer.get(player);
        return residencePlayer.canPlaceBlock(location.getBlock(), false);
    }

    @Override
    public boolean canOpen(Player player, Location location) {
        ResidencePlayer residencePlayer = ResidencePlayer.get(player);
        return residencePlayer.canPlaceBlock(location.getBlock(), false);
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("Residence")
        };
    }

    @Override
    public String id() {
        return "residence";
    }
}
