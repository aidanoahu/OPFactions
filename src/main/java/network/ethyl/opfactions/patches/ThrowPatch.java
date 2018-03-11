package network.ethyl.opfactions.patches;

import me.aidan.lib.api.OahuUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ThrowPatch implements Listener {

    /*

    Listener to remove throwing potions, eggs, snowballs, and fishing rods

     */

    @EventHandler
    public void onDebuff(ProjectileLaunchEvent e) {
        Player p = (Player) e.getEntity().getShooter();
        if (e.getEntity().getShooter() == null) return;
        if (e.getEntity() == null || e.getEntity().getType() == null) return;
        if (e.getEntity().getType() == EntityType.SPLASH_POTION) {
            e.setCancelled(true);
            p.sendMessage(OahuUtils.translate("&cSplash potions are disabled."));
        }

        if (e.getEntity().getType() == EntityType.EGG) {
            e.setCancelled(true);
            p.sendMessage(OahuUtils.translate("&cEggs are disabled."));
        }

        if (e.getEntity().getType() == EntityType.SNOWBALL) {
            e.setCancelled(true);
            p.sendMessage(OahuUtils.translate("&cSnowballs are disabled."));
        }

        if (e.getEntity().getType() == EntityType.FISHING_HOOK) {
            e.setCancelled(true);
            p.sendMessage(OahuUtils.translate("&cFishing rods are disabled."));
        }
    }
}
