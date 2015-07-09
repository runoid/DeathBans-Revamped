package me.walterrocks91.DeathBansRevamped.Commands;

import me.walterrocks91.DeathBansRevamped.API.API;
import me.walterrocks91.DeathBansRevamped.API.DBCommand;
import org.bukkit.command.CommandSender;

public class Lives extends DBCommand {

    public Lives() {
        super("lives", "Checks a players lives.", "[Player]");
    }

    public void execute(CommandSender sender, String[] args) {
        String target = "";
        if (args.length != 1)
            target = sender.getName();
        else target = args[0];
        API.sendMessage(sender, "&ePlayer &a" + target + " &ehas &a" + API.getLives(target) + " &elives.");
    }
}
