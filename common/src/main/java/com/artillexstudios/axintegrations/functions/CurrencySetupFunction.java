package com.artillexstudios.axintegrations.functions;

import org.jetbrains.annotations.Nullable;

import java.util.List;

@FunctionalInterface
public interface CurrencySetupFunction {

    @Nullable
    List<String> getCurrencyList(String name);
}
