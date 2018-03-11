package network.ethyl.opfactions.features.scoreboard.provider;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import network.ethyl.opfactions.features.duels.DuelCommand;
import network.ethyl.opfactions.features.killstreak.KillstreakListener;
import network.ethyl.opfactions.features.lms.LMSCommand;
import network.ethyl.opfactions.features.lms.LMSListener;
import network.ethyl.opfactions.features.scoreboard.ScoreboardEntryProvider;
import network.ethyl.opfactions.utils.PEX;
import network.ethyl.opfactions.utils.Time;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import subside.plugins.koth.KothPlugin;
import subside.plugins.koth.gamemodes.RunningKoth;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SidebarProvider implements ScoreboardEntryProvider {

    /*

    SCOREBOARD API by DEATHSTREAMS
    https://github.com/Deathstreams/ScoreboardWrapper

     */

    @Override
    public List<String> getLines(Player player) {

        List<String> lines = new ArrayList<>();

        RunningKoth koth = JavaPlugin.getPlugin(KothPlugin.class).getKothHandler().getRunningKoth();
        int votes = OPFactionsCore.getCore().getConfig().getInt("vote party");

        lines.add(0, OahuUtils.translate("&7&m------------------------"));
        lines.add(OahuUtils.translate("&cStarted&7: 12/23/17"));
        lines.add(OahuUtils.translate("&cIP&7: ethyl.network"));

        if (PEX.getPlayerGroup(player).equalsIgnoreCase("default")) {
            lines.add(OahuUtils.translate("&cRank&7: Default"));
        } else {
            lines.add(OahuUtils.translate("&cRank&7: " + PEX.getPlayerGroup(player)));
        }

        lines.add(OahuUtils.translate("&cVote Party&7: " + votes + "/50"));

        if (LMSCommand.playersInQueue.contains(player.getName())) {
            long startingIn = Math.abs(LMSCommand.startingAt - System.currentTimeMillis());
            lines.add(OahuUtils.translate("&7&m------------------------"));
            lines.add(OahuUtils.translate("&6&lLMS"));
            lines.add(OahuUtils.translate("&7» &cStatus&7: Queue"));
            lines.add(OahuUtils.translate("&7» &cPlayers&7: " + LMSCommand.playersInQueue.size()));
            lines.add(OahuUtils.translate("&7» &cStarting&7: " + Time.convertMillis(startingIn, true)));
        } else if (LMSCommand.playersInGame.contains(player.getName())) {
            lines.add(OahuUtils.translate("&7&m------------------------"));
            lines.add(OahuUtils.translate("&6&lLMS"));
            lines.add(OahuUtils.translate("&7» &cStatus&7: In-Game"));
            lines.add(OahuUtils.translate("&7» &cRemaining&7: " + LMSCommand.playersLeft));
            lines.add(OahuUtils.translate("&7» &cKills&7: " + LMSListener.kills.get(player.getName())));
        } else if (koth != null && !LMSCommand.playersInGame.contains(player.getName()) && !LMSCommand.playersInQueue.contains(player.getName())) {
            lines.add(OahuUtils.translate("&7&m------------------------"));
            if (koth.getKoth().getName().equalsIgnoreCase("Super")) {
                lines.add(OahuUtils.translate("&6&l" + koth.getKoth().getName() + " &7&o(/warp Super)"));
            } else if (koth.getKoth().getName().equalsIgnoreCase("FPS")) {
                lines.add(OahuUtils.translate("&6&l" + koth.getKoth().getName() + " &7&o(/warp FPS)"));
            } else {
                lines.add(OahuUtils.translate("&6&l" + koth.getKoth().getName() + " &7&o(" + koth.getKoth().getMiddle().getBlockX() + ", " + koth.getKoth().getMiddle().getBlockZ() + ")"));
            }

            if (koth.getCapper() == null) {
                lines.add(OahuUtils.translate("&7» &cCapper&7: None"));
            } else {
                lines.add(OahuUtils.translate("&7» &cCapper&7: " + koth.getCapper().getName()));
            }
            lines.add(OahuUtils.translate("&7» &cRemaining&7: " + koth.getTimeObject().getTimeLeftFormatted()));
        }

        lines.add(lines.size(), OahuUtils.translate("&7&m------------------------"));

        return lines;
    }


    @Override
    public String getTitle() {
        return OahuUtils.translate("&c&lOP Factions &6[Map 1]");
    }
}
