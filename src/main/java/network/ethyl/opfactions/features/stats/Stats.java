package network.ethyl.opfactions.features.stats;

import com.massivecraft.factions.FPlayers;
import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.Lang;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import network.ethyl.opfactions.utils.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class Stats extends CommandBase {

    /*

    Command to view stats of another player or yourself (kills, deaths, and more)

     */

    public Stats(OPFactionsCore core) {
        super(core, "stats", "/stats (player)");
    }

    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (args.length == 0) {
            PlayerConfig.load(p);
            double kills = PlayerConfig.get().getDouble("kills");
            double deaths = PlayerConfig.get().getDouble("deaths");
            double headWorth = PlayerConfig.get().getDouble("head value");
            double kdr;
            if (deaths == 0) {
                kdr = kills;
            } else if (kills == 0) {
                kdr = 0;
            } else {
                kdr = kills / deaths;
            }
            p.sendMessage(OahuUtils.translate("&8&m" + Lang.HEADER));
            p.sendMessage(OahuUtils.translate("&cKills &7» &6" + (int) kills));
            p.sendMessage(OahuUtils.translate("&cDeaths &7» &6" + (int) deaths));
            p.sendMessage(OahuUtils.translate("&cKDR &7» &6" + kdr));
            p.sendMessage(OahuUtils.translate("&cHead Worth &7» &6" + OPFactionsCore.getCore().formatMoney(headWorth)));
            if (FPlayers.getInstance().getByPlayer(p).getFaction() == null) {
                p.sendMessage(OahuUtils.translate("&cFaction &7» &2Wilderness"));
            } else {
                p.sendMessage(OahuUtils.translate("&cFaction &7» &6" + FPlayers.getInstance().getByPlayer(p).getFaction().getTag()));
            }
            p.sendMessage(OahuUtils.translate("&8&m" + Lang.HEADER));
        }

        if (args.length >= 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                OfflinePlayer arg = Bukkit.getOfflinePlayer(args[0]);
                if (PlayerConfig.isNull(arg.getUniqueId())) {
                    p.sendMessage(OahuUtils.translate("&cThat player has never logged onto the server."));
                } else  {
                    PlayerConfig.load(arg.getUniqueId());
                    double kills = PlayerConfig.get().getDouble("kills");
                    double deaths = PlayerConfig.get().getDouble("deaths");
                    double headWorth = PlayerConfig.get().getDouble("head value");
                    double kdr;
                    if (deaths == 0) {
                        kdr = kills;
                    } else if (kills == 0) {
                        kdr = 0;
                    } else {
                        kdr = kills / deaths;
                    }
                    p.sendMessage(OahuUtils.translate("&8&m" + Lang.HEADER));
                    p.sendMessage(OahuUtils.translate("&7» &6&oDisplaying stats for &c" + arg.getName() + "&6&o..."));
                    p.sendMessage(" ");
                    p.sendMessage(OahuUtils.translate("&cKills &7» &6" + (int) kills));
                    p.sendMessage(OahuUtils.translate("&cDeaths &7» &6" + (int) deaths));
                    p.sendMessage(OahuUtils.translate("&cKDR &7» &6" + kdr));
                    p.sendMessage(OahuUtils.translate("&cHead Worth &7» &6" + OPFactionsCore.getCore().formatMoney(headWorth)));
                    if (FPlayers.getInstance().getByOfflinePlayer(arg).getFaction() == null) {
                        p.sendMessage(OahuUtils.translate("&cFaction &7» &2Wilderness"));
                    } else {
                        p.sendMessage(OahuUtils.translate("&cFaction &7» &6" + FPlayers.getInstance().getByOfflinePlayer(arg).getFaction().getTag()));
                    }
                    p.sendMessage(OahuUtils.translate("&8&m" + Lang.HEADER));
                }
            } else {
                PlayerConfig.load(target);
                double kills = PlayerConfig.get().getDouble("kills");
                double deaths = PlayerConfig.get().getDouble("deaths");
                double headWorth = PlayerConfig.get().getDouble("head value");
                double kdr;
                if (deaths == 0) {
                    kdr = kills;
                } else if (kills == 0) {
                    kdr = 0;
                } else {
                    kdr = kills / deaths;
                }
                p.sendMessage(OahuUtils.translate("&8&m" + Lang.HEADER));
                p.sendMessage(OahuUtils.translate("&7» &6&oDisplaying stats for &c" + target.getName() + "&6&o..."));
                p.sendMessage(" ");
                p.sendMessage(OahuUtils.translate("&cKills &7» &6" + (int) kills));
                p.sendMessage(OahuUtils.translate("&cDeaths &7» &6" + (int) deaths));
                p.sendMessage(OahuUtils.translate("&cKDR &7» &6" + kdr));
                p.sendMessage(OahuUtils.translate("&cHead Worth &7» &6" + OPFactionsCore.getCore().formatMoney(headWorth)));
                if (FPlayers.getInstance().getByPlayer(target).getFaction() == null) {
                    p.sendMessage(OahuUtils.translate("&cFaction &7» &2Wilderness"));
                } else {
                    p.sendMessage(OahuUtils.translate("&cFaction &7» &6" + FPlayers.getInstance().getByPlayer(target).getFaction().getTag()));
                }
                p.sendMessage(OahuUtils.translate("&8&m" + Lang.HEADER));
            }
        }
    }
}
