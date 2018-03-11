package network.ethyl.opfactions.features;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.Lang;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reddit extends CommandBase {

    /*

    Command that displays a link to the server reddit

     */

    public Reddit(OPFactionsCore core) {
        super(core, "reddit", "/(command)");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
        p.sendMessage(OahuUtils.translate("&c&lReddit"));
        p.sendMessage(OahuUtils.translate("&6&oreddit.ethyl.network"));
        p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
    }
}
