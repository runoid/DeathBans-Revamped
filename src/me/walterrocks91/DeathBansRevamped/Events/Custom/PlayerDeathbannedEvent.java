package me.walterrocks91.DeathBansRevamped.Events.Custom;

import org.bukkit.entity.Player;
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

    private Player p;

    public PlayerDeathbannedEvent(Player p) {
        this.p = p;
    }

    public Player getPlayer() {
        return p;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return cancelled;
    }

}
