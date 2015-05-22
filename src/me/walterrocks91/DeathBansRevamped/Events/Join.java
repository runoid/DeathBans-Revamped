package me.walterrocks91.DeathBansRevamped.Events;

import me.walterrocks91.DeathBansRevamped.User.DBUsers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        DBUsers.registerDBUser(e.getPlayer());
    }

}
