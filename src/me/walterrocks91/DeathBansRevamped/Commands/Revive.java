package me.walterrocks91.DeathBansRevamped.Commands;

import me.walterrocks91.DeathBansRevamped.API.API;
import me.walterrocks91.DeathBansRevamped.API.DBCommand;
import org.bukkit.command.CommandSender;

public class Revive extends DBCommand {

    public Revive() {
        super("revive", "Revives a deathbanned player.", "<Player>");
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length != 2) {
            API.sendMessage(sender, "&cInvalid arguments");
            API.sendMessage(sender, "&eUse &f/DeathBans help &efor a list of all DeathBans commands.");
            return;
        }
        if (API.getLives(sender.getName()) >= 1) {
            if (API.unban(args[1])) {
                API.sendMessage(sender, "&aThe player &f" + args[0] + " &ahas been revived!");
                API.changeLives(sender.getName(), -1);
                return;
            } else {
                API.sendMessage(sender, "&cThe player &f" + args[0] + " &cwas not deathbanned.");
            }
        } else {
            API.sendMessage(sender, "&cYou do not have any lives to revive this player with!");
        }
        return;
    }
}
