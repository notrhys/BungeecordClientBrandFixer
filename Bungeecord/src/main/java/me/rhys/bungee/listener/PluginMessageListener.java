package me.rhys.bungee.listener;

import me.rhys.bungee.Plugin;
import me.rhys.bungee.user.User;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.nio.charset.StandardCharsets;

public class PluginMessageListener implements Listener {

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        Plugin.getInstance().getUserManager().remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onJoin(PostLoginEvent event) {
        Plugin.getInstance().getUserManager().addUser(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onMessage(PluginMessageEvent event) {
        if (event.getSender() instanceof ProxiedPlayer && event.getTag().equalsIgnoreCase("MC|Brand")) {
            ProxiedPlayer player = (ProxiedPlayer) event.getSender();
            User user = Plugin.getInstance().getUserManager().getUser(player.getUniqueId());

            if (user != null) {
                String data = new String(event.getData(), StandardCharsets.UTF_8)
                        .replaceAll("\\P{Print}", "");

                if (data.length() > 0) {
                    data = this.filterBrand(data);

                    if (data != null) {
                        user.setClientBrand(data);
                        Plugin.getInstance().getRedisManager().pushData(user);
                    }
                }
            }
        }
    }

    String filterBrand(String data) {
        return data.contains("PaperSpigot") ? null : data;
    }
}
