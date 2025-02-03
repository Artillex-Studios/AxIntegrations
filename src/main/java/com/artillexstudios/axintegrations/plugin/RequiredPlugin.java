package com.artillexstudios.axintegrations.plugin;

import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.List;

public record RequiredPlugin(List<String> pluginNames) {

    public static RequiredPlugin of(String... plugins) {
        return new RequiredPlugin(Arrays.asList(plugins));
    }

    public boolean anyLoaded() {
        for (String pluginName : this.pluginNames) {
            if (Bukkit.getPluginManager().getPlugin(pluginName) != null) {
                return true;
            }
        }

        return false;
    }

    public boolean allLoaded() {
        for (String pluginName : this.pluginNames) {
            if (Bukkit.getPluginManager().getPlugin(pluginName) == null) {
                return false;
            }

        }

        return true;
    }
}
