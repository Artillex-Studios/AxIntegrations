package com.artillexstudios.axintegrations.events;

import com.artillexstudios.axintegrations.events.impl.VanishEvent;

public abstract class IntegrationAdapter {

    public final void register() {
        IntegrationEvents.register(this);
    }

    public final void unregister() {
        IntegrationEvents.unregister(this);
    }

    public void onVanish(VanishEvent event) {}
}
