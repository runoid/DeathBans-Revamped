package me.walterrocks91.DeathBansRevamped.Commands;

import me.walterrocks91.DeathBansRevamped.API.API;
import me.walterrocks91.DeathBansRevamped.API.DBCommand;
import org.bukkit.command.CommandSender;

public class Unexempt extends DBCommand {

    public Unexempt() {
        super("unexempt", "Revokes an exemption to DeathBans.", "<Player>");
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("deathbans.admin")) {
            if (args.length != 2) {
                API.sendMessage(sender, "&cInvalid arguments");
                API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
                return;
            }
            if (API.removeExemption(args[0]))
                API.sendMessage(sender, "&ePlayer &f" + args[0] + " &eis no longer immune to deathbans.");
            else
                API.sendMessage(sender, "&ePlayer &f" + args[0] + " &ewas not immune to deathbans.");
        }
    }
}
