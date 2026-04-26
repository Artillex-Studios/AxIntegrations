package com.artillexstudios.axintegrations.events.impl;

import com.artillexstudios.axintegrations.Integration;

public class IntegrationEvent {
    private final Integration integration;

    public IntegrationEvent(Integration integration) {
        this.integration = integration;
    }

    public Integration getIntegration() {
        return integration;
    }
}
