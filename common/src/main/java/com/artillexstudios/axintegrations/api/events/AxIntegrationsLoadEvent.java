package com.artillexstudios.axintegrations.api.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * called once when the integration manager is loaded
 * after this event it is no longer possible to register any new integrations
 * it is recommended to register your integrations by listening to this event
 */
public class AxIntegrationsLoadEvent extends Event {
    private static final HandlerList handlerList = new HandlerList();

    public AxIntegrationsLoadEvent() {
        super(!Bukkit.isPrimaryThread());
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
