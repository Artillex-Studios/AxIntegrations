package com.artillexstudios.axintegrations.functions;

@FunctionalInterface
public interface EnableFunction {

    boolean isEnabled(String name);
}
