package network.ethyl.opfactions.features.tab;

import network.ethyl.opfactions.utils.PEX;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class TabListener implements Listener {

    /*

    Listeners to apply a player's tab list color upon join/chat

     */

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String playerGroup = PEX.getPlayerGroup(p);
        Tablist.apply(p, playerGroup);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String playerGroup = PEX.getPlayerGroup(p);
        Tablist.apply(p, playerGroup);
    }
}
