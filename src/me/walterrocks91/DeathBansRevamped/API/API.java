package me.walterrocks91.DeathBansRevamped.API;

import me.walterrocks91.DeathBansRevamped.Config;
import me.walterrocks91.DeathBansRevamped.Events.Custom.PlayerDeathbannedEvent;
import me.walterrocks91.DeathBansRevamped.Main;
import me.walterrocks91.DeathBansRevamped.Other.UUIDFetcher;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class API {

    protected static Main instance;

    public static void setInstance(Main main){
        instance = main;
    }

    public static Main getMain(){
        return instance;
    }

    private static Set<String> co;

    private static String prefix = "";

    private static int banTask;

    public static void sendMessage(CommandSender sender, String msg){
        prefix = API.parseColoredString(Config.getConfig().getString("prefix") + " &f");
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
                Config.getTimer().set(uuid, 0);
                Config.saveAll();
                return true;
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
            Config.getTimer().set(uuid.toString(), null);
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
            multiplier = 1;
        }else if(tf.equalsIgnoreCase("minute")){
            multiplier = 60;
        }else if(tf.equalsIgnoreCase("hour")){
            multiplier = 60*60;
        }else if(tf.equalsIgnoreCase("day")){
            multiplier = 60*60*24;
        }
        try {
            return Config.getConfig().getInt("ban-length") * multiplier;
        } catch (IllegalArgumentException e) {
            System.out.println("Ban Length is not a integer, could not get ban length.");
            return 0;
        }
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
        String hourString = "";
        String minuteString = "";
        String secondString = "";
        if (hours < 10)
            hourString = "0" + hours;
        else hourString = "" + hours;
        if (minutes < 10)
            minuteString = "0" + minutes;
        else minuteString = "" + minutes;
        if (seconds < 10)
            secondString = "0" + seconds;
        else secondString = "" + seconds;
        return hourString + ":" + minuteString + ":" + secondString;
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

    public static int getLives(String player) {
        String uuid = null;
        try {
            uuid = UUIDFetcher.getUUIDOf(player).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Config.getLives().getInt(uuid);
    }

    public static int getLives(UUID uuid) {
        return Config.getLives().getInt(uuid.toString());
    }

    public static void changeLives(String player, int amount) {
        String uuid = null;
        try {
            uuid = UUIDFetcher.getUUIDOf(player).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int lives = getLives(player) + amount;
        if (lives < 0) {
            lives = 0;
        }
        if (uuid == null) {
            return;
        }
        Config.getLives().set(uuid, lives);
        Config.saveAll();
    }

    public static void changeLives(UUID uuid, int amount) {
        int lives = getLives(uuid) + amount;
        if (lives < 0) {
            lives = 0;
        }
        Config.getLives().set(uuid.toString(), lives);
        Config.saveAll();
    }

    public static void ban(Player player) {
        if (Config.getExempt().getStringList("exempt").contains(player.getUniqueId().toString())) return;
        PlayerDeathbannedEvent event = new PlayerDeathbannedEvent(player);
        Main.getInstance().getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        String uuid = player.getUniqueId().toString();
        final String u = uuid;
        List<String> list = Config.getBans().getStringList("banned");
        if (list == null) {
            list = new ArrayList<String>();
        }
        list.add(u);
        Config.getBans().set("banned", list);
        Config.getTimer().set(u, API.getLessenedBanLength(player));
        Config.saveAll();
        banTask = Main.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            public void run() {
                if (Config.getTimer().getInt(u) <= 0) {
                    Main.getInstance().getServer().getScheduler().cancelTask(banTask);
                    API.unban(UUID.fromString(u));
                    return;
                }
                Config.getTimer().set(u, Config.getTimer().getInt(u) - 1);
                Config.saveAll();
            }
        }, 0, 1 * 20);
        player.kickPlayer(API.parseColoredString(API.getKickReason()));
    }

    public static int getLessenedBanLength(Player p) {
        if (Config.getConfig().getConfigurationSection("perms").getKeys(false) == null) return API.getBanLength();
        if (co == null) co = Config.getConfig().getConfigurationSection("perms").getKeys(false);
        for (String perm : co) {
            if (p.hasPermission("deathbans." + perm)) {
                String tf = Config.getConfig().getString("perms." + perm + ".timeframe");
                int multiplier = 0;
                if (tf.equalsIgnoreCase("second")) {
                    multiplier = 1;
                } else if (tf.equalsIgnoreCase("minute")) {
                    multiplier = 60;
                } else if (tf.equalsIgnoreCase("hour")) {
                    multiplier = 60 * 60;
                } else if (tf.equalsIgnoreCase("day")) {
                    multiplier = 60 * 60 * 24;
                }
                return API.getBanLength() - (Config.getConfig().getInt("perms." + perm + ".lessened-banlength") * multiplier);
            }
        }
        return API.getBanLength();
    }
}
