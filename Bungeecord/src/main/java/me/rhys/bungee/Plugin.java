package me.rhys.bungee;

import lombok.Getter;
import lombok.SneakyThrows;
import me.rhys.bungee.config.ConfigLoader;
import me.rhys.bungee.config.ConfigValues;
import me.rhys.bungee.listener.PluginMessageListener;
import me.rhys.bungee.redis.RedisManager;
import me.rhys.bungee.user.UserManager;

@Getter
public class Plugin extends net.md_5.bungee.api.plugin.Plugin {

    @Getter private static Plugin instance;

    private final ConfigLoader configLoader = new ConfigLoader();
    private final ConfigValues configValues = new ConfigValues();
    private final RedisManager redisManager = new RedisManager();
    private final UserManager userManager = new UserManager();

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;
        this.configLoader.init();
        this.redisManager.setup();
        getProxy().getPluginManager().registerListener(this, new PluginMessageListener());
    }

    @Override
    public void onDisable() {
        this.redisManager.shutdown();
    }
}
