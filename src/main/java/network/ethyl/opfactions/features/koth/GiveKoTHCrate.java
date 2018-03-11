package network.ethyl.opfactions.features.koth;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveKoTHCrate extends CommandBase {

    /*

    Admin command to get a koth crate

     */

    public GiveKoTHCrate(OPFactionsCore core) {
        super(core, "givekothcrate", "/(command) <player>");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(OahuUtils.translate("&cUsage: " + getUsage()));
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(OahuUtils.translate("&cPlease specify a valid player."));
            } else {
                target.getInventory().addItem(KothCrateAPI.getCrate());
                target.sendMessage(OahuUtils.translate("&aYou have received the KoTH Crate."));
                target.sendMessage(OahuUtils.translate("&aUpon placing, coordinates will be saved."));
                target.sendMessage(OahuUtils.translate("&aIf you place it more than once, the previous location(s) will be overwritten."));
                CrateListener.placing.add(target.getName());
            }
        }
    }
}
