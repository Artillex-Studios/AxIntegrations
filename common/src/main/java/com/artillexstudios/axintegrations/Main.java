package com.artillexstudios.axintegrations;

import java.util.List;

// this class is only used for testing!
public class Main {

    public static void main(String[] args) {
        IntegrationSetup.builder()
                .enableShopIntegrations(name -> {
                    return true;
                })
                .enableProtectionIntegrations(name -> {
                    return true;
                })
                .enableCurrencyIntegrations(name -> {
                    return true;
                }, name -> {
                    return List.of("coins", "money");
                })
                .setup();
    }
}
