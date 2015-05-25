package me.walterrocks91.DeathBansRevamped;

import me.walterrocks91.DeathBansRevamped.API.API;
import me.walterrocks91.DeathBansRevamped.Events.Death;
import me.walterrocks91.DeathBansRevamped.Events.Join;
import me.walterrocks91.DeathBansRevamped.Events.Login;
import me.walterrocks91.DeathBansRevamped.Events.Quit;
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
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Quit(), this);
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
                    if (l != 1) {
                        API.sendMessage(sender, "&cInvalid arguments");
                        API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
                        return true;
                    }
                    if (API.checkBan(args[1]))
                        API.sendMessage(sender, "&eThe player &f" + args[1] + "&eis &ccurrently banned.");
                    else
                        API.sendMessage(sender, "&eThe player &f" + args[1] + "&eis &anot currently banned.");
                    return true;
                }else if(args[0].equalsIgnoreCase("unban")){
                    if (l != 2) {
                        API.sendMessage(sender, "&cInvalid arguments");
                        API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
                        return true;
                    }
                    try {
                        if (API.unban(args[1]))
                            API.sendMessage(sender, "&eSuccessfully unbanned &f" + args[1]);
                        else
                            API.sendMessage(sender, "&eUn-Successfully unbanned &f" + args[1] + "&e. Player was not banned.");
                    } catch (Exception e) {
                        API.sendMessage(sender, "&cInvalid player specified. Could not get UUID of " + args[1]);
                    }
                    return true;
                }else if(args[0].equalsIgnoreCase("exempt")){
                    if (l != 2) {
                        API.sendMessage(sender, "&cInvalid arguments");
                        API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
                        return true;
                    }
                    if (API.addExemption(args[1]))
                        API.sendMessage(sender, "&ePlayer &f" + args[1] + " &eis now immune to deathbans.");
                    else
                        API.sendMessage(sender, "&ePlayer &f" + args[1] + " &ewas already immune to deathbans.");
                    return true;
                }else if(args[0].equalsIgnoreCase("unexempt")){
                    if (l != 2) {
                        API.sendMessage(sender, "&cInvalid arguments");
                        API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
                        return true;
                    }
                    if (API.removeExemption(args[1]))
                        API.sendMessage(sender, "&ePlayer &f" + args[1] + " &eis no longer immune to deathbans.");
                    else
                        API.sendMessage(sender, "&ePlayer &f" + args[1] + " &ewas not immune to deathbans.");
                }else if(args[0].equalsIgnoreCase("editlives")){
                    if (l != 5) {
                        API.sendMessage(sender, "&cInvalid arguments");
                        API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("take"))
                        API.changeLives(args[3], -(Integer.parseInt(args[4])));
                    else if (args[2].equalsIgnoreCase("give"))
                        API.changeLives(args[3], (Integer.parseInt(args[4])));
                    else {
                        API.sendMessage(sender, "&cInvalid arguments");
                        API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
                        return true;
                    }
                }else if(args[0].equalsIgnoreCase("update")){
                    // TODO: Coming soon <3
                }else{
                    API.sendMessage(sender, "&cIncorrect / Invalid SubCommand.");
                    API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
                }
            }
        }
        return true;
    }
}
