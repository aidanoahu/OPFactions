package network.ethyl.opfactions.features.stats;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class StatsResetListener implements Listener {

    /*

    Listener to reset a player's stats if the map has been reset since last logged in

     */

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPlayedBefore()) {
            p.setStatistic(Statistic.PLAYER_KILLS, 0);
            p.setStatistic(Statistic.DEATHS, 0);
        }
    }
}
