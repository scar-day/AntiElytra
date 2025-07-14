package dev.scarday.AntiElytra;

import lombok.val;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class WorldListener implements Listener {
    Configuration configuration;

    public WorldListener(AntiElytra plugin) {
        this.configuration = plugin.getConfiguration();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName();

        if (!configuration.getWorlds().contains(worldName)) return;

        val message = configuration.getMessages().getDisabledWorld();
        if (!message.isEmpty()) {
            player.sendActionBar(ColorUtility.colorize(message));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName();

        if (!configuration.getWorlds().contains(worldName)) return;

        val message = configuration.getMessages().getDisabledWorld();
        if (!message.isEmpty()) {
            player.sendActionBar(ColorUtility.colorize(message));
        }
    }
}
