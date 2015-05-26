package me.walterrocks91.DeathBansRevamped;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class Manager {

    protected static File bansF = new File(Main.getInstance().getDataFolder(), "bans.yml");
    protected static File livesF = new File(Main.getInstance().getDataFolder(), "lives.yml");
    protected static File timerF = new File(Main.getInstance().getDataFolder(), "timer.yml");
    protected static File exemptF = new File(Main.getInstance().getDataFolder(), "exempt.yml");
    protected static FileConfiguration bans = YamlConfiguration.loadConfiguration(bansF);
    protected static FileConfiguration lives = YamlConfiguration.loadConfiguration(livesF);
    protected static FileConfiguration timer = YamlConfiguration.loadConfiguration(timerF);
    protected static FileConfiguration exempt = YamlConfiguration.loadConfiguration(exemptF);
    protected static FileConfiguration config;

    protected static void setup(Plugin p){
        try {
            p.getConfig().options().copyDefaults(true);
            if (!p.getDataFolder().exists()) p.getDataFolder().mkdir();
            System.out.println("Config checks / Generation starting.");
            if (bansF == null) bansF = new File(p.getDataFolder(), "bans.yml");
            if (!bansF.exists()) bansF.createNewFile();
            if (bans == null) bans = YamlConfiguration.loadConfiguration(bansF);
            System.out.println("Bans.yml check / Generation completed.");
            if (livesF == null) livesF = new File(p.getDataFolder(), "lives.yml");
            if (!livesF.exists()) livesF.createNewFile();
            if (lives == null) lives = YamlConfiguration.loadConfiguration(livesF);
            System.out.println("Lives.yml check / Generation completed.");
            if (timerF == null) timerF = new File(p.getDataFolder(), "timer.yml");
            if (!timerF.exists()) timerF.createNewFile();
            if (timer == null) timer = YamlConfiguration.loadConfiguration(timerF);
            System.out.println("Timer.yml check / Generation completed.");
            if (exemptF == null) exemptF = new File(p.getDataFolder(), "exempt.yml");
            if (!exemptF.exists()) exemptF.createNewFile();
            if (exempt == null) exempt = YamlConfiguration.loadConfiguration(exemptF);
            System.out.println("Exempt.yml check / Generation completed.");
            config = p.getConfig();
            System.out.println("Config.yml check / Generation completed.");
            Config.saveAll();
            System.out.println("Config checks / Generation completed.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
