package network.ethyl.opfactions.features;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.Lang;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Discord extends CommandBase {

    /*

    Command to display the link to the server discord

     */

    public Discord(OPFactionsCore core) {
        super(core, "discord", "/(command)");
        
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
        p.sendMessage(OahuUtils.translate("&c&lDiscord"));
        p.sendMessage(OahuUtils.translate("&6&odiscord.gg/ygkZQMk"));
        p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
    }
}
