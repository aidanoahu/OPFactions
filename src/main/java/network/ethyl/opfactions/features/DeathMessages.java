package network.ethyl.opfactions.features;

import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import network.ethyl.opfactions.utils.PlayerConfig;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;
import java.util.Random;

public class DeathMessages implements Listener {

    /*

    Listener to display a custom death message upon player death

     */

    public String getDeathTerm() {
        List<String> terms = OPFactionsCore.getCore().getConfig().getStringList("death terms");
        return terms.get(new Random().nextInt(terms.size()));
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();

        if (player.getKiller() == null) {
            e.setDeathMessage(null);
        } else {
            if (killer.getItemInHand().getItemMeta().getDisplayName() == null) {
                e.setDeathMessage(OahuUtils.translate("&cEthyl &8» &c" + player.getName() + " &6was " + getDeathTerm() + " by &c" + killer.getName() + "&6!"));
            } else {
                e.setDeathMessage(OahuUtils.translate("&cEthyl &8» &c" + player.getName() + " &6was " + getDeathTerm() + " by &c" + killer.getName() + " &6using &7" + killer.getItemInHand().getItemMeta().getDisplayName() + "&6!"));
            }
        }
    }

    private double getKills(Player p) {
        PlayerConfig.load(p);
        return PlayerConfig.get().getDouble("kills");
    }
}
