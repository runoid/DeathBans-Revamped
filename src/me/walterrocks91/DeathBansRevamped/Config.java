package me.walterrocks91.DeathBansRevamped;

import me.walterrocks91.DeathBansRevamped.Main;
import me.walterrocks91.DeathBansRevamped.Manager;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.IOException;

public class Config {

    /*
     * Getting configuration files.
     */
    public static FileConfiguration getConfig(){
        return Manager.config;
    }

    public static FileConfiguration getBans(){
        return Manager.bans;
    }

    public static FileConfiguration getLives() {
        return Manager.lives;
    }

    public static FileConfiguration getTimer() {
        return Manager.timer;
    }

    public static void saveAll(){
        try{
            Manager.bans.save(Manager.bansF);
            Manager.lives.save(Manager.livesF);
            Manager.timer.save(Manager.timerF);
            Main.getInstance().saveConfig();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
