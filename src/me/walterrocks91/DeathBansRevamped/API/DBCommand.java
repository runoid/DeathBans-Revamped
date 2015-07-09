package me.walterrocks91.DeathBansRevamped.API;

import org.bukkit.command.CommandSender;

public abstract class DBCommand {

    private String cmd, desc, usage;
    private String[] aliases;

    public DBCommand(String cmd, String description, String usage, String... aliases) {
        this.cmd = cmd;
        this.desc = desc;
        this.usage = API.parseColoredString("&e/DeathBans " + cmd.split(" ")[0] + " " + (usage.trim()) + "&f- ") + desc.replaceAll("§", "").replaceAll("&", "");
        this.aliases = aliases;
    }

    public DBCommand(String cmd, String desc, String usage) {
        usage = usage.trim();
        this.cmd = cmd;
        this.desc = desc;
        this.usage = API.parseColoredString("&e/DeathBans " + cmd.split(" ")[0] + " " + usage + " &f- ") + desc.replaceAll("§", "").replaceAll("&", "");
    }

    public DBCommand(String cmd, String desc) {
        this.cmd = cmd;
        this.desc = desc;
        this.usage = API.parseColoredString("&e/DeathBans " + cmd.split(" ")[0] + " &f- ") + desc.replaceAll("§", "").replaceAll("&", "");
    }

    public abstract void execute(CommandSender sender, String[] args);

    public String getCommand() {
        return cmd;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return desc;
    }

    public String[] getAliases() {
        return aliases;
    }

}
