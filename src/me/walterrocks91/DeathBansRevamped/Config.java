package me.walterrocks91.DeathBansRevamped;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

public class Config {

    /*
     * Config:
     *   ban-length: 90
     *   timeframe: minute
     *   kick-reason: '&cYou have died, and have now been deathbanned!'
     *   ban-reason: '&cYou have been deathbanned for %time%.'
     */

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

    public static FileConfiguration getExempt() {
        return Manager.exempt;
    }

    public static void saveAll(){
        try{
            Manager.bans.save(Manager.bansF);
            Manager.lives.save(Manager.livesF);
            Manager.timer.save(Manager.timerF);
            Manager.exempt.save(Manager.exemptF);
            Main.getInstance().saveConfig();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
