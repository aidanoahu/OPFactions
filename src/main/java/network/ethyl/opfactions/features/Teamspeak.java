package network.ethyl.opfactions.features;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.Lang;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teamspeak extends CommandBase {

    /*

    Command to display the server teamspeak ip

     */

    public Teamspeak(OPFactionsCore core) {
        super(core, "teamspeak", "/(command)");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
        p.sendMessage(OahuUtils.translate("&c&lTeamspeak"));
        p.sendMessage(OahuUtils.translate("&6&ots.ethyl.network"));
        p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
    }
}
