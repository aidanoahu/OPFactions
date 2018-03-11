package network.ethyl.opfactions.features.koth;

import me.aidan.lib.api.OahuUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import subside.plugins.koth.KothPlugin;
import subside.plugins.koth.gamemodes.RunningKoth;

public class SuperKoTHBroadcast implements Runnable {

    /*

    When a Super KoTH is running, a broadcast will happen about it every 2 minutes

     */

    @Override
    public void run() {
        RunningKoth koth = JavaPlugin.getPlugin(KothPlugin.class).getKothHandler().getRunningKoth();
        if (koth != null) {
            if (koth.getKoth() != null && koth.getKoth().getName() != null) {
                if (koth.getKoth().getName().equalsIgnoreCase("Super")) {
                    Bukkit.broadcastMessage(OahuUtils.translate("&cEthyl &8» &7Go to the Super KoTH with &6&l/warp Super&7!"));
                }
            }
        }
    }
}
