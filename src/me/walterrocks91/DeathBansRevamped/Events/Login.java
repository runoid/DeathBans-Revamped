package me.walterrocks91.DeathBansRevamped.Events;

import me.walterrocks91.DeathBansRevamped.API.API;
import me.walterrocks91.DeathBansRevamped.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class Login implements Listener{
    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        String u = e.getPlayer().getUniqueId().toString();
        if (Config.getBans().getStringList("banned").contains(u))
            e.disallow(PlayerLoginEvent.Result.KICK_BANNED, API.parseColoredString(API.getBanReason().replaceAll("%remaining%", API.parseTime(Config.getTimer().getInt(u)))));
    }
}