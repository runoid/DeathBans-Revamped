package me.walterrocks91.DeathBansRevamped.Events.Custom;

import me.walterrocks91.DeathBansRevamped.User.DBUser;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerDeathbannedEvent extends Event {

    private boolean cancelled = false;

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    private DBUser user;

    public PlayerDeathbannedEvent(DBUser u) {
        user = u;
    }

    public DBUser getUser() {
        return user;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return cancelled;
    }

}
