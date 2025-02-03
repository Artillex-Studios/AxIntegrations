package com.artillexstudios.axintegrations.integration.protection.implementation;

import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;

import java.util.Optional;

public final class BentoBoxProtectionIntegration implements ProtectionIntegration {

    @Override
    public boolean canBuild(Player player, Location location) {
        Optional<Island> island = BentoBox.getInstance().getIslandsManager().getIslandAt(location);
        return island.map(value -> value.getMemberSet().stream().anyMatch(uuid -> {
            return player.getUniqueId().equals(uuid);
        })).orElse(true);

    }

    @Override
    public boolean canPlace(Player player, Location location) {
        Optional<Island> island = BentoBox.getInstance().getIslandsManager().getIslandAt(location);
        return island.map(value -> value.getMemberSet().stream().anyMatch(uuid -> {
            return player.getUniqueId().equals(uuid);
        })).orElse(true);
    }

    @Override
    public boolean canBreak(Player player, Location location) {
        Optional<Island> island = BentoBox.getInstance().getIslandsManager().getIslandAt(location);
        return island.map(value -> value.getMemberSet().stream().anyMatch(uuid -> {
            return player.getUniqueId().equals(uuid);
        })).orElse(true);
    }

    @Override
    public boolean canInteract(Player player, Location location) {
        Optional<Island> island = BentoBox.getInstance().getIslandsManager().getIslandAt(location);
        return island.map(value -> value.getMemberSet().stream().anyMatch(uuid -> {
            return player.getUniqueId().equals(uuid);
        })).orElse(true);
    }

    @Override
    public boolean canOpen(Player player, Location location) {
        Optional<Island> island = BentoBox.getInstance().getIslandsManager().getIslandAt(location);
        return island.map(value -> value.getMemberSet().stream().anyMatch(uuid -> {
            return player.getUniqueId().equals(uuid);
        })).orElse(true);
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("BentoBox")
        };
    }

    @Override
    public String id() {
        return "bentobox";
    }
}
