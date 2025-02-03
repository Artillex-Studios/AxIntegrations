package com.artillexstudios.axintegrations.integration.prices;

import com.artillexstudios.axintegrations.integration.Integration;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface PriceIntegration extends Integration {

    Double getPrice(ItemStack itemStack);

    Double getPrice(ItemStack itemStack, long amount);

    Double getPrice(ItemStack itemStack, OfflinePlayer player);

    Double getPrice(ItemStack itemStack, long amount, OfflinePlayer player);
}
