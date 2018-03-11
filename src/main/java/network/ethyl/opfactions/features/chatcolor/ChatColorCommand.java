package network.ethyl.opfactions.features.chatcolor;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChatColorCommand extends CommandBase {

    /*

    Command with GUI to change player chat color

     */

    public ChatColorCommand(OPFactionsCore core) {
        super(core, "chatcolor", "/(command)");
    }

    public ItemStack createButton(ItemStack stack, String name) {
        ItemMeta m = stack.getItemMeta();
        m.setDisplayName(OahuUtils.translate(name));
        stack.setItemMeta(m);
        return stack;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        Inventory inv = Bukkit.createInventory(null, 9, OahuUtils.translate("&6Chat Color"));
        ItemStack lightgreen = createButton(new ItemStack(Material.INK_SACK, 1, (short) 10), "&a&lLight Green");
        ItemStack lightblue = createButton(new ItemStack(Material.INK_SACK, 1, (short) 12), "&b&lLight Blue");
        ItemStack red = createButton(new ItemStack(Material.INK_SACK, 1, (short) 1), "&c&lRed");
        ItemStack pink = createButton(new ItemStack(Material.INK_SACK, 1, (short) 9), "&d&lPink");
        ItemStack yellow = createButton(new ItemStack(Material.INK_SACK, 1, (short) 11), "&e&lYellow");
        ItemStack green = createButton(new ItemStack(Material.INK_SACK, 1, (short) 2), "&2&lGreen");
        ItemStack turquoise = createButton(new ItemStack(Material.INK_SACK, 1, (short) 6), "&3&lTurquoise");
        ItemStack orange = createButton(new ItemStack(Material.INK_SACK, 1, (short) 14), "&6&lOrange / Gold");
        ItemStack blue = createButton(new ItemStack(Material.INK_SACK, 1, (short) 4), "&9&lBlue");

        inv.setItem(0, lightgreen);
        inv.setItem(1, lightblue);
        inv.setItem(2, red);
        inv.setItem(3, pink);
        inv.setItem(4, yellow);
        inv.setItem(5, green);
        inv.setItem(6, turquoise);
        inv.setItem(7, orange);
        inv.setItem(8, blue);

        p.openInventory(inv);

    }
}
