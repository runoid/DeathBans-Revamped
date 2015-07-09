package me.walterrocks91.DeathBansRevamped.Commands;

import me.walterrocks91.DeathBansRevamped.API.API;
import me.walterrocks91.DeathBansRevamped.API.DBCommand;
import org.bukkit.command.CommandSender;

public class Check extends DBCommand {

    public Check() {
        super("check", "Checks if a player is banned.", "<Player>");
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("deathbans.admin")) {
            if (args.length != 1) {
                API.sendMessage(sender, "&cInvalid arguments.");
                API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
                return;
            }
            if (API.checkBan(args[0]))
                API.sendMessage(sender, "&eThe player &f" + args[1] + " &eis &ccurrently banned.");
            else
                API.sendMessage(sender, "&eThe player &f" + args[1] + " &eis &anot currently banned.");
            return;
        }
    }
}
