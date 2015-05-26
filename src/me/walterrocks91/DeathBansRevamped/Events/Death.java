package me.walterrocks91.DeathBansRevamped.Events;

import me.walterrocks91.DeathBansRevamped.API.API;
import me.walterrocks91.DeathBansRevamped.Config;
import me.walterrocks91.DeathBansRevamped.User.DBUser;
import me.walterrocks91.DeathBansRevamped.User.DBUsers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (DBUser u : DBUsers.getAllUsers()) {
            if (u.getPlayer().getName().equalsIgnoreCase(e.getEntity().getName())) {
                if (Config.getConfig().getBoolean("lightning-strike"))
                    u.getPlayer().getWorld().strikeLightningEffect(u.getPlayer().getLocation());
                if (API.getLives(u.getPlayer().getName()) > 0) {
                    API.changeLives(u.getPlayer().getName(), -1);
                    API.sendMessage(u.getPlayer(), "&cYou have lost &e1 life. &cRemaining lives: &e" + API.getLives(u.getPlayer().getName()));
                    return;
                }
                u.ban();
            }
        }
    }
}