package network.ethyl.opfactions.features;

import me.aidan.lib.api.Lang;
import me.aidan.lib.api.OahuUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoinMessage implements Listener {

    /*

    Message that sends to the player upon join if they have never joined before

     */

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPlayedBefore()) {
            p.sendMessage(OahuUtils.translate("&8&m" + Lang.HEADER));
            p.sendMessage(OahuUtils.translate("&c&lWelcome to Ethyl OP Factions!"));
            p.sendMessage(OahuUtils.translate("&7We are an OP Factions server mainly based around PvP."));
            p.sendMessage(OahuUtils.translate("&7You can use &6&o/kit &7to get kits to PvP!"));
            p.sendMessage(OahuUtils.translate("&7Use &6&o/help &7if you need help with anything regarding the server!"));
            p.sendMessage(OahuUtils.translate("&8&m" + Lang.HEADER));
        }
    }
}
