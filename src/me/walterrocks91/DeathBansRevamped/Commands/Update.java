package me.walterrocks91.DeathBansRevamped.Commands;

import me.walterrocks91.DeathBansRevamped.API.API;
import me.walterrocks91.DeathBansRevamped.API.DBCommand;
import org.bukkit.command.CommandSender;

public class Update extends DBCommand {

    public Update() {
        super("update", "Force updates DeathBans.");
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("deathbans.admin")) {
            // TODO: Coming soon <3
            API.sendMessage(sender, "&cNot yet implemented!");
            return;
        }
    }
}
