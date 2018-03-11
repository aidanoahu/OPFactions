package network.ethyl.opfactions.features.voting;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import me.aidan.lib.api.Lang;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;

public class VotePartyListener implements Listener {

    /*

    Listener that fires upon player vote that checks if there are enough votes for a vote party!

     */

    @EventHandler
    public void onVote(VotifierEvent e) {
        Vote vote = e.getVote();
        Player p = Bukkit.getPlayer(vote.getUsername());
        FileConfiguration config = OPFactionsCore.getCore().getConfig();

        if (config.getInt("vote party") == 49) {
            config.set("vote party", config.getInt("vote party") + 1);
            try {
                config.save(OPFactionsCore.getCore().getDataFolder() + File.separator + "config.yml");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Bukkit.broadcastMessage(OahuUtils.translate("&8&m" + Lang.HEADER));
            Bukkit.broadcastMessage(OahuUtils.translate("&4&l** &6&lVOTE PARTY &4&l**"));
            Bukkit.broadcastMessage(OahuUtils.translate("&7Everyone on the server has received a &cMystery Key &7for"));
            Bukkit.broadcastMessage(OahuUtils.translate("&7reaching the goal of &650 votes&7!"));
            Bukkit.broadcastMessage(OahuUtils.translate("&8&m" + Lang.HEADER));
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate ga Mystery 1");
            config.set("vote party", 0);
            try {
                config.save(OPFactionsCore.getCore().getDataFolder() + File.separator + "config.yml");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            config.set("vote party", config.getInt("vote party") + 1);
            try {
                config.save(OPFactionsCore.getCore().getDataFolder() + File.separator + "config.yml");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }
}
