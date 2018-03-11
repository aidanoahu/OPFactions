package network.ethyl.opfactions.features;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.Lang;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Borders extends CommandBase {

    /*

    Command to display current World Borders

     */

    public Borders(OPFactionsCore core) {
        super(core, "borders", "/(command)");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
        p.sendMessage(OahuUtils.translate("&6&lBorders"));
        p.sendMessage(OahuUtils.translate("&cOverworld &7- &c10k"));
        p.sendMessage(OahuUtils.translate("&cNether &7- &c5k"));
        p.sendMessage(OahuUtils.translate("&cEnd &7- &c3k"));
        p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
    }
}
