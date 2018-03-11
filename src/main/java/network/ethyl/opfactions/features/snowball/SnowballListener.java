package network.ethyl.opfactions.features.snowball;

import me.aidan.lib.api.OahuUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SnowballListener implements Listener {

    /*

    Prevents throwing of winter event snowballs

     */

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getItemInHand() == null) return;
        if (p.getItemInHand().getItemMeta() == null) return;
        if (p.getItemInHand().getItemMeta().getDisplayName() == null) return;
        if (p.getItemInHand().getType() != Material.SNOW_BALL) return;

        if (p.getItemInHand().getItemMeta().getDisplayName().equals(OahuUtils.translate("&b&l&oGUSH OF FREEZING COLD WIND"))) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                e.setCancelled(true);
                p.updateInventory();
            }
        }
    }
}
