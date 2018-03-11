package network.ethyl.opfactions.features.koth;

import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import network.ethyl.opfactions.utils.ColoredName;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrateListener implements Listener {

    /*

    Utility methods for KoTH crate and listeners

     */

    public static List<String> placing = new ArrayList<>();

    public void setSpawner(Block block, EntityType entityType) {
        BlockState blockState = block.getState();
        CreatureSpawner spawner = ((CreatureSpawner) blockState);
        spawner.setSpawnedType(entityType);
        blockState.update();
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlockPlaced();
        ItemStack hand = p.getItemInHand();
        if (b == null || hand == null || hand.getItemMeta() == null || hand.getItemMeta().getDisplayName() == null) return;

        if (b.getType() == Material.MOB_SPAWNER && hand.getType() == Material.MOB_SPAWNER) {
            ItemMeta handm = hand.getItemMeta();
            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cIron Golem Spawner"))) {
                setSpawner(b, EntityType.IRON_GOLEM);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cBlaze Spawner"))) {
                setSpawner(b, EntityType.BLAZE);
            }
        }

        if (b.getType() == Material.ENDER_PORTAL_FRAME) {
            if (placing.contains(p.getName())) {
                FileConfiguration config = OPFactionsCore.getCore().getConfig();
                config.set("koth crate.x", e.getBlockPlaced().getX());
                config.set("koth crate.y", e.getBlockPlaced().getY());
                config.set("koth crate.z", e.getBlockPlaced().getZ());
                config.set("koth crate.world", e.getBlockPlaced().getWorld().getName());
                try {
                    config.save(OPFactionsCore.getCore().getDataFolder() + File.separator + "config.yml");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                p.sendMessage(OahuUtils.translate("&aLocation for KoTH Crate successfully saved."));
                placing.remove(p.getName());
                p.getInventory().remove(KothCrateAPI.getCrate());
                p.updateInventory();
            }
        }

        if (hand.getItemMeta().getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cKoTH Key")) || hand.getType() == Material.TRIPWIRE_HOOK) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        FileConfiguration config = OPFactionsCore.getCore().getConfig();
        Location crate = new Location(Bukkit.getWorld(config.getString("koth crate.world")), config.getInt("koth crate.x"), config.getInt("koth crate.y"), config.getInt("koth crate.z"));

        if (b == null) return;
        if (b.getLocation() == null) return;
        if (p.getItemInHand().getType() != Material.CLAY_BALL) return;
        if (p.getItemInHand().getItemMeta() == null) return;
        if (p.getItemInHand().getItemMeta().getDisplayName() == null) return;

        if (b.getLocation().getBlockX() == config.getInt("koth crate.x")
                && b.getLocation().getBlockY() == config.getInt("koth crate.y")
                && b.getLocation().getBlockZ() == config.getInt("koth crate.z")
                && b.getLocation().getWorld() == Bukkit.getWorld(config.getString("koth crate.world"))) {

            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (p.getItemInHand().getItemMeta().getDisplayName().equals(OahuUtils.translate("&cKoTH Key"))) {
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(OahuUtils.translate("&c" + p.getName() + " &6is opening a KoTH crate..."));
                    Bukkit.broadcastMessage(" ");
                    if (p.getItemInHand().getAmount() == 1) {
                        p.getInventory().remove(KothCrateAPI.getCrateKey());
                    } else if (p.getItemInHand().getAmount() >= 2) {
                        p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
                    }
                    p.getWorld().playEffect(crate, Effect.VILLAGER_THUNDERCLOUD, 1);
                    p.getWorld().playSound(p.getLocation(), Sound.CHEST_OPEN, 1, 1);
                    e.setCancelled(true);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(OPFactionsCore.getCore(), new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder loot = new StringBuilder();
                            for (ItemStack stack : KothCrateAPI.getKoTHWinnings()) {
                                String name = stack.getItemMeta().getDisplayName();
                                loot.append(name).append(OahuUtils.translate("&7, "));
                                p.getInventory().addItem(stack);
                            }

                            Bukkit.broadcastMessage(" ");
                            Bukkit.broadcastMessage(OahuUtils.translate("&c" + p.getName() + " &7has received "
                                    + loot.substring(0, loot.length() - 2) + "&7."));
                            Bukkit.broadcastMessage(" ");

                        }
                    }, (5 * 20));
                } else {
                    p.sendMessage(OahuUtils.translate("&cYou must be holding a KoTH Crate Key to use the KoTH Crate!"));
                }
            }

        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().getName().equalsIgnoreCase(ChatColor.RED + "KoTH Crate Loot")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        FileConfiguration config = OPFactionsCore.getCore().getConfig();

        if (b.getLocation().getBlockX() == config.getInt("koth crate.x")
                && b.getLocation().getBlockY() == config.getInt("koth crate.y")
                && b.getLocation().getBlockZ() == config.getInt("koth crate.z")
                && b.getLocation().getWorld() == Bukkit.getWorld(config.getString("koth crate.world"))) {
            config.set("koth crate.x", 0);
            config.set("koth crate.y", 0);
            config.set("koth crate.z", 0);
            config.set("koth crate.world", "null");
            try {
                config.save(OPFactionsCore.getCore().getDataFolder() + File.separator + "config.yml");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            p.sendMessage(OahuUtils.translate("&cYou have just destroyed the KoTH Crate. Location removed from file."));
        }
    }
}
