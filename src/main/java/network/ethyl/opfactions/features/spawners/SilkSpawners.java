package network.ethyl.opfactions.features.spawners;

import me.aidan.lib.api.OahuUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SilkSpawners implements Listener {

    /*

    Listener so players can mine spawners with a silk touch pickaxe

     */

    private int getCreatureID(CreatureSpawner spawner) {
        int id = 0;
        switch (spawner.getCreatureTypeName()) {
            case "sheep":
                break;
            case "creeper":
                id = 50;
                break;
            case "skeleton":
                id = 51;
                break;
            case "spider":
                id = 52;
                break;
            case "giant":
                id = 53;
                break;
            case "zombie":
                id = 54;
                break;
            case "slime":
                id = 55;
                break;
            case "ghast":
                id = 56;
                break;
            case "pigman":
                id = 57;
                break;
            case "enderman":
                id = 58;
                break;
            case "cavespider":
                id = 59;
                break;
            case "silverfish":
                id = 60;
                break;
            case "blaze":
                id = 61;
                break;
            case "magmacube":
                id = 62;
                break;
            case "enderdragon":
                id = 63;
                break;
            case "pig":
                id = 90;
                break;
            case "cow":
                id = 92;
                break;
            case "chicken":
                id = 93;
                break;
            case "squid":
                id = 94;
                break;
            case "wolf":
                id = 95;
                break;
            case "mooshroom":
                id = 96;
                break;
            case "snowgolem":
                id = 97;
                break;
            case "ocelot":
                id = 98;
                break;
            case "villager":
                id = 98;
                break;
            case "irongolem":
                id = 99;
                break;
        }
        return id;
    }

    public ItemStack spawner(CreatureSpawner s) {
        short metaData = (short) getCreatureID(s);
        ItemStack stack = new ItemStack(Material.MOB_SPAWNER, 1, metaData);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.RED + s.getCreatureTypeName().toUpperCase() + " SPAWNER");
        stack.setItemMeta(meta);
        return stack;
    }

    public ItemStack specialSpawner(CreatureSpawner s, String name) {
        short metaData = (short) getCreatureID(s);
        ItemStack stack = new ItemStack(Material.MOB_SPAWNER, 1, metaData);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.RED + name + " SPAWNER");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        World w = p.getWorld();
        Location bLoc = b.getLocation();
        ItemStack hand = p.getItemInHand();

        if (hand == null || b.getState() == null || hand.getItemMeta() == null) return;

        if (b.getState() instanceof CreatureSpawner) {
            CreatureSpawner spawner = (CreatureSpawner) b.getState();
            if (hand.containsEnchantment(Enchantment.SILK_TOUCH)) {
                if (b.getType() == Material.MOB_SPAWNER) {
                    if (spawner.getCreatureTypeName().equalsIgnoreCase("villagergolem")) {
                        w.dropItemNaturally(bLoc, specialSpawner(spawner, "IRONGOLEM"));
                    } else if (spawner.getCreatureTypeName().equalsIgnoreCase("pigzombie")) {
                        w.dropItemNaturally(bLoc, specialSpawner(spawner, "PIGMAN"));
                    } else {
                        w.dropItemNaturally(bLoc, spawner(spawner));
                    }
                }
            }
        }
    }
}
