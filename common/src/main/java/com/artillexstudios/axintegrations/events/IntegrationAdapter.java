package com.artillexstudios.axintegrations.events;

import com.artillexstudios.axintegrations.events.impl.VanishEvent;

public abstract class IntegrationAdapter {

    public abstract void onVanish(VanishEvent event);
}
