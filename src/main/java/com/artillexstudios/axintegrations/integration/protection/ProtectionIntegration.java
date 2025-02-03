package com.artillexstudios.axintegrations.integration.protection;

import com.artillexstudios.axintegrations.integration.Integration;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface ProtectionIntegration extends Integration {

    boolean canBuild(Player player, Location location);

    boolean canPlace(Player player, Location location);

    boolean canBreak(Player player, Location location);

    boolean canInteract(Player player, Location location);

    boolean canOpen(Player player, Location location);
}
