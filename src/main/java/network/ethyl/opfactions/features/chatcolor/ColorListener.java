package network.ethyl.opfactions.features.chatcolor;

import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.utils.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class ColorListener implements Listener {

    /*

    Listeners for the chatcolor command

     */

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        PlayerConfig.load(p);

        if (PlayerConfig.get().getString("settings.chatcolor").equalsIgnoreCase("lightgreen")) {
            e.setMessage(ChatColor.GREEN + e.getMessage());
        } else if (PlayerConfig.get().getString("settings.chatcolor").equalsIgnoreCase("lightblue")) {
            e.setMessage(ChatColor.AQUA + e.getMessage());
        } else if (PlayerConfig.get().getString("settings.chatcolor").equalsIgnoreCase("red")) {
            e.setMessage(ChatColor.RED + e.getMessage());
        } else if (PlayerConfig.get().getString("settings.chatcolor").equalsIgnoreCase("pink")) {
            e.setMessage(ChatColor.LIGHT_PURPLE + e.getMessage());
        } else if (PlayerConfig.get().getString("settings.chatcolor").equalsIgnoreCase("yellow")) {
            e.setMessage(ChatColor.YELLOW + e.getMessage());
        } else if (PlayerConfig.get().getString("settings.chatcolor").equalsIgnoreCase("green")) {
            e.setMessage(ChatColor.DARK_GREEN + e.getMessage());
        } else if (PlayerConfig.get().getString("settings.chatcolor").equalsIgnoreCase("turquoise")) {
            e.setMessage(ChatColor.DARK_AQUA + e.getMessage());
        } else if (PlayerConfig.get().getString("settings.chatcolor").equalsIgnoreCase("orange")) {
            e.setMessage(ChatColor.GOLD + e.getMessage());
        } else if (PlayerConfig.get().getString("settings.chatcolor").equalsIgnoreCase("blue")) {
            e.setMessage(ChatColor.BLUE + e.getMessage());
        }

    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();

        if (clicked == null || clicked.getItemMeta() == null || clicked.getItemMeta().getDisplayName() == null) return;

        if (!e.getInventory().getName().equalsIgnoreCase(OahuUtils.translate("&6Chat Color"))) return;

        PlayerConfig.load(p);
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(OahuUtils.translate("&a&lLight Green"))) {
            PlayerConfig.get().set("settings.chatcolor", "lightgreen");
            PlayerConfig.save();
            e.setCancelled(true);
            p.closeInventory();
            p.sendMessage(OahuUtils.translate("&7Chat Color successfully changed to &aLight Green&7."));
        } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(OahuUtils.translate("&b&lLight Blue"))) {
            PlayerConfig.get().set("settings.chatcolor", "lightblue");
            PlayerConfig.save();
            e.setCancelled(true);
            p.closeInventory();
            p.sendMessage(OahuUtils.translate("&7Chat Color successfully changed to &bLight Blue&7."));
        } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(OahuUtils.translate("&c&lRed"))) {
            PlayerConfig.get().set("settings.chatcolor", "red");
            PlayerConfig.save();
            e.setCancelled(true);
            p.closeInventory();
            p.sendMessage(OahuUtils.translate("&7Chat Color successfully changed to &cRed&7."));
        } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(OahuUtils.translate("&d&lPink"))) {
            PlayerConfig.get().set("settings.chatcolor", "pink");
            PlayerConfig.save();
            e.setCancelled(true);
            p.closeInventory();
            p.sendMessage(OahuUtils.translate("&7Chat Color successfully changed to &dPink&7."));
        } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(OahuUtils.translate("&e&lYellow"))) {
            PlayerConfig.get().set("settings.chatcolor", "yellow");
            PlayerConfig.save();
            e.setCancelled(true);
            p.closeInventory();
            p.sendMessage(OahuUtils.translate("&7Chat Color successfully changed to &eYellow&7."));
        } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(OahuUtils.translate("&2&lGreen"))) {
            PlayerConfig.get().set("settings.chatcolor", "green");
            PlayerConfig.save();
            e.setCancelled(true);
            p.closeInventory();
            p.sendMessage(OahuUtils.translate("&7Chat Color successfully changed to &2Green&7."));
        } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(OahuUtils.translate("&3&lTurquoise"))) {
            PlayerConfig.get().set("settings.chatcolor", "turquoise");
            PlayerConfig.save();
            e.setCancelled(true);
            p.closeInventory();
            p.sendMessage(OahuUtils.translate("&7Chat Color successfully changed to &3Turquoise&7."));
        } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(OahuUtils.translate("&6&lOrange / Gold"))) {
            PlayerConfig.get().set("settings.chatcolor", "orange");
            PlayerConfig.save();
            e.setCancelled(true);
            p.closeInventory();
            p.sendMessage(OahuUtils.translate("&7Chat Color successfully changed to &6Orange / Gold&7."));
        } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(OahuUtils.translate("&9&lBlue"))) {
            PlayerConfig.get().set("settings.chatcolor", "blue");
            PlayerConfig.save();
            e.setCancelled(true);
            p.closeInventory();
            p.sendMessage(OahuUtils.translate("&7Chat Color successfully changed to &9Blue&7."));
        } else {
            e.setCancelled(true);
            p.sendMessage(OahuUtils.translate("&cYou did not click a valid color."));
        }
    }
}
