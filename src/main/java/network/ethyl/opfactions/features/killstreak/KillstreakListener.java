package network.ethyl.opfactions.features.killstreak;

import me.aidan.lib.api.OahuUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KillstreakListener implements Listener {

    /*

    Killstreak listeners, rewards players at amounts of kills

     */

    public static HashMap<String, Integer> streak = new HashMap<>();

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player dead = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();

        if (killer == null || dead == null) return;

        ItemStack opapples = new ItemStack(Material.GOLDEN_APPLE, 64, (short) 1);
        ItemMeta applemeta = opapples.getItemMeta();
        applemeta.setDisplayName(OahuUtils.translate("&6&lOP Apple"));
        List<String> lore = new ArrayList<>();
        lore.add(OahuUtils.translate("&c&oGives an extra 4 normal hearts and 6 absorption hearts."));
        applemeta.setLore(lore);
        opapples.setItemMeta(applemeta);

        if (streak.containsKey(killer.getName())) {
            if (streak.get(killer.getName()) >= 1) {
                streak.put(killer.getName(), streak.get(killer.getName()) + 1);
                switch (streak.get(killer.getName())) {
                    case 5:
                        Bukkit.broadcastMessage(OahuUtils.translate("&cEthyl &8» &c" + killer.getName() + " &6is on a killstreak of &4&lFIVE!"));
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "kit thoth " + killer.getName());
                        killer.sendMessage(OahuUtils.translate("&aYou have received &a&lKIT THOTH &r&afor achieving a killstreak of 5."));
                        break;
                    case 10:
                        Bukkit.broadcastMessage(OahuUtils.translate("&cEthyl &8» &c" + killer.getName() + " &6is on a killstreak of &4&lTEN!"));
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + killer.getName() + " Mystery 1");
                        killer.sendMessage(OahuUtils.translate("&aYou have received &a&l1 MYSTERY KEY &r&afor achieving a killstreak of 10."));
                        break;
                    case 15:
                        Bukkit.broadcastMessage(OahuUtils.translate("&cEthyl &8» &c" + killer.getName() + " &6is on a killstreak of &4&lFIFTEEN!"));
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + killer.getName() + " 300000");
                        killer.sendMessage(OahuUtils.translate("&aYou have received &a&l$300,000 &r&afor achieving a killstreak of 15."));
                        break;
                    case 20:
                        Bukkit.broadcastMessage(OahuUtils.translate("&cEthyl &8» &c" + killer.getName() + " &6is on a killstreak of &4&lTWENTY!"));
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + killer.getName() + " Ethyl 1");
                        killer.sendMessage(OahuUtils.translate("&aYou have received &a&l1 ETHYL KEY &r&afor achieving a killstreak of 20."));
                        break;
                    case 25:
                        Bukkit.broadcastMessage(OahuUtils.translate("&cEthyl &8» &c" + killer.getName() + " &6is on a killstreak of &4&lTWENTY-FIVE!"));
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "kit geb " + killer.getName());
                        killer.sendMessage(OahuUtils.translate("&aYou have received &a&lKIT GEB &r&afor achieving a killstreak of 25."));
                        break;
                }
            }
        } else {
            streak.put(killer.getName(), 1);
        }

        if (streak.containsKey(dead.getName())) {
            streak.remove(dead.getName());
        }
    }
}
