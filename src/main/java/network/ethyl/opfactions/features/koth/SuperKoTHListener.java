package network.ethyl.opfactions.features.koth;

import me.aidan.lib.api.OahuUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import subside.plugins.koth.events.KothInitializeEvent;

public class SuperKoTHListener implements Listener {

    @EventHandler
    public void onKoTHStart(KothInitializeEvent e) {
        if (e.getKoth() == null || e.getKoth().getName() == null) return;
        if (e.getKoth().getName().equalsIgnoreCase("Super")) {
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage(OahuUtils.translate("&4&l** &6&lSUPER KOTH &4&l**"));
            Bukkit.broadcastMessage(OahuUtils.translate("&c&oThe Super KoTH has started!"));
            Bukkit.broadcastMessage(OahuUtils.translate("&c&oUse &6&l/warp Super &c&oto go to the arena."));
            Bukkit.broadcastMessage(" ");
        }
    }
}
