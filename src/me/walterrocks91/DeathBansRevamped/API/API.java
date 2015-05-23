package me.walterrocks91.DeathBansRevamped.API;

import me.walterrocks91.DeathBansRevamped.Config;
import me.walterrocks91.DeathBansRevamped.Main;
import me.walterrocks91.DeathBansRevamped.Other.UUIDFetcher;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
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
        sender.sendMessage(prefix + parseColoredString(msg));
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
    public static boolean unban(String player){
        try {
            String uuid = UUIDFetcher.getUUIDOf(player).toString();
            if(checkBan(player)){
                List<String> list = Config.getBans().getStringList("banned");
                list.remove(uuid);
                Config.getBans().set("banned", list);
                Config.saveAll();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean unban(UUID uuid){
        if(checkBan(uuid)) {
            List<String> list = Config.getBans().getStringList("banned");
            list.remove(uuid.toString());
            Config.getBans().set("banned", list);
            Config.saveAll();
            return true;
        }
        return false;
    }
    public static String getKickReason(){
        return Config.getConfig().getString("kick-reason");
    }

    public static String getBanReason() { return Config.getConfig().getString("ban-reason"); }

    public static int getBanLength(){
        String tf = Config.getConfig().getString("timeframe");
        int multiplier = 0;
        if(tf.equalsIgnoreCase("second")){
            // Do nothing.
        }else if(tf.equalsIgnoreCase("minute")){
            multiplier = 60;
        }else if(tf.equalsIgnoreCase("hour")){
            multiplier = 60*60;
        }else if(tf.equalsIgnoreCase("day")){
            multiplier = 60*60*24;
        }
        return Config.getConfig().getInt("ban-length")*multiplier;
    }

    public static String parseTime(int time){
        int hours = 0;
        int minutes = 0;
        int seconds = time;
        while(seconds >= 60){
            seconds -= 60;
            minutes += 1;
        }
        while(minutes >= 60){
            minutes -= 60;
            hours += 1;
        }
        return ""+hours+":"+minutes+"h "+seconds+"s";
    }

    public static String parseColoredString(String string){
        return string.replaceAll("&", "§");
    }

    public static boolean addExemption(String player) {
        String uuid = null;
        try {
            uuid = UUIDFetcher.getUUIDOf(player).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> list = Config.getExempt().getStringList("exempt");
        if (list == null) {
            list = new ArrayList<String>();
        }
        if (list.contains(uuid)) return false;
        list.add(uuid);
        Config.getExempt().set("exempt", list);
        Config.saveAll();
        return true;
    }

    public static boolean addExemption(UUID u) {
        String uuid = u.toString();
        List<String> list = Config.getExempt().getStringList("exempt");
        if (list == null) {
            list = new ArrayList<String>();
        }
        if (list.contains(uuid)) return false;
        list.add(uuid);
        Config.getExempt().set("exempt", list);
        Config.saveAll();
        return true;
    }

    public static boolean removeExemption(String player) {
        String uuid = null;
        try {
            uuid = UUIDFetcher.getUUIDOf(player).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> list = Config.getExempt().getStringList("exempt");
        if (list == null) {
            list = new ArrayList<String>();
        }
        if (!list.contains(uuid)) return false;
        list.remove(uuid);
        Config.getExempt().set("exempt", list);
        Config.saveAll();
        return true;
    }

    public static boolean removeExemption(UUID u) {
        String uuid = u.toString();
        List<String> list = Config.getExempt().getStringList("exempt");
        if (list == null) {
            list = new ArrayList<String>();
        }
        if (!list.contains(uuid)) return false;
        list.remove(uuid);
        Config.getExempt().set("exempt", list);
        Config.saveAll();
        return true;
    }

    public static void changeLives(String player, int amount) {

    }

    public static void changeLives(UUID uuid, int amount) {

    }
}
