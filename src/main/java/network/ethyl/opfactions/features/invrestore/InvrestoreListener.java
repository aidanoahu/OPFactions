package network.ethyl.opfactions.features.invrestore;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class InvrestoreListener implements Listener {

    /*

    Listener for the invrestore command

     */


    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Invrestore.inventories.put(e.getEntity().getPlayer().getName(), e.getEntity().getPlayer().getInventory().getContents());
        Invrestore.armor.put(e.getEntity().getPlayer().getName(), e.getEntity().getPlayer().getInventory().getArmorContents());
    }
}
