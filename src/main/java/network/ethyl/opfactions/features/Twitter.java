package network.ethyl.opfactions.features;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.Lang;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Twitter extends CommandBase {

    /*

    Command to display the @ and link to the server twitter

     */

    public Twitter(OPFactionsCore core) {
        super(core, "twitter", "/(command)");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
        p.sendMessage(OahuUtils.translate("&c&lTwitter &7&o(@EthylNetwork)"));
        p.sendMessage(OahuUtils.translate("&6&otwitter.com/ethylnetwork"));
        p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
    }
}
