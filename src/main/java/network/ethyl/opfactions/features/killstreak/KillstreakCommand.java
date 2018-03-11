package network.ethyl.opfactions.features.killstreak;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.Lang;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillstreakCommand extends CommandBase {

    /*

    Command to list all killstreaks

     */

    public KillstreakCommand(OPFactionsCore core) {
        super(core, "killstreak", "/(command)");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
        p.sendMessage(OahuUtils.translate("&c5 Kills &7- &6Kit Thoth (Prot 75, Sharp 15)"));
        p.sendMessage(OahuUtils.translate("&c10 Kills &7- &61 Mystery Key"));
        p.sendMessage(OahuUtils.translate("&c15 Kills &7- &6$300,000"));
        p.sendMessage(OahuUtils.translate("&c20 Kills &7- &61 Ethyl Key"));
        p.sendMessage(OahuUtils.translate("&c25 Kills &7- &6Kit Geb (Prot 999, Sharp 30)"));
        p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
    }
}
