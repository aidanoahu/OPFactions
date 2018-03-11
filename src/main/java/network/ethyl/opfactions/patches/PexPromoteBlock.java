package network.ethyl.opfactions.patches;

import me.aidan.lib.api.OahuUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PexPromoteBlock implements Listener {

    /*

    Listener to patch the pex promote command that lags the server

     */

    @EventHandler
    public void onCmdPreProcess(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().startsWith("/") && e.getMessage().contains("pex promote")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(OahuUtils.translate("&cThis command is blocked."));
        }
    }

}
