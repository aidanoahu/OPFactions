package network.ethyl.opfactions.features.tab;

import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.entity.Player;

public class Tablist {

    /*

    Method to change the color of a player's name based on their PEX rank

     */

    public static void apply(Player player, String playerGroup) {
        String longDisplayName = OPFactionsCore.getCore().getConfig().getString("tabcolor." + playerGroup) + player.getName();
        String displayName = longDisplayName;
        if (longDisplayName.length() > 16) {
            displayName = longDisplayName.substring(0, 16);
        }
        player.setPlayerListName(displayName);
    }
}
