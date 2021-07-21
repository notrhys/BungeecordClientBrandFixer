package me.rhys.plugin.redis;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.rhys.plugin.Plugin;
import me.rhys.plugin.api.BrandFixAPI;
import org.bukkit.Bukkit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisManager {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Gson gson = new Gson();
    private Jedis jedis;

    public void setup() {
        try {
            this.jedis = new Jedis(
                    Plugin.getInstance().getConfigValues().getRedisIP(),
                    Plugin.getInstance().getConfigValues().getRedisPort()
            );

            this.jedis.auth(Plugin.getInstance().getConfigValues().getRedisPassword());

            this.executorService.execute(() -> this.jedis.subscribe(new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    if (message.length() > 3) {
                        JsonElement jsonElement = new JsonParser().parse(message);
                        JsonObject jsonObject = jsonElement.getAsJsonObject();

                        if (jsonObject.has("uuid") && jsonObject.has("brand")) {
                            String uuid = jsonObject.get("uuid").getAsString();
                            String clientBrand = jsonObject.get("brand").getAsString();

                            BrandFixAPI.brandMap.put(UUID.fromString(uuid), clientBrand);
                        }
                    }
                }
            }, Plugin.getInstance().getConfigValues().getRedisChannel()));
        } catch (Exception e) {
            Plugin.getInstance().getLogger().warning("Could not connect to redis : " + e.getMessage());
        }
    }

    public void shutdown() {
        if (this.jedis != null) {
            this.jedis.disconnect();
            this.jedis.close();
        }

        this.executorService.shutdownNow();
    }
}
