package network.ethyl.opfactions.features.perks;

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

import java.util.ArrayList;
import java.util.List;

public class PerksCommand extends CommandBase {

    public PerksCommand(OPFactionsCore core) {
        super(core, "perks", "/(command)");
    }

    public ItemStack createButton(ItemStack stack, String name, String permission, Player p) {
        ItemMeta m = stack.getItemMeta();
        m.setDisplayName(OahuUtils.translate(name));
        List<String> lore = new ArrayList<>();
        if (!p.hasPermission(permission)) {
            lore.add(OahuUtils.translate("&cYou do not have this perk!"));
            lore.add(OahuUtils.translate("&6Purchase at store.ethyl.network!"));
        } else {
            lore.add(OahuUtils.translate("&aYou have this perk."));
        }
        m.setLore(lore);
        stack.setItemMeta(m);
        return stack;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        Inventory perks = Bukkit.createInventory(null, 9, OahuUtils.translate("&6Perks"));

        ItemStack fire = createButton(new ItemStack(Material.FIRE, 1), "&6&lFire Immunity", "immunity.fire", p);
        ItemStack water = createButton(new ItemStack(Material.WATER, 1), "&b&lWater Immunity", "immunity.water", p);
        ItemStack jellylegs = createButton(new ItemStack(Material.IRON_BOOTS, 1), "&f&lJelly Legs", "perks.jellylegs", p);
        ItemStack fly = createButton(new ItemStack(Material.FEATHER, 1), "&3&l/Fly Command", "essentials.fly", p);
        ItemStack near = createButton(new ItemStack(Material.COMPASS, 1), "&4&l/Near Command", "essentials.near", p);
        ItemStack fixall = createButton(new ItemStack(Material.ANVIL, 1), "&d&l/Fix All Command", "essentials.repair.all", p);
        ItemStack icmd = createButton(new ItemStack(Material.COBBLESTONE, 1), "&5&l/I Command", "essentials.item", p);
        ItemStack unlimitedhomes = createButton(new ItemStack(Material.BED, 1), "&c&lUnlimited Sethomes", "essentials.sethome.multiple.unlimited", p);
        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        ItemMeta fm = filler.getItemMeta();
        fm.setDisplayName(" ");
        filler.setItemMeta(fm);

        perks.setItem(0, fire);
        perks.setItem(1, water);
        perks.setItem(2, jellylegs);
        perks.setItem(3, fly);
        perks.setItem(4, near);
        perks.setItem(5, fixall);
        perks.setItem(6, icmd);
        perks.setItem(7, unlimitedhomes);
        perks.setItem(8, filler);

        p.openInventory(perks);
    }
}
