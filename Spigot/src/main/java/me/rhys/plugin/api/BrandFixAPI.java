package me.rhys.plugin.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BrandFixAPI {
    public static final Map<UUID, String> brandMap = new HashMap<>();

    public static String getClientBrand(UUID uuid) {
        return brandMap.getOrDefault(uuid, "vanilla");
    }
}
