package network.ethyl.opfactions.features;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Rename extends CommandBase {

    /*

    Command to rename a weapon in hand

     */

    public Rename(OPFactionsCore core) {
        super(core, "rename", "/(command) <name>");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(OahuUtils.translate("&cUsage: " + getUsage()));
        }

        if (args.length >= 1) {

            String msg = "";
            for (int i = 0; i < args.length; i++) {
                if (i + 1 != args.length) {
                    msg = msg + args[i] + " ";
                } else {
                    msg = msg + args[i];
                }
            }

            if (p.getItemInHand() != null) {
                if (p.getItemInHand().getType() == Material.DIAMOND_SWORD || p.getItemInHand().getType() == Material.DIAMOND_AXE
                        || p.getItemInHand().getType() == Material.DIAMOND_HELMET
                        || p.getItemInHand().getType() == Material.DIAMOND_CHESTPLATE
                        || p.getItemInHand().getType() == Material.DIAMOND_LEGGINGS
                        || p.getItemInHand().getType() == Material.DIAMOND_BOOTS
                        || p.getItemInHand().getType() == Material.BOW) {
                    ItemStack hand = p.getItemInHand();
                    ItemMeta handMeta = hand.getItemMeta();
                    handMeta.setDisplayName(OahuUtils.translate(msg));
                    hand.setItemMeta(handMeta);
                    p.sendMessage(OahuUtils.translate("&7Item name successfully set to '&r" + msg + "&7'"));
                } else {
                    p.sendMessage(OahuUtils.translate("&cYou can only rename Diamond Armor, Axes, Swords, and Bows!"));
                }
            } else {
                p.sendMessage(OahuUtils.translate("&cYou must be holding something in your hand to use this command!"));
            }
        }
    }
}
