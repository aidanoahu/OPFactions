package network.ethyl.opfactions.features;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.Lang;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help extends CommandBase {

    /*

    Command to display some help messages for a new player

     */

    public Help(OPFactionsCore core) {
        super (core, "help", "/(command)");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
        p.sendMessage(OahuUtils.translate("&c&lEthyl OP Factions Help"));
        p.sendMessage(OahuUtils.translate("&7This gamemode is based around PvP and raiding."));
        p.sendMessage(OahuUtils.translate("&7To go to a random place in the wild to build your base use &6&o/wild&7!"));
        p.sendMessage(OahuUtils.translate("&7To get armor for PvP and materials for your base or raiding use &6&o/kit&7."));
        p.sendMessage(OahuUtils.translate("&7Faction commands can be found with &6&o/f&7."));
        p.sendMessage(OahuUtils.translate("&7Go to &6&o/warp pvp &7and &6&o/warp fps &7for quick PvP."));
        p.sendMessage(OahuUtils.translate("&7Find out when the next KoTH is taking place with &6&o/koth schedule&7."));
        p.sendMessage(OahuUtils.translate("&7Default rank can fix their gear every 30 minutes with &6&o/fix all&7."));
        p.sendMessage(OahuUtils.translate("&7Donate to get an advantage on your enemies with &6&o/buy&7."));
        p.sendMessage(OahuUtils.translate("&7Join our Discord to find out when resets or events will happen at &6&odiscord.gg/ygkZQMk&7."));
        p.sendMessage(OahuUtils.translate("&7Follow us on Twitter to find out about giveaways and more at &6&otwitter.com/EthylNetwork&7."));
        p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
    }
}
