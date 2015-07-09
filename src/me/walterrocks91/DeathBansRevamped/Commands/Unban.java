package me.walterrocks91.DeathBansRevamped.Commands;

import me.walterrocks91.DeathBansRevamped.API.API;
import me.walterrocks91.DeathBansRevamped.API.DBCommand;
import org.bukkit.command.CommandSender;

public class Unban extends DBCommand {

    public Unban() {
        super("unban", "Unbans a banned player.", "<Player>");
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("deathbans.admin")) {
            if (args.length != 1) {
                API.sendMessage(sender, "&cInvalid arguments");
                API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
                return;
            }
            try {
                if (API.unban(args[0]))
                    API.sendMessage(sender, "&eSuccessfully unbanned &f" + args[0]);
                else
                    API.sendMessage(sender, "&eUn-Successfully unbanned &f" + args[0] + "&e. Player was not banned.");
            } catch (Exception e) {
                API.sendMessage(sender, "&cInvalid player specified. Could not get UUID of " + args[0]);
            }
        }
    }
}
