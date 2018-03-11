package network.ethyl.opfactions.features.duels;

import com.massivecraft.factions.P;
import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.Lang;
import me.aidan.lib.api.OahuUtils;
import me.aidan.lib.api.SerializableLocation;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuelCommand extends CommandBase {

    /*
    Duels command
    - /duel request <player> - send a duel request
    - /duel accept <player> - accept a duel request
    - /duel redeem - redeem loot from a recently won duel
    - /duel setloc1 - admin command to set first duel location
    - /duel setloc2 - admin command to set the second duel location
     */

    public DuelCommand(OPFactionsCore core) {
        super(core, "duel", "/(command) <player>");
    }

    public static List<String> inDuels = new ArrayList<>();
    public static Map<String, String> outgoingRequests = new HashMap<>();
    public static Map<String, String> incomingRequests = new HashMap<>();
    public static Map<String, String> duels = new HashMap<>();
    public static Map<String, ItemStack[]> redeem = new HashMap<>();
    public static Map<String, ItemStack[]> redeemArmor = new HashMap<>();
    public List<String> hasRedeemed = new ArrayList<>();

    public FileConfiguration config = OPFactionsCore.getCore().getConfig();

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
            p.sendMessage(OahuUtils.translate("&5&lDuel Commands"));
            p.sendMessage(OahuUtils.translate("&d/duel request <player> &7- &dRequest to duel another player."));
            p.sendMessage(OahuUtils.translate("&d/duel accept <player> &7- &dAccept a duel request from another player."));
            p.sendMessage(OahuUtils.translate("&d/duel redeem &7- &dRedeem loot from a recent duel victory."));
            if (p.hasPermission("duels.admin")) {
                p.sendMessage(OahuUtils.translate("&d/duel setloc1 &7- &dSet the first spawn location for duels."));
                p.sendMessage(OahuUtils.translate("&d/duel setloc2 &7- &dSet the second spawn location for duels."));
            }
            p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
        }

        if (args.length == 2) {
            SerializableLocation locationOne = SerializableLocation.getFromYML(config, "duels.location1");
            SerializableLocation locationTwo = SerializableLocation.getFromYML(config, "duels.location2");

            // REQUEST
            if (args[0].equalsIgnoreCase("request")) {
                Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    p.sendMessage(OahuUtils.translate("&cPlease specify a valid player."));
                } else {
                    if (inDuels.contains(p.getName()) || inDuels.contains(target.getName())) {
                        p.sendMessage(OahuUtils.translate("&cYou or the other player is already in a duel!"));
                    } else {
                        if (outgoingRequests.containsKey(p.getName()) || incomingRequests.containsKey(target.getName())) {
                            if (outgoingRequests.containsKey(p.getName())) {
                                p.sendMessage(OahuUtils.translate("&cYou already have a pending request."));
                            }

                            if (incomingRequests.containsKey(target.getName())) {
                                p.sendMessage(OahuUtils.translate("&cThat player already has a pending request."));
                            }
                        } else {
                            TextComponent clickHere = new TextComponent(OahuUtils.translate("&eClick here to accept!"));
                            clickHere.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel accept " + p.getName()));
                            clickHere.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(OahuUtils.translate("&e/duel accept " + p.getName())).create()));

                            outgoingRequests.put(p.getName(), target.getName());
                            incomingRequests.put(target.getName(), p.getName());
                            p.sendMessage(OahuUtils.translate("&7Duel request has been sent to &6" + target.getName() + "&7!"));

                            target.sendMessage(OahuUtils.translate("&8&m" + Lang.HEADER));
                            target.sendMessage(OahuUtils.translate("&7You have received a duel request from &6" + p.getName() + "&7!"));
                            target.spigot().sendMessage(clickHere);
                            target.sendMessage(OahuUtils.translate("&8&m" + Lang.HEADER));

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(OPFactionsCore.getCore(), new Runnable() {
                                @Override
                                public void run() {
                                    if (!inDuels.contains(p.getName())) {
                                        outgoingRequests.remove(p.getName());
                                        incomingRequests.remove(target.getName());
                                        p.sendMessage(OahuUtils.translate("&7Duel request to &c" + target.getName() + " &7has expired."));
                                        target.sendMessage(OahuUtils.translate("&7Duel request from &c" + p.getName() + " &7has expired."));
                                    }
                                }
                            }, (15 * 20));
                        }
                    }
                }
            }

            // ACCEPT
            if (args[0].equalsIgnoreCase("accept")) {
                Player target = Bukkit.getPlayer(args[1]);

                if (target.getName() == null) {
                    p.sendMessage(OahuUtils.translate("&cPlease specify a valid player."));
                }

                if (incomingRequests.containsKey(p.getName())) {
                    if (inDuels.contains(p.getName()) || inDuels.contains(target.getName())) {
                        p.sendMessage(OahuUtils.translate("&cYou or the other player is already in a duel!"));
                    } else {
                        p.sendMessage(OahuUtils.translate("&6Duel starting in 5 seconds against &c" + target.getName() + "&6..."));
                        target.sendMessage(OahuUtils.translate("&6Duel starting in 5 seconds against &c" + p.getName() + "&6..."));
                        outgoingRequests.remove(target.getName());
                        incomingRequests.remove(p.getName());
                        duels.put(p.getName(), target.getName());
                        duels.put(target.getName(), p.getName());
                        inDuels.add(p.getName());
                        inDuels.add(target.getName());

                        Bukkit.getScheduler().scheduleSyncDelayedTask(OPFactionsCore.getCore(), new Runnable() {
                            @Override
                            public void run() {
                                p.teleport(locationOne.getLocation());
                                target.teleport(locationTwo.getLocation());

                                p.sendMessage(OahuUtils.translate("&6Duel has started!"));
                                target.sendMessage(OahuUtils.translate("&6Duel has started!"));

                                for (Player server : Bukkit.getServer().getOnlinePlayers()) {
                                    if (server != target) {
                                        p.hidePlayer(server);
                                    }

                                    if (server != p) {
                                        target.hidePlayer(server);
                                    }
                                }

                                if (p.isFlying()) {
                                    p.setAllowFlight(false);
                                }

                                if (target.isFlying()) {
                                    target.setAllowFlight(false);
                                }
                            }
                        }, (5 * 20));
                    }
                }
            }
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("redeem")) {
                if (!redeem.containsKey(p.getName())) {
                    p.sendMessage(OahuUtils.translate("&cYou don't have any recent duel wins."));
                } else {
                    Inventory redeemLoot = Bukkit.createInventory(null, 45, OahuUtils.translate("&cRecent Duel Loot"));
                    if (!hasRedeemed.contains(p.getName())) {
                        hasRedeemed.add(p.getName());
                        redeemLoot.setContents(redeem.get(p.getName()));
                        redeemLoot.addItem(redeemArmor.get(p.getName()));
                        p.openInventory(redeemLoot);
                    } else {
                        redeemLoot.setContents(redeem.get(p.getName()));
                        p.openInventory(redeemLoot);
                    }
                }
            }

            if (args[0].equalsIgnoreCase("setloc1")) {
                if (!p.hasPermission("duels.admin")) {
                    p.sendMessage(OahuUtils.translate("&cNo permission."));
                } else {
                    config.set("duels.location1", new SerializableLocation(p.getLocation()));
                    try {
                        config.save(OPFactionsCore.getCore().getDataFolder() + File.separator + "config.yml");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    p.sendMessage(OahuUtils.translate("&aDuel location 1 successfully set."));
                }
            }

            if (args[0].equalsIgnoreCase("setloc2")) {
                if (!p.hasPermission("duels.admin")) {
                    p.sendMessage(OahuUtils.translate("&cNo permission."));
                } else {
                    config.set("duels.location2", new SerializableLocation(p.getLocation()));
                    try {
                        config.save(OPFactionsCore.getCore().getDataFolder() + File.separator + "config.yml");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    p.sendMessage(OahuUtils.translate("&aDuel location 2 successfully set."));
                }
            }
        }
    }
}
