package dev.scarday.AntiElytra.listeners;

import dev.scarday.AntiElytra.util.ColorUtility;
import dev.scarday.AntiElytra.configuration.Configuration;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WorldListener implements Listener {
    Configuration configuration;

    @EventHandler(priority = EventPriority.NORMAL)
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName();

        if (!configuration.getWorlds().contains(worldName)) return;

        sendActionBar(player);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName();

        if (!configuration.getWorlds().contains(worldName)) return;

        sendActionBar(player);
    }

    private void sendActionBar(@NotNull Player player) {
        val message = configuration.getMessages().getDisabledWorld();
        if (!message.isEmpty()) {
            player.sendActionBar(ColorUtility.colorize(message));
        }
    }
}
