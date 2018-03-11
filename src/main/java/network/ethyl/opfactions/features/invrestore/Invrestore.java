package network.ethyl.opfactions.features.invrestore;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Invrestore extends CommandBase {

    /*

    Command to restore a player's inventory from before a death

     */

    public Invrestore(OPFactionsCore core) {
        super(core, "invrestore", "/(command) <player>");
    }

    static Map<String, ItemStack[]> inventories = new HashMap<>();
    static Map<String, ItemStack[]> armor = new HashMap<>();

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: " + getUsage());
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(OahuUtils.translate("Please specify a valid player."));
            } else {
                if (!inventories.containsKey(target.getName()) || !armor.containsKey(target.getName())) {
                    sender.sendMessage(OahuUtils.translate("&cThat player has not died recently."));
                } else {
                    target.getInventory().setContents(inventories.get(target.getName()));
                    target.getInventory().setArmorContents(armor.get(target.getName()));
                    target.sendMessage(OahuUtils.translate("&aYour inventory has been restored from your previous death."));
                    sender.sendMessage(OahuUtils.translate("&7Restored &6" + target.getName() + "&7's previous inventory."));
                }
            }
        }
    }
}
