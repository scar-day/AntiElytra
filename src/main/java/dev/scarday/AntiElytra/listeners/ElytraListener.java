package dev.scarday.AntiElytra.listeners;

import dev.scarday.AntiElytra.util.ColorUtility;
import dev.scarday.AntiElytra.configuration.Configuration;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ElytraListener implements Listener {
    Configuration configuration;

    @EventHandler
    public void onToggleGlide(EntityToggleGlideEvent e) {
        if (!(e.getEntity() instanceof Player player)) return;
        String world = player.getWorld().getName();

        if (!configuration.getWorlds().contains(world)) return;
        ItemStack chest = player.getInventory().getChestplate();
        if (chest == null || chest.getType() != Material.ELYTRA) return;

        if (e.isGliding()) {
            e.setCancelled(true);
            player.setGliding(false);
            player.sendActionBar(ColorUtility.colorize(configuration.getMessages().getNoElytra()));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onUseFirework(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR &&
                event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

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
