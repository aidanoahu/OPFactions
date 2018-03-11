package network.ethyl.opfactions.features.duels;

import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.*;

public class DuelListener implements Listener {

    /*

    Listeners for the duel command

     */

    private Location spawn = new Location(Bukkit.getWorld("world2"), 0 , 72, 0);

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (DuelCommand.outgoingRequests.containsKey(p.getName())) {
            DuelCommand.outgoingRequests.remove(p.getName());
        }

        if (DuelCommand.incomingRequests.containsKey(p.getName())) {
            DuelCommand.incomingRequests.remove(p.getName());
        }

        if (DuelCommand.inDuels.contains(p.getName())) {
            Player other = Bukkit.getPlayer(DuelCommand.duels.get(p.getName()));
            DuelCommand.inDuels.remove(p.getName());
            DuelCommand.duels.remove(p.getName());
            p.teleport(spawn);
            other.teleport(spawn);
            DuelCommand.inDuels.remove(other.getName());
            DuelCommand.duels.remove(other.getName());
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            DuelCommand.redeem.put(other.getName(), p.getInventory().getContents());
            DuelCommand.redeemArmor.put(other.getName(), p.getInventory().getArmorContents());
            other.sendMessage(OahuUtils.translate("&cYou have won the duel due to your opponent disconnecting."));
            other.sendMessage(OahuUtils.translate("&cRedeem their loot with &6/duel redeem&c. These items will only be available temporarily!"));
            for (Player server : Bukkit.getServer().getOnlinePlayers()) {
                if (server != other) {
                    p.showPlayer(server);
                }

                if (server != p) {
                    other.showPlayer(server);
                }
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();
        Player winner = e.getEntity().getKiller();

        if (winner == null || p == null) return;

        if (DuelCommand.inDuels.contains(p.getName()) && DuelCommand.inDuels.contains(winner.getName())) {
            Bukkit.broadcastMessage(OahuUtils.translate("&cEthyl &8» &6" + winner.getName() + " &7has defeated &6" + p.getName() + " &7in a 1v1!"));
            winner.sendMessage(OahuUtils.translate("&7Loot &c" + p.getName() + "&7's items with &6/duel redeem&7. These items will only be available temporarily!"));
            DuelCommand.redeem.put(winner.getName(), p.getInventory().getContents());
            DuelCommand.redeemArmor.put(winner.getName(), p.getInventory().getArmorContents());
            for (Entity ent : Bukkit.getWorld("Duels").getEntities()) {
                if ((!(ent instanceof LivingEntity)) &&
                        (!(ent instanceof ItemFrame)) &&
                        (!(ent instanceof Boat))) {
                    ent.remove();
                }
            }
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(OPFactionsCore.getCore(), new Runnable() {
                @Override
                public void run() {
                    DuelCommand.inDuels.remove(p.getName());
                    DuelCommand.duels.remove(p.getName());
                    winner.teleport(spawn);
                    DuelCommand.inDuels.remove(winner.getName());
                    DuelCommand.duels.remove(winner.getName());
                    for (Player server : Bukkit.getServer().getOnlinePlayers()) {
                        if (server != winner) {
                            p.showPlayer(server);
                        }

                        if (server != p) {
                            winner.showPlayer(server);
                        }
                    }
                }
            }, (2 * 20));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        for (String s : DuelCommand.inDuels) {
            Player sp = Bukkit.getPlayer(s);
            sp.hidePlayer(p);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (DuelCommand.inDuels.contains(p.getName())) {
            for (String command : OPFactionsCore.getCore().getConfig().getStringList("blocked duel commands")) {
                if (e.getMessage().startsWith("/") && e.getMessage().contains(command)) {
                    p.sendMessage(OahuUtils.translate("&cYou cannot do this during a duel."));
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();

        if (DuelCommand.inDuels.contains(p.getName())) {
            e.setCancelled(true);
            p.sendMessage(OahuUtils.translate("&cYou cannot drop items during a duel."));
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();

        if (DuelCommand.inDuels.contains(p.getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().getTitle().equals(OahuUtils.translate("&cRecent Duel Loot"))) {
            DuelCommand.redeem.put(e.getPlayer().getName(), e.getInventory().getContents());
        }
    }
}
