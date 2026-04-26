package com.artillexstudios.axintegrations.integrations;

import com.artillexstudios.axapi.reflection.ClassUtils;
import com.artillexstudios.axintegrations.types.VanishIntegration;
import com.earth2me.essentials.IEssentials;
import com.earth2me.essentials.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EssentialsXVanish extends VanishIntegration {
    private IEssentials api;

    public EssentialsXVanish() {
        super("EssentialsX");
    }

    @Override
    public boolean canLoad() {
        return ClassUtils.INSTANCE.classExists("com.earth2me.essentials.IEssentials");
    }

    @Override
    public boolean setup() {
        api = (IEssentials) Bukkit.getPluginManager().getPlugin("Essentials");
        if (api == null) return false;
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return true;
    }


    @Override
    public boolean isVanished(@NotNull Player player) {
        User user = getUser(player);
        if (user == null) return false;
        return user.isVanished();
    }

    @Override
    public void showPlayer(@NotNull Player player) {
        User user = getUser(player);
        if (user == null) return;
        user.setVanished(false);
    }

    @Override
    public void hidePlayer(@NotNull Player player) {
        User user = getUser(player);
        if (user == null) return;
        user.setVanished(true);
    }

    @Override
    public boolean canSee(@NotNull Player viewer, @NotNull Player viewed) {
        User viewedUser = getUser(viewed);
        if (viewedUser == null) return true;
        return !viewedUser.isHiddenFrom(viewed);
    }

    @Nullable
    private User getUser(Player player) {
        return api.getUser(player);
    }
}
