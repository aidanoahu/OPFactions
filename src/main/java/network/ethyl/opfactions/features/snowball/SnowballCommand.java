package network.ethyl.opfactions.features.snowball;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SnowballCommand extends CommandBase {

    /*

    Special command to give winter event snowballs

     */

    public SnowballCommand(OPFactionsCore core) {
        super(core, "snowball", "/(command) <player> <amount>");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(OahuUtils.translate("&cUsage: " + getUsage()));
        }

        if (args.length == 2) {
            Player target = Bukkit.getPlayer(args[0]);
            int amount = Integer.parseInt(args[1]);

            if (target == null) {
                sender.sendMessage(OahuUtils.translate("&cPlease specify a valid player."));
            }

            if (amount <= 0) {
                sender.sendMessage(OahuUtils.translate("&cPlease specify a valid amount."));
            }

            ItemStack snowball = new ItemStack(Material.SNOW_BALL, amount);
            ItemMeta snowballMeta = snowball.getItemMeta();
            snowballMeta.setDisplayName(OahuUtils.translate("&b&l&oGUSH OF FREEZING COLD WIND"));
            List<String> lore = new ArrayList<>();
            lore.add(OahuUtils.translate("&b&nLeft Click Only"));
            snowballMeta.setLore(lore);
            snowballMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
            snowballMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            snowball.setItemMeta(snowballMeta);

            target.getInventory().addItem(snowball);
            target.sendMessage(OahuUtils.translate("&7You have received " + amount + " special snowballs."));
        }
    }
}
