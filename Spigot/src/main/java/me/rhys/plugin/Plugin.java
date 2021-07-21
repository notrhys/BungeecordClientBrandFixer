package me.rhys.plugin;

import lombok.Getter;
import me.rhys.plugin.api.BrandFixAPI;
import me.rhys.plugin.config.ConfigLoader;
import me.rhys.plugin.config.ConfigValues;
import me.rhys.plugin.redis.RedisManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Plugin extends JavaPlugin {
    @Getter private static Plugin instance;

    private final ConfigValues configValues = new ConfigValues();
    private final ConfigLoader configLoader = new ConfigLoader();
    private final RedisManager redisManager = new RedisManager();

    @Override
    public void onEnable() {
        instance = this;
        this.configLoader.load();
        this.redisManager.setup();
    }

    @Override
    public void onDisable() {
        this.redisManager.shutdown();
    }
}
