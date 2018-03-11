package network.ethyl.opfactions.features.stats;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import network.ethyl.opfactions.utils.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsResetCommand extends CommandBase {

    /*

    Command to reset a player's stats

     */

    public StatsResetCommand(OPFactionsCore core) {
        super(core, "statsreset", "/statsreset <player>");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(OahuUtils.translate("&cUsage: " + getUsage()));
        }
        if (args.length >= 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(OahuUtils.translate("&cPlease specify a valid player."));
            } else {
                PlayerConfig.load(target);
                PlayerConfig.get().set("kills", 0);
                PlayerConfig.get().set("deaths", 0);
                PlayerConfig.get().set("head value", 0);
                PlayerConfig.save();
                sender.sendMessage(OahuUtils.translate("&6Stats have been reset for &c" + target.getName()));
            }
        }
    }
}
