package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ProtectionIntegration;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.api.user.User;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.lists.Flags;

public class BentoBoxProtection extends ProtectionIntegration {
    private BentoBox api;

    public BentoBoxProtection() {
        super("BentoBox");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("world.bentobox.bentobox.BentoBox");
    }

    @Override
    public boolean setup() {
        api = BentoBox.getInstance();
        if (api == null) return false;
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @Override
    public boolean canPlace(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, Flags.PLACE_BLOCKS);
    }

    @Override
    public boolean canBreak(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, Flags.BREAK_BLOCKS);
    }

    @Override
    public boolean canInteract(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, Flags.PLACE_BLOCKS);
    }

    @Override
    public boolean canOpenContainer(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, Flags.CHEST);
    }

    @Override
    public boolean canPvP(@NotNull Player player, @NotNull Location location) {
        return hasPermission(player, location, getPvPFlag(location.getWorld()));
    }

    private boolean hasPermission(Player player, Location location, Flag flag) {
        Island island = api.getIslandsManager().getIslandAt(location).orElse(null);
        if (island == null) return true;
        return island.isAllowed(User.getInstance(player.getUniqueId()), flag);
    }

    private Flag getPvPFlag(World world) {
        return switch (world.getEnvironment()) {
            case NETHER -> Flags.PVP_NETHER;
            case THE_END -> Flags.PVP_END;
            default -> Flags.PVP_OVERWORLD;
        };
    }
}
