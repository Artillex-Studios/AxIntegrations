package com.artillexstudios.axintegrations.events.impl;

import com.artillexstudios.axintegrations.Integration;
import org.bukkit.entity.Player;

public abstract class PlayerEvent extends IntegrationEvent {
    private final Player player;

    public PlayerEvent(Integration integration, Player player) {
        super(integration);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
