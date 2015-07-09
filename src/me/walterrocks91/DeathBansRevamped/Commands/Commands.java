package me.walterrocks91.DeathBansRevamped.Commands;

import me.walterrocks91.DeathBansRevamped.API.DBCommand;
import me.walterrocks91.DeathBansRevamped.Main;

import java.util.ArrayList;
import java.util.List;

public class Commands {

    private static List<DBCommand> registered = Main.getInstance().cmds;

    public static void register(DBCommand cmd) {
        if (registered.contains(cmd)) return;
        registered.add(cmd);
    }

    public static void unregister(DBCommand cmd) {
        if (!registered.contains(cmd)) return;
        registered.remove(cmd);
    }

    public static List<DBCommand> getCommands() {
        if (registered != null) return registered;
        return new ArrayList<DBCommand>();
    }

}
