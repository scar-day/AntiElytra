package dev.scarday.AntiElytra;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class ElytraListener implements Listener {
    Configuration configuration;

    public ElytraListener(AntiElytra plugin) {
        configuration = plugin.getConfiguration();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onElytraGlide(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName();

        if (!configuration.getWorlds().contains(worldName)) return;

        ItemStack chestplate = player.getInventory().getChestplate();
        if (chestplate == null || chestplate.getType() != Material.ELYTRA) return;

        if (player.isGliding()) {
            player.setGliding(false);
            player.sendActionBar(ColorUtility.colorize(configuration.getMessages().getNoElytra()));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onUseFirework(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName();

        if (!configuration.getWorlds().contains(worldName)) return;

        ItemStack chestplate = player.getInventory().getChestplate();
        if (chestplate == null || chestplate.getType() != Material.ELYTRA) return;

        ItemStack usedItem = null;

        if (event.getHand() == EquipmentSlot.HAND) {
            usedItem = player.getInventory().getItemInMainHand();
        } else if (event.getHand() == EquipmentSlot.OFF_HAND) {
            usedItem = player.getInventory().getItemInOffHand();
        }

        if (usedItem == null || usedItem.getType() != Material.FIREWORK_ROCKET) return;

        event.setCancelled(true);
    }
}
