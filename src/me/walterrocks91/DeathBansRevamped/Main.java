package me.walterrocks91.DeathBansRevamped;

import me.walterrocks91.DeathBansRevamped.API.API;
import me.walterrocks91.DeathBansRevamped.Events.Death;
import me.walterrocks91.DeathBansRevamped.Events.Login;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

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
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("deathbans") || cmd.getName().equalsIgnoreCase("db")){
            int l = args.length;
            if(l == 0){
                API.sendMessage(sender, "&cIncorrect / Invalid SubCommand.");
                API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
            }else{
                if (args[0].equalsIgnoreCase("help")) {
                    API.sendMessage(sender, "&e/DeathBans lives <Player> &f- Shows a players current lives.");
                    if(sender.hasPermission("deathbans.admin")) {
                        API.sendMessage(sender, "&f-=[&cDeathBans Admin Commands&f]=-");
                        API.sendMessage(sender, "&e/DeathBans check <Player> &f- Checks if a player is banned.");
                        API.sendMessage(sender, "&e/DeathBans unban <Player> &f- Unbans a banned player.");
                        API.sendMessage(sender, "&e/DeathBans exempt <Player> &f- Exempts a player from DeathBans.");
                        API.sendMessage(sender, "&e/DeathBans unexempt <Player> &f- UnExempts a player from DeathBans.");
                        API.sendMessage(sender, "&e/DeathBans editlives <Give>|<Take> <Player> <Amount> &f- Edits a players lives.");
                        API.sendMessage(sender, "&e/DeathBans update &f- Updates DeathBans manually.");
                    }
                    return true;
                }else if(args[0].equalsIgnoreCase("check")){

                }else if(args[0].equalsIgnoreCase("unban")){

                }else if(args[0].equalsIgnoreCase("exempt")){

                }else if(args[0].equalsIgnoreCase("unexempt")){

                }else if(args[0].equalsIgnoreCase("editlives")){

                }else if(args[0].equalsIgnoreCase("update")){

                }else{
                    API.sendMessage(sender, "&cIncorrect / Invalid SubCommand.");
                    API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
                }
            }
        }
        return true;
    }
}
