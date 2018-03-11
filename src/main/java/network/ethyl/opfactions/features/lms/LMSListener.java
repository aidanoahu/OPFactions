package network.ethyl.opfactions.features.lms;

import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.util.HashMap;
import java.util.Map;

public class LMSListener implements Listener {

    public static Map<String, Integer> kills = new HashMap<>();

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (LMSCommand.playersInGame.contains(p.getName())) {
            e.setCancelled(true);
            p.sendMessage(OahuUtils.translate("&cYou cannot break blocks while in LMS."));
        }

        if (LMSCommand.playersInQueue.contains(p.getName())) {
            e.setCancelled(true);
            p.sendMessage(OahuUtils.translate("&cYou cannot break blocks while in the queue for LMS."));
        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (LMSCommand.playersInGame.contains(p.getName())) {
            e.setCancelled(true);
            p.sendMessage(OahuUtils.translate("&cYou cannot place blocks while in LMS."));
        }

        if (LMSCommand.playersInQueue.contains(p.getName())) {
            e.setCancelled(true);
            p.sendMessage(OahuUtils.translate("&cYou cannot place blocks while in the queue for LMS."));
        }

    }

    // send message and check if only 1 player left
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player dead = e.getEntity();
        if (LMSCommand.playersInGame.contains(dead.getName())) {
            LMSCommand.playersLeft = LMSCommand.playersLeft - 1;
            if (e.getEntity().getKiller() == null) {
                if (LMSCommand.playersLeft > 1) {
                    for (String p : LMSCommand.playersInGame) {
                        Bukkit.getPlayer(p).sendMessage(OahuUtils.translate("&cPlayer &6" + dead.getName() + " &chas been eliminated. " + LMSCommand.playersLeft + " players remain!"));
                    }
                } else if (LMSCommand.playersLeft == 1) {
                    for (String killer : LMSCommand.playersInGame) {
                        kills.put(killer, kills.get(killer) + 1);

                        Bukkit.broadcastMessage(OahuUtils.translate(" "));
                        Bukkit.broadcastMessage(OahuUtils.translate("&c&lCongratulations to &6&l" + killer + " &c&lfor winning the LMS!"));
                        Bukkit.broadcastMessage(OahuUtils.translate(" "));


                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(OPFactionsCore.getCore(), new Runnable() {
                            @Override
                            public void run() {

                                Bukkit.getPlayer(killer).sendMessage(OahuUtils.translate("&6You have been rewarded an Ethyl Crate Key for winning!"));
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + killer + " Ethyl 1");

                                LMSCommand.playersInGame.remove(killer);
                                LMSCommand.playersInQueue.clear();

                                LMSCommand.isRunning.set(false);
                            }
                        }, (5 * 20));
                    }
                }
            } else {
                Player killer = e.getEntity().getKiller();
                if (LMSCommand.playersLeft > 1) {
                    for (String p : LMSCommand.playersInGame) {
                        Bukkit.getPlayer(p).sendMessage(OahuUtils.translate("&cPlayer &6" + dead.getName() + " &chas been eliminated by &6" + killer.getName()
                                + "&c. " + LMSCommand.playersLeft + " players remain!"));
                    }
                    kills.put(killer.getName(), kills.get(killer.getName()) + 1);
                } else if (LMSCommand.playersLeft == 1) {
                    kills.put(killer.getName(), kills.get(killer.getName()) + 1);
                    Bukkit.broadcastMessage(OahuUtils.translate(" "));
                    Bukkit.broadcastMessage(OahuUtils.translate("&c&lCongratulations to &6&l" + killer.getName() + " &c&lfor winning the LMS!"));
                    Bukkit.broadcastMessage(OahuUtils.translate(" "));

                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(OPFactionsCore.getCore(), new Runnable() {
                        @Override
                        public void run() {
                            LMSCommand.remove(killer);

                            killer.sendMessage(OahuUtils.translate("&6You have been rewarded an Ethyl Crate Key for winning!"));
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + killer.getName() + " Ethyl 1");

                            LMSCommand.playersInGame.remove(killer.getName());
                            LMSCommand.playersInQueue.clear();

                            LMSCommand.isRunning.set(false);
                        }
                    }, (5 * 20));
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (LMSCommand.playersInQueue.contains(p.getName())) {
            LMSCommand.remove(p);
            LMSCommand.playersInQueue.remove(p.getName());
        }


        if (LMSCommand.playersInGame.contains(p.getName())) {
            LMSCommand.remove(p);
            LMSCommand.playersInGame.remove(p.getName());
            LMSCommand.playersLeft = LMSCommand.playersLeft - 1;
            Bukkit.broadcastMessage(OahuUtils.translate(ChatColor.RED + p.getName() + " has disconnected and has been eliminated from the LMS."));
        }

        if (LMSCommand.isRunning.get()) {
            if (LMSCommand.playersLeft == 1) {
                for (String s : LMSCommand.playersInGame) {
                    Player sp = Bukkit.getPlayer(s);

                    Bukkit.broadcastMessage(OahuUtils.translate(" "));
                    Bukkit.broadcastMessage(OahuUtils.translate("&c&lCongratulations to &6&l" + sp.getName() + " &c&lfor winning the LMS!"));
                    Bukkit.broadcastMessage(OahuUtils.translate(" "));

                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(OPFactionsCore.getCore(), new Runnable() {
                        @Override
                        public void run() {
                            LMSCommand.remove(sp);
                            sp.sendMessage(OahuUtils.translate("&6You have been rewarded an Ethyl Crate Key for winning!"));
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + sp.getName() + " Ethyl 1");

                            LMSCommand.playersInGame.clear();
                            LMSCommand.playersInQueue.clear();

                            LMSCommand.isRunning.set(false);
                        }
                    }, (5 * 20));
                }
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (LMSCommand.playersInGame.contains(p.getName())) {
            LMSCommand.remove(p);
            p.sendMessage(OahuUtils.translate("&cYou have been eliminated from the LMS!"));
            p.sendMessage(OahuUtils.translate("&cYour previous inventory, armor, potion effects, and location have been restored."));
            LMSCommand.playersInGame.remove(p.getName());
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCmd(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (LMSCommand.playersInGame.contains(p.getName()) || LMSCommand.playersInQueue.contains(p.getName())) {
            for (String command : OPFactionsCore.getCore().getConfig().getStringList("Blocked commands")) {
                if (e.getMessage().startsWith("/") && e.getMessage().contains(command)) {
                    p.sendMessage(OahuUtils.translate("&cYou cannot do this during an LMS. Leave the LMS using /lms leave."));
                    e.setCancelled(true);
                }
            }
        }
    }
}
