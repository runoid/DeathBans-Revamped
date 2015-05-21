package me.walterrocks91.DeathBansRevamped.User;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DBUsers {

    protected static List<DBUser> allUsers = new ArrayList<DBUser>();

    protected static void addUser(DBUser user){
        allUsers.add(user);
    }

    public static DBUser getFromPlayer(Player p){
        for(DBUser user : allUsers){
            if(user.getPlayer().getName().equalsIgnoreCase(p.getName())){
                return user;
            }
        }
        return null;
    }

    public static void registerDBUser(Player p){
        for(DBUser u : allUsers){
            if(u.getPlayer().getName().equalsIgnoreCase(p.getName())){
                return;
            }
        }
        addUser(getFromPlayer(p));
    }
}
