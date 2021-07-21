package me.rhys.bungee.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class User {
    private UUID uuid;
    private String clientBrand = "vanilla";

    public User(UUID uuid) {
        this.uuid = uuid;
    }
}
