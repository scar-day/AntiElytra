package dev.scarday.AntiElytra;

import dev.scarday.AntiElytra.configuration.Configuration;
import dev.scarday.AntiElytra.listeners.ElytraListener;
import dev.scarday.AntiElytra.listeners.WorldListener;
import dev.scarday.AntiElytra.util.ColorUtility;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class AntiElytra extends JavaPlugin {
    @Getter
    private Configuration configuration;

    private final PluginManager pm = getServer().getPluginManager();

    @Override
    public void onEnable() {
        loadConfiguration();
        loadListeners();
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

    private void loadConfiguration() {
        try {
            this.configuration = ConfigManager.create(Configuration.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(this.getDataFolder(), "config.yml"));
                it.saveDefaults();
                it.load(true);
            });

            getComponentLogger().info(ColorUtility.colorize("<green>Configuration successfully loaded."));
        } catch (Exception exception) {
            getComponentLogger().error(ColorUtility.colorize("<red>An exception occurred while loading configuration."), exception);
            pm.disablePlugin(this);
        }
    }

    private void loadListeners() {
        pm.registerEvents(new ElytraListener(this.configuration), this);
        pm.registerEvents(new WorldListener(this.configuration), this);
    }
}
