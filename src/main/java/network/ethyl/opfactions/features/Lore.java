package network.ethyl.opfactions.features;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Lore extends CommandBase {

    /*

    Command to add a lore to a weapon in hand

     */

    public Lore(OPFactionsCore core) {
        super(core, "lore", "/(command) <text>");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        ItemStack hand = p.getItemInHand();
        ItemMeta handm = hand.getItemMeta();

        if (hand.getType() == null || handm == null) return;

        String msg = "";
        for (int i = 0; i < args.length; i++) {
            if (i + 1 != args.length) {
                msg = msg + args[i] + " ";
            } else {
                msg = msg + args[i];
            }
        }

        if (args.length == 0) {
            p.sendMessage(ChatColor.RED + "Usage: " + getUsage());
        }

        if (args.length >= 1) {
            if (p.getItemInHand().getType() == Material.DIAMOND_SWORD || p.getItemInHand().getType() == Material.DIAMOND_AXE
                    || p.getItemInHand().getType() == Material.DIAMOND_HELMET
                    || p.getItemInHand().getType() == Material.DIAMOND_CHESTPLATE
                    || p.getItemInHand().getType() == Material.DIAMOND_LEGGINGS
                    || p.getItemInHand().getType() == Material.DIAMOND_BOOTS
                    || p.getItemInHand().getType() == Material.BOW) {
                if (handm.hasLore()) {
                    List<String> lore = handm.getLore();
                    lore.add(OahuUtils.translate(msg));
                    handm.setLore(lore);
                    hand.setItemMeta(handm);
                    p.sendMessage(OahuUtils.translate("&7Successfully added lore '" + msg + "&7' to item in hand."));
                } else {
                    List<String> lore = new ArrayList<>();
                    lore.add(OahuUtils.translate(msg));
                    handm.setLore(lore);
                    hand.setItemMeta(handm);
                    p.sendMessage(OahuUtils.translate("&7Successfully added lore '" + msg + "&7' to item in hand."));
                }
            } else {
                p.sendMessage(OahuUtils.translate("&cYou can only add lore to Diamond Armor, Axes, Swords, and Bows!"));
            }
        }
    }
}
