package network.ethyl.opfactions.features.koth;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import network.ethyl.opfactions.utils.ColoredName;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class KoTHLoot extends CommandBase {

    /*

    Command to show all possible koth crate winnings

     */

    public KoTHLoot(OPFactionsCore core) {
        super(core, "kothloot", "/(command)");
    }



    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.RED + "KoTH Crate Loot");
        for (ItemStack stack : KothCrateAPI.getKoTHLoot()) {
            inv.addItem(stack);
        }

        if (inv == null) return;

        p.openInventory(inv);
    }
}
