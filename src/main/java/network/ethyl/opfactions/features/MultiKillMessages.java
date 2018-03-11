package network.ethyl.opfactions.features;

import me.aidan.lib.api.OahuUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;

import java.util.HashMap;

public class MultiKillMessages implements Listener {

    /*

    Listener that displays a message in public chat when a player gets multiple kills within 15 seconds of each other

     */

    HashMap<Player, Long> cooldown = new HashMap<>();
    HashMap<Player, Integer> multi = new HashMap<>();

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player dead = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();

        int cooldownTime = 15;
        if (cooldown.containsKey(killer) && multi.containsKey(killer)) {
            long secondsLeft = ((cooldown.get(killer) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
            if (secondsLeft > 0) {
                if (multi.get(killer) > 0) {
                    multi.put(killer, multi.get(killer) + 1);
                    cooldown.put(killer, System.currentTimeMillis());
                    switch (multi.get(killer)) {
                        case 2:
                            Bukkit.broadcastMessage(OahuUtils.translate("&cEthyl &8» &4&l" + killer.getName() + " &6&ljust got a &c&lDOUBLE KILL&6&l!"));
                            break;
                        case 3:
                            Bukkit.broadcastMessage(OahuUtils.translate("&cEthyl &8» &4&l" + killer.getName() + " &6&ljust got a &c&lTRIPLE KILL&6&l!"));
                            break;
                        case 4:
                            Bukkit.broadcastMessage(OahuUtils.translate("&cEthyl &8» &4&l" + killer.getName() + " &6&ljust got a &c&lQUAD KILL&6&l!"));
                            break;
                    }

                    if (multi.get(killer) >= 5) {
                        Bukkit.broadcastMessage(OahuUtils.translate("&cEthyl &8» &4&l" + killer.getName() + " &6&ljust got a &c&l&o&nMULTI KILL&r&6&l!"));
                    }
                } else {
                    multi.put(killer, 1);
                    cooldown.put(killer, System.currentTimeMillis());
                }
            } else {
                cooldown.put(killer, System.currentTimeMillis());
                multi.put(killer, 1);
            }
        } else {
            cooldown.put(killer, System.currentTimeMillis());
            multi.put(killer, 1);
        }

        if (cooldown.containsKey(dead)) {
            cooldown.remove(dead);
        }

        if (multi.containsKey(dead)) {
            multi.remove(dead);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (cooldown.containsKey(p)) {
            cooldown.remove(p);
        }

        if (multi.containsKey(p)) {
            multi.remove(p);
        }
    }

    @EventHandler
    public void onDisable(PluginDisableEvent e) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (cooldown.containsKey(p)) {
                cooldown.remove(p);
            }

            if (multi.containsKey(p)) {
                multi.remove(p);
            }
        }
    }
}
