package network.ethyl.opfactions.features.spawners;

import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BuySigns implements Listener {

    /*

    Place spawner signs and purchase spawners with the placed sign

     */

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
            if (e.getLine(1).contains("[squid]")) {
                e.setLine(0, OahuUtils.translate("&4[&6Spawner&4]"));
                e.setLine(1, OahuUtils.translate("&c1"));
                e.setLine(2, OahuUtils.translate("&cSquid"));
                e.setLine(3, OahuUtils.translate("&c$100,000"));
            } else if (e.getLine(1).contains("[irongolem]")) {
                e.setLine(0, OahuUtils.translate("&4[&6Spawner&4]"));
                e.setLine(1, OahuUtils.translate("&c1"));
                e.setLine(2, OahuUtils.translate("&cIron Golem"));
                e.setLine(3, OahuUtils.translate("&c$1,000,000"));
            } else if (e.getLine(1).contains("[cow]")) {
                e.setLine(0, OahuUtils.translate("&4[&6Spawner&4]"));
                e.setLine(1, OahuUtils.translate("&c1"));
                e.setLine(2, OahuUtils.translate("&cCow"));
                e.setLine(3, OahuUtils.translate("&c$75,000"));
            } else if (e.getLine(1).contains("[pigman]")) {
                e.setLine(0, OahuUtils.translate("&4[&6Spawner&4]"));
                e.setLine(1, OahuUtils.translate("&c1"));
                e.setLine(2, OahuUtils.translate("&cPigman"));
                e.setLine(3, OahuUtils.translate("&c$350,000"));
            } else if (e.getLine(1).contains("[zombie]")) {
                e.setLine(0, OahuUtils.translate("&4[&6Spawner&4]"));
                e.setLine(1, OahuUtils.translate("&c1"));
                e.setLine(2, OahuUtils.translate("&cZombie"));
                e.setLine(3, OahuUtils.translate("&c$25,000"));
            } else if (e.getLine(1).contains("[spider]")) {
                e.setLine(0, OahuUtils.translate("&4[&6Spawner&4]"));
                e.setLine(1, OahuUtils.translate("&c1"));
                e.setLine(2, OahuUtils.translate("&cSpider"));
                e.setLine(3, OahuUtils.translate("&c$75,000"));
            } else if (e.getLine(1).contains("[skeleton]")) {
                e.setLine(0, OahuUtils.translate("&4[&6Spawner&4]"));
                e.setLine(1, OahuUtils.translate("&c1"));
                e.setLine(2, OahuUtils.translate("&cSkeleton"));
                e.setLine(3, OahuUtils.translate("&c$75,000"));
            } else if (e.getLine(1).contains("[enderman]")) {
                e.setLine(0, OahuUtils.translate("&4[&6Spawner&4]"));
                e.setLine(1, OahuUtils.translate("&c1"));
                e.setLine(2, OahuUtils.translate("&cEnderman"));
                e.setLine(3, OahuUtils.translate("&c$200,000"));
            } else if (e.getLine(1).contains("[creeper]")) {
                e.setLine(0, OahuUtils.translate("&4[&6Spawner&4]"));
                e.setLine(1, OahuUtils.translate("&c1"));
                e.setLine(2, OahuUtils.translate("&cCreeper"));
                e.setLine(3, OahuUtils.translate("&c$225,000"));
            } else if (e.getLine(1).contains("[blaze]")) {
                e.setLine(0, OahuUtils.translate("&4[&6Spawner&4]"));
                e.setLine(1, OahuUtils.translate("&c1"));
                e.setLine(2, OahuUtils.translate("&cBlaze"));
                e.setLine(3, OahuUtils.translate("&c$250,000"));
            } else if (e.getLine(1).contains("[pig]")) {
                e.setLine(0, OahuUtils.translate("&4[&6Spawner&4]"));
                e.setLine(1, OahuUtils.translate("&c1"));
                e.setLine(2, OahuUtils.translate("&cPig"));
                e.setLine(3, OahuUtils.translate("&c$50,000"));
            }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock().getState() instanceof Sign)) {
            Sign s = (Sign) e.getClickedBlock().getState();
            Player p = e.getPlayer();
            OPFactionsCore core = OPFactionsCore.getCore();
            if (s.getLine(0).contains("Spawner") && s.getLine(2).contains("Squid")) {
                if (!core.hasEnoughMoney(p, 100000)) {
                    p.sendMessage(OahuUtils.translate("&cYou do not have enough money! You have " + core.getBalance(p) + " out of $100,000!"));
                } else {
                    core.takeMoney(p, 100000);
                    p.sendMessage(OahuUtils.translate("&7You have purchased &61x Squid Spawner &7for &c$100,000&7!"));
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "givespawner " + p.getName() + " squid 1");
                }
            } else if (s.getLine(0).contains("Spawner") && s.getLine(2).contains("Iron Golem")) {
                if (!core.hasEnoughMoney(p, 1000000)) {
                    p.sendMessage(OahuUtils.translate("&cYou do not have enough money! You have " + core.getBalance(p) + " out of $1,000,000!"));
                } else {
                    core.takeMoney(p, 1000000);
                    p.sendMessage(OahuUtils.translate("&7You have purchased &61x Iron Golem Spawner &7for &c$1,000,000&7!"));
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "givespawner " + p.getName() + " irongolem 1");
                }
            } else if (s.getLine(0).contains("Spawner") && s.getLine(2).contains("Cow")) {
                if (!core.hasEnoughMoney(p, 75000)) {
                    p.sendMessage(OahuUtils.translate("&cYou do not have enough money! You have " + core.getBalance(p) + " out of $75,000!"));
                } else {
                    core.takeMoney(p, 75000);
                    p.sendMessage(OahuUtils.translate("&7You have purchased &61x Cow Spawner &7for &c$75,000&7!"));
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "givespawner " + p.getName() + " cow 1");
                }
            } else if (s.getLine(0).contains("Spawner") && s.getLine(2).contains("Pigman")) {
                if (!core.hasEnoughMoney(p, 350000)) {
                    p.sendMessage(OahuUtils.translate("&cYou do not have enough money! You have " + core.getBalance(p) + " out of $350,000!"));
                } else {
                    core.takeMoney(p, 350000);
                    p.sendMessage(OahuUtils.translate("&7You have purchased &61x Pigman Spawner &7for &c$350,000&7!"));
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "givespawner " + p.getName() + " pigman 1");
                }
            } else if (s.getLine(0).contains("Spawner") && s.getLine(2).contains("Zombie")) {
                if (!core.hasEnoughMoney(p, 25000)) {
                    p.sendMessage(OahuUtils.translate("&cYou do not have enough money! You have " + core.getBalance(p) + " out of $25,000!"));
                } else {
                    core.takeMoney(p, 25000);
                    p.sendMessage(OahuUtils.translate("&7You have purchased &61x Zombie Spawner &7for &c$25,000&7!"));
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "givespawner " + p.getName() + " zombie 1");
                }
            } else if (s.getLine(0).contains("Spawner") && s.getLine(2).contains("Spider")) {
                if (!core.hasEnoughMoney(p, 75000)) {
                    p.sendMessage(OahuUtils.translate("&cYou do not have enough money! You have " + core.getBalance(p) + " out of $75,000!"));
                } else {
                    core.takeMoney(p, 75000);
                    p.sendMessage(OahuUtils.translate("&7You have purchased &61x Spider Spawner &7for &c$75,000&7!"));
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "givespawner " + p.getName() + " spider 1");
                }
            } else if (s.getLine(0).contains("Spawner") && s.getLine(2).contains("Skeleton")) {
                if (!core.hasEnoughMoney(p, 75000)) {
                    p.sendMessage(OahuUtils.translate("&cYou do not have enough money! You have " + core.getBalance(p) + " out of $75,000!"));
                } else {
                    core.takeMoney(p, 75000);
                    p.sendMessage(OahuUtils.translate("&7You have purchased &61x Skeleton Spawner &7for &c$75,000&7!"));
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "givespawner " + p.getName() + " skeleton 1");
                }
            } else if (s.getLine(0).contains("Spawner") && s.getLine(2).contains("Enderman")) {
                if (!core.hasEnoughMoney(p, 200000)) {
                    p.sendMessage(OahuUtils.translate("&cYou do not have enough money! You have " + core.getBalance(p) + " out of $200,000!"));
                } else {
                    core.takeMoney(p, 200000);
                    p.sendMessage(OahuUtils.translate("&7You have purchased &61x Enderman Spawner &7for &c$200,000&7!"));
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "givespawner " + p.getName() + " enderman 1");
                }
            } else if (s.getLine(0).contains("Spawner") && s.getLine(2).contains("Creeper")) {
                if (!core.hasEnoughMoney(p, 225000)) {
                    p.sendMessage(OahuUtils.translate("&cYou do not have enough money! You have " + core.getBalance(p) + " out of $225,000!"));
                } else {
                    core.takeMoney(p, 225000);
                    p.sendMessage(OahuUtils.translate("&7You have purchased &61x Creeper Spawner &7for &c$225,000&7!"));
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "givespawner " + p.getName() + " creeper 1");
                }
            } else if (s.getLine(0).contains("Spawner") && s.getLine(2).contains("Blaze")) {
                if (!core.hasEnoughMoney(p, 250000)) {
                    p.sendMessage(OahuUtils.translate("&cYou do not have enough money! You have " + core.getBalance(p) + " out of $250,000!"));
                } else {
                    core.takeMoney(p, 250000);
                    p.sendMessage(OahuUtils.translate("&7You have purchased &61x Blaze Spawner &7for &c$250,000&7!"));
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "givespawner " + p.getName() + " blaze 1");
                }
            } else if (s.getLine(0).contains("Spawner") && s.getLine(2).contains("Pig")) {
                if (!core.hasEnoughMoney(p, 50000)) {
                    p.sendMessage(OahuUtils.translate("&cYou do not have enough money! You have " + core.getBalance(p) + " out of $50,000!"));
                } else {
                    core.takeMoney(p, 50000);
                    p.sendMessage(OahuUtils.translate("&7You have purchased &61x Pig Spawner &7for &c$50,000&7!"));
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "givespawner " + p.getName() + " pig 1");
                }
            }
        }

    }
}
