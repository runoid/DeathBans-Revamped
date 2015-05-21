package me.walterrocks91.DeathBansRevamped.API;

import me.walterrocks91.DeathBansRevamped.Config;
import me.walterrocks91.DeathBansRevamped.Main;
import me.walterrocks91.DeathBansRevamped.Other.UUIDFetcher;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class API {

    protected static Main instance;

    public static void setInstance(Main main){
        instance = main;
    }

    public static Main getMain(){
        return instance;
    }

    private static String prefix = "";

    public API(){
        prefix = Config.getConfig().getString("prefix").replaceAll("&", "§");
    }

    public static void sendMessage(CommandSender sender, String msg){
        sender.sendMessage(prefix + msg.replaceAll("&", "§"));
    }

    public static boolean checkBan(String player){
        try {
            if (Config.getBans().getStringList("banned").contains(UUIDFetcher.getUUIDOf(player).toString())){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean checkBan(UUID uuid){
        if (Config.getBans().getStringList("banned").contains(uuid.toString())){
            return true;
        }
        return false;
    }
    public static String getKickReason(){
        return Config.getConfig().getString("kick-reason");
    }
    public static int getBanLength(){
        String tf = Config.getConfig().getString("timeframe");
        int multiplier = 0;
        if(tf.equalsIgnoreCase("second")){
            multiplier = 20;
        }else if(tf.equalsIgnoreCase("minute")){
            multiplier = 20*60;
        }else if(tf.equalsIgnoreCase("hour")){
            multiplier = 20*60*60;
        }else if(tf.equalsIgnoreCase("day")){
            multiplier = 20*60*60*24;
        }
        return Config.getConfig().getInt("ban-length")*multiplier;
    }
}
