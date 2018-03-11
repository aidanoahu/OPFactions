package network.ethyl.opfactions.features.stats;

import network.ethyl.opfactions.utils.PlayerConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class StatsListener implements Listener {

    /*

    Listeners to update stats in PlayerConfig file(s)

     */

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent e) {
        Player dead = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();

        if (killer == null) return;

        PlayerConfig.load(dead);
        PlayerConfig.get().set("deaths", PlayerConfig.get().getDouble("deaths") + 1);
        PlayerConfig.save();

        PlayerConfig.load(killer);
        PlayerConfig.get().set("kills", PlayerConfig.get().getDouble("kills") + 1);
        PlayerConfig.save();

        updateHeadWorth(dead);
        updateHeadWorth(killer);
    }

    private void updateHeadWorth(Player p) {
        PlayerConfig.load(p);
        double kills = PlayerConfig.get().getDouble("kills");
        double deaths = PlayerConfig.get().getDouble("deaths");
        double kdr;
        double headWorth;

        if (deaths == 0) {
            kdr = kills;
        } else if (kills == 0) {
            kdr = 0;
        } else {
            kdr = kills / deaths;
        }

        headWorth = kdr * 20000;

        PlayerConfig.get().set("head value", headWorth);
        PlayerConfig.save();
    }
}
