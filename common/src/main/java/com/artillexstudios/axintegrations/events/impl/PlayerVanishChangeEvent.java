package com.artillexstudios.axintegrations.events.impl;

import com.artillexstudios.axintegrations.Integration;
import org.bukkit.entity.Player;

public class PlayerVanishChangeEvent extends PlayerEvent {
    private final boolean newState;

    public PlayerVanishChangeEvent(Integration integration, Player player, boolean newState) {
        super(integration, player);
        this.newState = newState;
    }

    public boolean getNewState() {
        return newState;
    }
}
