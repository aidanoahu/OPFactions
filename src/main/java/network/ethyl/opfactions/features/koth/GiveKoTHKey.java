package network.ethyl.opfactions.features.koth;

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

public class GiveKoTHKey extends CommandBase {

    /*

    Give a koth crate key to a player

     */

    public GiveKoTHKey(OPFactionsCore core) {
        super(core, "givekothkey", "/(command) <player> <normal/challenge/super>");
    }

    public ItemStack getCrateKey(int amount) {
        ItemStack key = new ItemStack(Material.CLAY_BALL, amount);
        ItemMeta keym = key.getItemMeta();
        keym.setDisplayName(OahuUtils.translate("&cKoTH Key"));
        List<String> lore = new ArrayList<>();
        lore.add(OahuUtils.translate("&6&oUse at /warp KOTHCrate."));
        keym.setLore(lore);
        keym.addEnchant(Enchantment.DURABILITY, 1, true);
        key.setItemMeta(keym);
        return key;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(OahuUtils.translate("&cUsage: " + getUsage()));
        }

        if (args.length == 2) {
            Player p = Bukkit.getPlayer(args[0]);
            String koth = args[1];

            if (p == null || koth == null) {
                sender.sendMessage(OahuUtils.translate("&cInvalid arguments. Usage: " + getUsage()));
            } else {
                if (koth.equalsIgnoreCase("challenge")) {
                    p.getInventory().addItem(getCrateKey(3));
                    p.updateInventory();
                    p.sendMessage(OahuUtils.translate("&7You have received &63 KoTH Crate Keys &7for capturing &cChallenge KoTH&7!"));
                    p.sendMessage(OahuUtils.translate("&7Use them at &6/warp KOTHCrate&7!"));
                } else if (koth.equalsIgnoreCase("super")) {
                    p.getInventory().addItem(getCrateKey(3));
                    p.updateInventory();
                    p.sendMessage(OahuUtils.translate("&7You have received &63 KoTH Crate Keys &7for capturing &cSuper KoTH&7!"));
                    p.sendMessage(OahuUtils.translate("&7Use them at &6/warp KOTHCrate&7!"));
                } else {
                    p.getInventory().addItem(getCrateKey(1));
                    p.updateInventory();
                    p.sendMessage(OahuUtils.translate("&7You have received &61 KoTH Crate Key &7for capturing a &cKoTH&7!"));
                    p.sendMessage(OahuUtils.translate("&7Use them at &6/warp KOTHCrate&7!"));
                }
            }
        }
    }
}
