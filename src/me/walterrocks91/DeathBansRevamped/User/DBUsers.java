package me.walterrocks91.DeathBansRevamped.User;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DBUsers {

    protected static List<DBUser> allUsers = new ArrayList<DBUser>();

    protected static void addUser(DBUser user){
        allUsers.add(user);
    }

    protected static void removeUser(DBUser user) { allUsers.remove(user); }

    public static DBUser getFromPlayer(Player p){
        for (DBUser user : allUsers)
            if (user.getPlayer().getName().equalsIgnoreCase(p.getName())) return user;
        return null;
    }

    public static void registerDBUser(Player p){
        for (DBUser u : allUsers)
            if (u.getPlayer().getName().equalsIgnoreCase(p.getName())) return;
        addUser(new DBUser(p));
        System.out.println("Registered DeathBans User " + p.getName());
    }

    public static void unregisterDBUser(DBUser user){
        if (allUsers.contains(user))
            allUsers.remove(user);
        System.out.println("Unregistered DeathBans User " + user.getPlayer().getName());
    }

    public static List<DBUser> getAllUsers() {
        return allUsers;
    }
}
