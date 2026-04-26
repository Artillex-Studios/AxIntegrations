package com.artillexstudios.axintegrations.events.impl;

import com.artillexstudios.axintegrations.Integration;
import org.bukkit.entity.Player;

public class VanishEvent extends PlayerEvent {
    private final boolean newState;

    public VanishEvent(Integration integration, Player player, boolean newState) {
        super(integration, player);
        this.newState = newState;
    }

    public boolean getNewState() {
        return newState;
    }
}
