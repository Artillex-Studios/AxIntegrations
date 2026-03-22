package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.ProtectionIntegration;
import com.plotsquared.core.PlotSquared;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotArea;
import com.plotsquared.core.plot.world.PlotAreaManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlotSquaredProtection extends ProtectionIntegration {
    private PlotSquared api;
    private PlotAreaManager manager;

    public PlotSquaredProtection() {
        super("PlotSquared");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.plotsquared.core.PlotSquared");
    }

    @Override
    public boolean setup() {
        api = PlotSquared.get();
        manager = api.getPlotAreaManager();
        return true;
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
        com.plotsquared.core.location.Location plotLocation = com.plotsquared.core.location.Location.at(
                location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ()
        );
        PlotArea plotArea = manager.getPlotArea(plotLocation);
        if (plotArea == null) return true; // not plot world
        Plot plot = plotArea.getPlotAbs(plotLocation);
        if (plot == null) return false; // road
        return plot.isAdded(player.getUniqueId()) || plot.isOwner(player.getUniqueId());
    }
}
