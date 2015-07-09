package me.walterrocks91.DeathBansRevamped;

import me.walterrocks91.DeathBansRevamped.API.API;
import me.walterrocks91.DeathBansRevamped.API.DBCommand;
import me.walterrocks91.DeathBansRevamped.Commands.*;
import me.walterrocks91.DeathBansRevamped.Events.Death;
import me.walterrocks91.DeathBansRevamped.Events.Login;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {

    public List<DBCommand> cmds = new ArrayList<DBCommand>();

    private static Main instance;

    public Main(){
        instance = this;
    }

    public static Main getInstance(){
        return instance;
    }

    public void onEnable(){
        instance = this;
        API.setInstance(this);
        Manager.setup(this);
        getServer().getPluginManager().registerEvents(new Death(), this);
        getServer().getPluginManager().registerEvents(new Login(), this);
        if (Config.getTimer().getConfigurationSection("timer") == null) Config.getTimer().createSection("timer");
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {
                getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
                    public void run() {
                        ConfigurationSection s = Config.getTimer().getConfigurationSection("timer");
                        for (String u : s.getKeys(false)) {
                            if (s.getInt(u) <= 0) {
                                API.unban(UUID.fromString(u));
                                return;
                            }
                            s.set(u, s.getInt(u) - 1);
                            Config.saveAll();
                        }
                    }
                }, 0, 20);
            }
        }, 60);
        if (cmds == null) cmds = new ArrayList<DBCommand>();
        Commands.register(new Help());
        Commands.register(new Unban());
        Commands.register(new Check());
        Commands.register(new Editlives());
        Commands.register(new Exempt());
        Commands.register(new Unexempt());
        Commands.register(new Lives());
        Commands.register(new Revive());
        Commands.register(new Update());
    }

    private DBCommand getDBCommand(String cmd) {
        for (DBCommand command : cmds) {
            if (command.getCommand().equalsIgnoreCase(cmd)) return command;
        }
        return null;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("deathbans")) {
            int l = args.length;
            if(l == 0){
                API.sendMessage(sender, "&cIncorrect / Invalid SubCommand.");
                API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
            } else {
                DBCommand command = getDBCommand(args[0]);
                if (command == null) {
                    API.sendMessage(sender, "&cIncorrect / Invalid SubCommand.");
                    API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
                    return true;
                }
                String[] newArgs = null;
                List<String> list = new ArrayList<String>(Arrays.asList(args));
                list.removeAll(Arrays.asList(args[0]));
                newArgs = list.toArray(new String[0]);
                command.execute(sender, newArgs);
            }
        }
        return true;
    }
}
