package dev.scarday.AntiElytra;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import lombok.Getter;
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
    public void onDisable() {}

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
        pm.registerEvents(new ElytraListener(this), this);
        pm.registerEvents(new WorldListener(this), this);
    }
}
