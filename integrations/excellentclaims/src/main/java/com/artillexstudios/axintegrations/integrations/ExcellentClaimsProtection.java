package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ProtectionIntegration;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.jetbrains.annotations.NotNull;
import su.nightexpress.excellentclaims.ClaimsAPI;
import su.nightexpress.excellentclaims.claim.ClaimManager;

public class ExcellentClaimsProtection extends ProtectionIntegration {
    private ClaimManager manager;

    public ExcellentClaimsProtection() {
        super("ExcellentEconomy");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("su.nightexpress.excellentclaims.ClaimsAPI");
    }

    @Override
    public boolean setup() {
        manager = ClaimsAPI.getClaimManager();
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean canPlace(@NotNull Player player, @NotNull Location location) {
        return manager.canBuild(player, location);
    }

    @Override
    public boolean canBreak(@NotNull Player player, @NotNull Location location) {
        return manager.canBreak(player, location);
    }

    @Override
    public boolean canInteract(@NotNull Player player, @NotNull Location location) {
        return manager.canUseBlock(player, location.getBlock(), Action.RIGHT_CLICK_BLOCK);
    }

    @Override
    public boolean canOpenContainer(@NotNull Player player, @NotNull Location location) {
        return manager.canUseBlock(player, location.getBlock(), Action.RIGHT_CLICK_BLOCK);
    }
}
