package network.ethyl.opfactions.features;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PotionsCommand extends CommandBase {

    /*

    Command to give you a stack of speed 2 and strength 2 4:00 potions

     */

    public PotionsCommand(OPFactionsCore core) {
        super(core, "potions", "/(command)");
    }

    public void createStack(ItemStack stack, String name) {
        ItemMeta m = stack.getItemMeta();
        m.setDisplayName(OahuUtils.translate(name));
        stack.setItemMeta(m);
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        ItemStack speed = new ItemStack(Material.POTION, 64, (short) 8290);
        ItemStack strength = new ItemStack(Material.POTION, 64, (short) 8297);

        p.getInventory().addItem(speed, strength);
        p.sendMessage(OahuUtils.translate("&7You have received &664x Speed II &7& &664x Strength II&7!"));
    }
}
