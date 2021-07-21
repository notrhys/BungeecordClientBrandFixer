package me.rhys.bungee.redis;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.rhys.bungee.Plugin;
import me.rhys.bungee.user.User;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisManager {
    private final ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final Gson gson = new Gson();
    private Jedis jedis;

    public void setup() {
        try {
            this.jedis = new Jedis(
                    Plugin.getInstance().getConfigValues().getRedisIP(),
                    Plugin.getInstance().getConfigValues().getRedisPort()
            );

            this.jedis.auth(Plugin.getInstance().getConfigValues().getRedisPassword());
        } catch (Exception e) {
            Plugin.getInstance().getLogger().warning("Could not connect to redis : " + e.getMessage());
        }
    }

    public void pushData(User user) {
        if (this.jedis != null && this.jedis.isConnected()) {
            this.executorService.execute(() -> {

                Map<String, String> map = new HashMap<>();
                map.put("uuid", user.getUuid().toString());
                map.put("brand", user.getClientBrand());

                this.jedis.publish(Plugin.getInstance().getConfigValues().getRedisChannel(), this.gson.toJson(map));
                map.clear();
            });
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
