package me.rhys.bungee.config;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConfigValues {
    private String redisIP, redisPassword, redisChannel;
    private int redisPort;
}
