package me.walterrocks91.DeathBansRevamped.User;

import me.walterrocks91.DeathBansRevamped.API.API;
import me.walterrocks91.DeathBansRevamped.Config;
import me.walterrocks91.DeathBansRevamped.Events.Custom.PlayerDeathbannedEvent;
import me.walterrocks91.DeathBansRevamped.Main;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DBUser {

    private Player player;

    private int banTask = -1;

    protected DBUser(Player p){
        player = p;
        DBUsers.addUser(this);
    }

    public Player getPlayer(){
        return player;
    }

    public void ban(){
        if (Config.getExempt().getStringList("exempt").contains(player.getUniqueId().toString())) return;
        PlayerDeathbannedEvent event = new PlayerDeathbannedEvent(DBUsers.getFromPlayer(player));
        Main.getInstance().getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        final String u = player.getUniqueId().toString();
        List<String> list = Config.getBans().getStringList("banned");
        if(list == null){
            list = new ArrayList<String>();
        }
        list.add(u);
        Config.getBans().set("banned", list);
        Config.getTimer().set(u, API.getBanLength());
        Config.saveAll();
        banTask = Main.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
            public void run(){
                if(Config.getTimer().getInt(u) <= 0){
                    Main.getInstance().getServer().getScheduler().cancelTask(banTask);
                    Config.getTimer().set(u, null);
                    List<String> uuids = Config.getBans().getStringList("banned");
                    uuids.remove(u);
                    Config.getBans().set("banned", uuids);
                    Config.saveAll();
                    return;
                }
                Config.getTimer().set(u, Config.getTimer().getInt(u)-1);
                Config.saveAll();
            }
        }, 0, 1*20);
        player.kickPlayer(API.parseColoredString(API.getKickReason()));
    }
}
