package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ProtectionIntegration;
import com.griefprevention.protection.ProtectionHelper;
import me.ryanhamshire.GriefPrevention.ClaimPermission;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GriefPreventionProtection extends ProtectionIntegration {

    public GriefPreventionProtection() {
        super("GriefPrevention");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.griefprevention.protection.ProtectionHelper");
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean canPlace(@NotNull Player player, @NotNull Location location) {
        return isAllowed(player, location, ClaimPermission.Build);
    }

    @Override
    public boolean canBreak(@NotNull Player player, @NotNull Location location) {
        return isAllowed(player, location, ClaimPermission.Build);
    }

    @Override
    public boolean canInteract(@NotNull Player player, @NotNull Location location) {
        return isAllowed(player, location, ClaimPermission.Build);
    }

    @Override
    public boolean canOpenContainer(@NotNull Player player, @NotNull Location location) {
        return isAllowed(player, location, ClaimPermission.Inventory);
    }

    private boolean isAllowed(Player player, Location location, ClaimPermission permission) {
        return ProtectionHelper.checkPermission(player, location, permission, null) == null;
    }
}
