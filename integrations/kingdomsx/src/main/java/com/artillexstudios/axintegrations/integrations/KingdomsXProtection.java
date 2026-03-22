package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ProtectionIntegration;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kingdoms.constants.group.Kingdom;
import org.kingdoms.constants.land.Land;

public class KingdomsXProtection extends ProtectionIntegration {

    public KingdomsXProtection() {
        super("KingdomsX");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("org.kingdoms.constants.group.Kingdom");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean canPlace(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location);
    }

    @Override
    public boolean canBreak(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location);
    }

    @Override
    public boolean canInteract(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location);
    }

    @Override
    public boolean canOpenContainer(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location);
    }

    private boolean hasPermission(Player player, Location location) {
        Land land = Land.getLand(location);
        if (land == null) return true;
        Kingdom kingdom = land.getKingdom();
        if (kingdom == null) return true;
        return kingdom.isMember(player);
    }
}
