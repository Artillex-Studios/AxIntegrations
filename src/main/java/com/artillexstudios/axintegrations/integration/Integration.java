package com.artillexstudios.axintegrations.integration;

import com.artillexstudios.axintegrations.plugin.RequiredPlugin;

public interface Integration {

    default boolean canRegister() {
        for (RequiredPlugin requiredPlugin : this.requiredPlugins()) {
            if (!requiredPlugin.anyLoaded()) {
                return false;
            }
        }

        return true;
    }

    default void register() {

    }

    RequiredPlugin[] requiredPlugins();

    String id();
}
