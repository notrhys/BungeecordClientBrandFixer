package me.rhys.bungee.user;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

@Getter
public class UserManager {
    private final Map<UUID, User> userMap = new WeakHashMap<>();

    public void addUser(UUID uuid) {
        this.userMap.put(uuid, new User(uuid));
    }

    public User getUser(UUID uuid) {
        return this.userMap.get(uuid);
    }

    public void remove(UUID uuid) {
        this.userMap.remove(uuid);
    }
}

