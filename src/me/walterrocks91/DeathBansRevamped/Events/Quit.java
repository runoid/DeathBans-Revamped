package me.walterrocks91.DeathBansRevamped.Events;

import me.walterrocks91.DeathBansRevamped.User.DBUsers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        DBUsers.unregisterDBUser(DBUsers.getFromPlayer(e.getPlayer()));
    }

}
