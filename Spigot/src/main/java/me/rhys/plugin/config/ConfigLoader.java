package me.rhys.plugin.config;

import me.rhys.plugin.Plugin;

public class ConfigLoader {
    public void load() {
        Plugin.getInstance().getConfig().options().copyDefaults(true);
        Plugin.getInstance().saveConfig();

        Plugin.getInstance().getConfigValues().setRedisIP(Plugin.getInstance().getConfig()
                .getString("Redis.IP"));

        Plugin.getInstance().getConfigValues().setRedisPort(Plugin.getInstance().getConfig()
                .getInt("Redis.port"));

        Plugin.getInstance().getConfigValues().setRedisPassword(Plugin.getInstance().getConfig()
                .getString("Redis.password"));

        Plugin.getInstance().getConfigValues().setRedisChannel(Plugin.getInstance().getConfig()
                .getString("Redis.channel"));
    }
}
