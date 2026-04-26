package com.artillexstudios.axintegrations;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class Integration {
    protected final String name;
    protected final IntegrationType type;

    protected Integration(@NotNull String name, @NotNull IntegrationType type) {
        this.name = name;
        this.type = type;
    }

    /**
     * this is the integration plugin's exact name
     */
    @NotNull
    public final String getName() {
        return name;
    }

    /**
     * used in messages, might have more information than just the plugin's name
     */
    @NotNull
    public String getFormattedName() {
        return getName();
    }

    @NotNull
    public IntegrationType getType() {
        return type;
    }

    /**
     * can the integration be loaded? make sure to check if the plugin it depends on is loaded using {@link com.artillexstudios.axapi.reflection.ClassUtils#classExists(String)}
     * or using {@link Bukkit#getPluginManager()#isPluginEnabled(String)}
     */
    public abstract boolean canLoad();

    public boolean isBuiltin() {
        return false;
    }

    public boolean setup() {
        return true;
    }

    public void disable() {

    }

    public void registerListener() {
        if (!(this instanceof Listener listener)) return;
        Bukkit.getServer().getPluginManager().registerEvents(listener, IntegrationManager.getPlugin());
    }

    public void unregisterListener() {
        if (!(this instanceof Listener listener)) return;
        HandlerList.unregisterAll(listener);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Integration that = (Integration) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
