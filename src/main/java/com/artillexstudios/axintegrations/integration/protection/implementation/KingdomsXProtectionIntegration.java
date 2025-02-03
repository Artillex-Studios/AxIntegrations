package com.artillexstudios.axintegrations.integration.protection.implementation;

import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.kingdoms.constants.land.Land;
import org.kingdoms.constants.player.KingdomPlayer;

public final class KingdomsXProtectionIntegration implements ProtectionIntegration {

    @Override
    public boolean canBuild(Player player, Location location) {
        return this.canDo(player, location);
    }

    @Override
    public boolean canPlace(Player player, Location location) {
        return this.canDo(player, location);
    }

    @Override
    public boolean canBreak(Player player, Location location) {
        return this.canDo(player, location);
    }

    @Override
    public boolean canInteract(Player player, Location location) {
        return this.canDo(player, location);
    }

    @Override
    public boolean canOpen(Player player, Location location) {
        return this.canDo(player, location);
    }

    private boolean canDo(Player player, Location location) {
        KingdomPlayer kingdomPlayer = KingdomPlayer.getKingdomPlayer(player);
        Land land = Land.getLand(location);
        if (land == null) {
            return true;
        }

        return land.getKingdom().isMember(kingdomPlayer);
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("Kingdoms")
        };
    }

    @Override
    public String id() {
        return "kingdomsx";
    }
}
