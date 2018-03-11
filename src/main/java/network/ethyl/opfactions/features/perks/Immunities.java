package network.ethyl.opfactions.features.perks;

import me.aidan.lib.api.OahuUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Immunities implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Entity en = e.getEntity();
        if (en instanceof Player) {
            Player p = (Player) en;
            if (e.getCause() == DamageCause.FIRE_TICK
                    || e.getCause() == DamageCause.FIRE
                    || e.getCause() == DamageCause.LAVA) {
                if (p.hasPermission("immunity.fire")) {
                    e.setCancelled(true);
                }
            }

            if (e.getCause() == DamageCause.DROWNING) {
                if (p.hasPermission("immunity.water")) {
                    e.setCancelled(true);
                }
            }

            if (e.getCause() == DamageCause.FALL) {
                if (p.hasPermission("perks.jellylegs")) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle() == null) return;
        if (e.getInventory().getTitle().equalsIgnoreCase(OahuUtils.translate("&6Perks"))) {
            e.setCancelled(true);
        }
    }

}
