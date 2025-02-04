package com.artillexstudios.axintegrations.integration.protection.implementation;

import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.zcore.fperms.Access;
import com.massivecraft.factions.zcore.fperms.PermissableAction;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class SaberFactionsProtectionIntegration implements ProtectionIntegration {

    @Override
    public boolean canBuild(Player player, Location location) {
        final FLocation loc = FLocation.wrap(location);
        final FPlayer pl = FPlayers.getInstance().getByPlayer(player);
        return Board.getInstance().getFactionAt(loc).getAccess(pl, PermissableAction.BUILD) != Access.DENY;
    }

    @Override
    public boolean canPlace(Player player, Location location) {
        final FLocation loc = FLocation.wrap(location);
        final FPlayer pl = FPlayers.getInstance().getByPlayer(player);
        return Board.getInstance().getFactionAt(loc).getAccess(pl, PermissableAction.BUILD) != Access.DENY;
    }

    @Override
    public boolean canBreak(Player player, Location location) {
        final FLocation loc = FLocation.wrap(location);
        final FPlayer pl = FPlayers.getInstance().getByPlayer(player);
        return Board.getInstance().getFactionAt(loc).getAccess(pl, PermissableAction.DESTROY) != Access.DENY;
    }

    @Override
    public boolean canInteract(Player player, Location location) {
        final FLocation loc = FLocation.wrap(location);
        final FPlayer pl = FPlayers.getInstance().getByPlayer(player);
        return Board.getInstance().getFactionAt(loc).getAccess(pl, PermissableAction.BUTTON) != Access.DENY;
    }

    @Override
    public boolean canOpen(Player player, Location location) {
        final FLocation loc = FLocation.wrap(location);
        final FPlayer pl = FPlayers.getInstance().getByPlayer(player);
        return Board.getInstance().getFactionAt(loc).getAccess(pl, PermissableAction.CONTAINER) != Access.DENY;
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("Factions")
        };
    }

    @Override
    public String id() {
        return "saberfactions";
    }
}
