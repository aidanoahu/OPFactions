package network.ethyl.opfactions.patches;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class FenceGlitch implements Listener {

    /*

    Listener to patch fence glitching potions in 1.7.10

     */

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getPlayer() == null) {
            return;
        }

        Player p = e.getPlayer();
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (e.getClickedBlock().getType() == Material.FENCE) {
            e.setCancelled(true);
        }
    }
}
