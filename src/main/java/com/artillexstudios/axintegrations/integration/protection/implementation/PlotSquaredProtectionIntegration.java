package com.artillexstudios.axintegrations.integration.protection.implementation;

import com.artillexstudios.axintegrations.integration.protection.ProtectionIntegration;
import com.artillexstudios.axintegrations.plugin.RequiredPlugin;
import com.plotsquared.core.PlotSquared;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotArea;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class PlotSquaredProtectionIntegration implements ProtectionIntegration {

    @Override
    public boolean canBuild(Player player, Location location) {
        final com.plotsquared.core.location.Location plotLocation = com.plotsquared.core.location.Location.at(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
        final PlotArea plotArea = PlotSquared.get().getPlotAreaManager().getPlotArea(plotLocation);
        if (plotArea == null) {
            return true;
        }

        final Plot plot = plotArea.getPlot(plotLocation);
        if (plot == null) {
            return true;
        }

        return plot.isAdded(player.getUniqueId());
    }

    @Override
    public boolean canPlace(Player player, Location location) {
        final com.plotsquared.core.location.Location plotLocation = com.plotsquared.core.location.Location.at(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
        final PlotArea plotArea = PlotSquared.get().getPlotAreaManager().getPlotArea(plotLocation);
        if (plotArea == null) {
            return true;
        }

        final Plot plot = plotArea.getPlot(plotLocation);
        if (plot == null) {
            return true;
        }

        return plot.isAdded(player.getUniqueId());
    }

    @Override
    public boolean canBreak(Player player, Location location) {
        final com.plotsquared.core.location.Location plotLocation = com.plotsquared.core.location.Location.at(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
        final PlotArea plotArea = PlotSquared.get().getPlotAreaManager().getPlotArea(plotLocation);
        if (plotArea == null) {
            return true;
        }

        final Plot plot = plotArea.getPlot(plotLocation);
        if (plot == null) {
            return true;
        }

        return plot.isAdded(player.getUniqueId());
    }

    @Override
    public boolean canInteract(Player player, Location location) {
        final com.plotsquared.core.location.Location plotLocation = com.plotsquared.core.location.Location.at(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
        final PlotArea plotArea = PlotSquared.get().getPlotAreaManager().getPlotArea(plotLocation);
        if (plotArea == null) {
            return true;
        }

        final Plot plot = plotArea.getPlot(plotLocation);
        if (plot == null) {
            return true;
        }

        return plot.isAdded(player.getUniqueId());
    }

    @Override
    public boolean canOpen(Player player, Location location) {
        final com.plotsquared.core.location.Location plotLocation = com.plotsquared.core.location.Location.at(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
        final PlotArea plotArea = PlotSquared.get().getPlotAreaManager().getPlotArea(plotLocation);
        if (plotArea == null) {
            return true;
        }

        final Plot plot = plotArea.getPlot(plotLocation);
        if (plot == null) {
            return true;
        }

        return plot.isAdded(player.getUniqueId());
    }

    @Override
    public RequiredPlugin[] requiredPlugins() {
        return new RequiredPlugin[]{
                RequiredPlugin.of("PlotSquared")
        };
    }

    @Override
    public String id() {
        return "plotsquared";
    }
}
