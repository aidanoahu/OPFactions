package network.ethyl.opfactions.features.koth;

import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.utils.ColoredName;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KothCrateAPI {

    public static void createArmorLootEntry(ItemStack stack, String name, int level, List<ItemStack> list) {
        ItemMeta m = stack.getItemMeta();
        m.setDisplayName(OahuUtils.translate(name));
        m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, level, true);
        m.addEnchant(Enchantment.DURABILITY, level, true);
        m.addEnchant(Enchantment.PROTECTION_FIRE, level, true);
        m.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, level, true);
        stack.setItemMeta(m);
        list.add(stack);
    }

    public static void createWeaponLootEntry(ItemStack stack, String name, int level, List<ItemStack> list) {
        ItemMeta m = stack.getItemMeta();
        m.setDisplayName(OahuUtils.translate(name));
        m.addEnchant(Enchantment.DAMAGE_ALL, level, true);
        m.addEnchant(Enchantment.DURABILITY, level, true);
        m.addEnchant(Enchantment.FIRE_ASPECT, level, true);
        stack.setItemMeta(m);
        list.add(stack);
    }

    public static void createKnockbackWeaponLootEntry(ItemStack stack, String name, int level, List<ItemStack> list) {
        ItemMeta m = stack.getItemMeta();
        m.setDisplayName(OahuUtils.translate(name));
        m.addEnchant(Enchantment.KNOCKBACK, level, true);
        stack.setItemMeta(m);
        list.add(stack);
    }

    public static void createLootEntry(ItemStack stack, String name, List<ItemStack> list) {
        ItemMeta m = stack.getItemMeta();
        m.setDisplayName(OahuUtils.translate(name));
        stack.setItemMeta(m);
        list.add(stack);
    }

    public static ItemStack getCrateKey() {
        ItemStack key = new ItemStack(Material.CLAY_BALL, 1);
        ItemMeta keym = key.getItemMeta();
        keym.setDisplayName(OahuUtils.translate("&cKoTH Key"));
        List<String> lore = new ArrayList<>();
        lore.add(OahuUtils.translate("&6&oUse at /warp KOTHCrate."));
        keym.setLore(lore);
        keym.addEnchant(Enchantment.DURABILITY, 1, true);
        key.setItemMeta(keym);
        return key;
    }

    public static List<ItemStack> getKoTHWinnings() {
        List<ItemStack> winnings = new ArrayList<>();
        winnings.add(getKoTHLoot().get(new Random().nextInt(getKoTHLoot().size())));
        winnings.add(getKoTHLoot().get(new Random().nextInt(getKoTHLoot().size())));
        winnings.add(getKoTHLoot().get(new Random().nextInt(getKoTHLoot().size())));
        winnings.add(getKoTHLoot().get(new Random().nextInt(getKoTHLoot().size())));
        return winnings;
    }

    public static ItemStack getCrate() {
        ItemStack crate = new ItemStack(Material.ENDER_PORTAL_FRAME, 1);
        ItemMeta cratem = crate.getItemMeta();
        cratem.setDisplayName(OahuUtils.translate("&cKoTH Crate &7&o(One Time Use)"));
        crate.setItemMeta(cratem);

        return crate;
    }

    public static List<ItemStack> getKoTHLoot() {
        List<ItemStack> allLoot = new ArrayList<>();

        createKnockbackWeaponLootEntry(new ItemStack(Material.STICK, 1), "&dGrandpa's Cane", 2, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_HELMET, 1), ColoredName.VENGEANCE, 700, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_CHESTPLATE, 1), ColoredName.VENGEANCE, 700, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_LEGGINGS, 1), ColoredName.VENGEANCE, 700, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_BOOTS, 1), ColoredName.VENGEANCE, 700, allLoot);
        createWeaponLootEntry(new ItemStack(Material.DIAMOND_SWORD, 1), ColoredName.VENGEANCE, 27, allLoot);
        createWeaponLootEntry(new ItemStack(Material.DIAMOND_AXE, 1), ColoredName.VENGEANCE, 27, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_HELMET, 1), ColoredName.RA, 777, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_CHESTPLATE, 1), ColoredName.RA, 777, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_LEGGINGS, 1), ColoredName.RA, 777, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_BOOTS, 1), ColoredName.RA, 777, allLoot);
        createWeaponLootEntry(new ItemStack(Material.DIAMOND_SWORD, 1), ColoredName.RA, 28, allLoot);
        createWeaponLootEntry(new ItemStack(Material.DIAMOND_AXE, 1), ColoredName.RA, 28, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_HELMET, 1), ColoredName.GEB, 999, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_CHESTPLATE, 1), ColoredName.GEB, 999, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_LEGGINGS, 1), ColoredName.GEB, 999, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_BOOTS, 1), ColoredName.GEB, 999, allLoot);
        createWeaponLootEntry(new ItemStack(Material.DIAMOND_SWORD, 1), ColoredName.GEB, 30, allLoot);
        createWeaponLootEntry(new ItemStack(Material.DIAMOND_AXE, 1), ColoredName.GEB, 30, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_HELMET, 1), ColoredName.PTAH, 1250, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_CHESTPLATE, 1), ColoredName.PTAH, 1250, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_LEGGINGS, 1), ColoredName.PTAH, 1250, allLoot);
        createArmorLootEntry(new ItemStack(Material.DIAMOND_BOOTS, 1), ColoredName.PTAH, 1250, allLoot);
        createWeaponLootEntry(new ItemStack(Material.DIAMOND_SWORD, 1), ColoredName.PTAH, 32, allLoot);
        createWeaponLootEntry(new ItemStack(Material.DIAMOND_AXE, 1), ColoredName.PTAH, 32, allLoot);
        createLootEntry(new ItemStack(Material.MOB_SPAWNER, 3), "&c3x Blaze Spawner", allLoot);
        createLootEntry(new ItemStack(Material.MOB_SPAWNER, 1), "&cIron Golem Spawner", allLoot);
        createLootEntry(new ItemStack(Material.EMERALD, 3), "&dKit Key", allLoot);
        createLootEntry(new ItemStack(Material.GOLD_NUGGET, 3), "&6McMMO Key", allLoot);
        createLootEntry(new ItemStack(Material.TRIPWIRE_HOOK, 1), "&5Mystery Key", allLoot);
        createLootEntry(new ItemStack(Material.NETHER_STAR, 1), "&cEthyl Key", allLoot);
        createLootEntry(new ItemStack(Material.INK_SACK, 1, (short) 12), "&bWinter Key", allLoot);

        return allLoot;
    }
}
