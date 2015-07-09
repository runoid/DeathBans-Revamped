package me.walterrocks91.DeathBansRevamped.Events;

import me.walterrocks91.DeathBansRevamped.API.API;
import me.walterrocks91.DeathBansRevamped.Config;
import me.walterrocks91.DeathBansRevamped.Main;
import me.walterrocks91.DeathBansRevamped.World.Worlds;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Death implements Listener {
    @EventHandler
    public void onDeath(final PlayerDeathEvent e) {
        if (Worlds.isInDeniedWorld(e.getEntity().getWorld())) return;
        e.getEntity().getWorld().strikeLightningEffect(e.getEntity().getLocation());
        if (API.getLives(e.getEntity().getUniqueId()) > 0) {
            API.changeLives(e.getEntity().getUniqueId(), -1);
            API.sendMessage(e.getEntity(), "&cYou have lost &e1 life&c. Remaining lives: &e" + API.getLives(e.getEntity().getUniqueId()));
            return;
        }
        if (!Config.getConfig().getBoolean("delay-ban")) {
            ItemStack[] inv = e.getEntity().getInventory().getContents();
            ItemStack[] armor = e.getEntity().getInventory().getArmorContents();
            e.getDrops().clear();
            for (ItemStack i : inv) {
                e.getDrops().add(i);
            }
            for (ItemStack i : armor) {
                e.getDrops().add(i);
            }
            e.getEntity().getInventory().clear();
            e.getEntity().getInventory().setArmorContents(new ItemStack[e.getEntity().getInventory().getArmorContents().length]);
            API.ban(e.getEntity());
        } else {
            Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                public void run() {
                    API.ban(e.getEntity());
                }
            }, 20);
        }
    }
}