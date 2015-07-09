package me.walterrocks91.DeathBansRevamped.Commands;

import me.walterrocks91.DeathBansRevamped.API.DBCommand;
import me.walterrocks91.DeathBansRevamped.Main;
import org.bukkit.command.CommandSender;

public class Help extends DBCommand {

    public Help() {
        super("help", "Gives a list of all DeathBans commands. (And registered ones too!)");
    }

    public void execute(CommandSender sender, String[] args) {
        for (DBCommand cmd : Main.getInstance().cmds) {
            sender.sendMessage(cmd.getUsage());
        }
    }
}
