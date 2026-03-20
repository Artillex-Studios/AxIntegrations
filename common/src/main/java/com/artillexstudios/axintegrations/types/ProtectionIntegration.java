package com.artillexstudios.axintegrations.types;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationType;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Rules
 * - every method must return true if the player has bypass permission
 */
public abstract class ProtectionIntegration extends Integration {

    public ProtectionIntegration(String name) {
        super(name, IntegrationType.PROTECTION);
    }

    public abstract boolean canPlace(Player player, Location location);

    public abstract boolean canBreak(Player player, Location location);

    public abstract boolean canInteract(Player player, Location location);

    public abstract boolean canOpenContainer(Player player, Location location);

    public abstract boolean canPvP(Player player, Location location);
}
