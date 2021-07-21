package me.rhys.bungee.config;

import me.rhys.bungee.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigLoader {

    private final String configName = "config.yml";

    public void init() throws IOException {
        this.create();
        this.load();
    }

    void load() throws IOException {
        Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class)
                .load(new File(Plugin.getInstance().getDataFolder(), this.configName));

        Plugin.getInstance().getConfigValues().setRedisIP(configuration.getString("Redis.IP"));
        Plugin.getInstance().getConfigValues().setRedisPort(configuration.getInt("Redis.port"));
        Plugin.getInstance().getConfigValues().setRedisPassword(configuration.getString("Redis.password"));
        Plugin.getInstance().getConfigValues().setRedisChannel(configuration.getString("Redis.channel"));
    }

    void create() {
        if (!Plugin.getInstance().getDataFolder().exists()) {
            Plugin.getInstance().getDataFolder().mkdir();
        }

        File file = new File(Plugin.getInstance().getDataFolder(), this.configName);

        if (!file.exists()) {
            try (InputStream inputStream = Plugin.getInstance().getResourceAsStream(this.configName)) {
                Files.copy(inputStream, file.toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
