package me.walterrocks91.DeathBansRevamped.World;

import me.walterrocks91.DeathBansRevamped.Config;
import org.bukkit.World;

import java.util.List;

public class Worlds {

    public static boolean isInDeniedWorld(World paramWorld) {
        String w = paramWorld.getName();
        List<String> list = Config.getConfig().getStringList("denied-worlds");
        if (list == null) return false;
        if (list.isEmpty()) return false;
        for (String s : list) {
            return s.equalsIgnoreCase(w);
        }
        return false;
    }

}
