package com.artillexstudios.axintegrations.integrations.helpers;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.events.IntegrationEvents;
import com.artillexstudios.axintegrations.events.impl.PlayerVanishChangeEvent;
import com.artillexstudios.axintegrations.types.VanishIntegration;
import de.myzelyam.api.vanish.PlayerVanishStateChangeEvent;
import de.myzelyam.api.vanish.VanishAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class SuperPremiumVanishHelper extends VanishIntegration implements Listener {

    public SuperPremiumVanishHelper(String name) {
        super(name);
    }

    @Override
    public boolean canLoad() {
        if (Bukkit.getPluginManager().getPlugin(name) == null) return false;
        return ClassUtils.INSTANCE.classExists("de.myzelyam.api.vanish.VanishAPI");
    }

    @Override
    public boolean setup() {
        registerListener();
        return true;
    }

    public void disable() {
        unregisterListener();
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChange(PlayerVanishStateChangeEvent event) {
        Player player = Bukkit.getPlayer(event.getUUID());
        if (player == null) return;
        IntegrationEvents.callEvent(new PlayerVanishChangeEvent(this, player, event.isVanishing()));
    }

    @Override
    public boolean isVanished(@NotNull Player player) {
        return VanishAPI.isInvisible(player);
    }

    @Override
    public void showPlayer(@NotNull Player player) {
        VanishAPI.showPlayer(player);
    }

    @Override
    public void hidePlayer(@NotNull Player player) {
        VanishAPI.hidePlayer(player);
    }

    @Override
    public boolean canSee(@NotNull Player viewer, @NotNull Player viewed) {
        return VanishAPI.canSee(viewer, viewed);
    }
    }
