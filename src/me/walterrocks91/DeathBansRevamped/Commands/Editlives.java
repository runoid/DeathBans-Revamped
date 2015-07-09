package me.walterrocks91.DeathBansRevamped.Commands;

import me.walterrocks91.DeathBansRevamped.API.API;
import me.walterrocks91.DeathBansRevamped.API.DBCommand;
import org.bukkit.command.CommandSender;

public class Editlives extends DBCommand {

    public Editlives() {
        super("editlives", "Edits a players lives.", "<Give>|<Take> <Player> <Amount>");
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("deathbans.admin")) {
            if (args.length != 3) {
                API.sendMessage(sender, "&cInvalid arguments");
                API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
                return;
            }
            if (args[0].equalsIgnoreCase("take")) {
                API.changeLives(args[1], -(Integer.parseInt(args[2])));
                API.sendMessage(sender, "&eThe player &a" + args[2] + " &ehas lost &a" + args[2] + " &elives. Remaining lives: &a" + API.getLives(args[1]));
            } else if (args[0].equalsIgnoreCase("give")) {
                API.changeLives(args[1], (Integer.parseInt(args[2])));
                API.sendMessage(sender, "&eThe player &a" + args[1] + " &ehas gained &a" + args[2] + " &elives. Current lives: &a" + API.getLives(args[1]));
            } else {
                API.sendMessage(sender, "&cInvalid arguments");
                API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
                return;
            }
        }
    }
}
