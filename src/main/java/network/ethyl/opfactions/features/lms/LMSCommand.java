package network.ethyl.opfactions.features.lms;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.Lang;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class LMSCommand extends CommandBase {

    private static LMSCommand instance;

    public LMSCommand(OPFactionsCore core) {
        super(core, "lms", "/(command)");
    }

    public static LMSCommand getLMS() {
        return instance;
    }

    private FileConfiguration config = OPFactionsCore.getCore().getConfig();

    public static List<String> playersInGame = new ArrayList<>();
    public static List<String> playersInQueue = new ArrayList<>();

    public static Map<Player, ItemStack[]> inventories = new HashMap<>();
    public static Map<Player, ItemStack[]> armor = new HashMap<>();
    public static Map<Player, Collection<PotionEffect>> effects = new HashMap<>();
    public static Map<Player, Location> locations = new HashMap<>();
    public static Map<Player, Float> exp = new HashMap<>();

    public static long startingAt;

    public static AtomicBoolean isRunning = new AtomicBoolean(false);
    public static AtomicBoolean isWaiting = new AtomicBoolean(false);

    public static int playersLeft = 0;

    public static void remove(Player p) {
        if (p.getInventory() == null) return;
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        for (PotionEffect e : p.getActivePotionEffects()) {
            p.removePotionEffect(e.getType());
        }
        p.getActivePotionEffects().clear();
        p.setExp(exp.get(p));
        p.teleport(locations.get(p));
        p.getInventory().setContents(inventories.get(p));
        p.getInventory().setArmorContents(armor.get(p));
        for (PotionEffect ef : effects.get(p)) {
            p.addPotionEffect(ef);
        }
        LMSListener.kills.remove(p.getName());
        effects.remove(p);
        locations.remove(p);
        inventories.remove(p);
        armor.remove(p);
    }

    public static int getPlayerCount() {
        int i = 0;
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            i++;
        }
        return i;
    }

    public static void setupInventory(Player p) {
        ItemStack helm = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemMeta hm = helm.getItemMeta();
        hm.setDisplayName(OahuUtils.translate("&c&l&ki&6&lLMS&c&l&ki"));
        hm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 200, true);
        hm.addEnchant(Enchantment.DURABILITY, 200, true);
        helm.setItemMeta(hm);

        ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta cm = chest.getItemMeta();
        cm.setDisplayName(OahuUtils.translate("&c&l&ki&6&lLMS&c&l&ki"));
        cm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 200, true);
        cm.addEnchant(Enchantment.DURABILITY, 200, true);
        chest.setItemMeta(cm);

        ItemStack leg = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        ItemMeta lm = leg.getItemMeta();
        lm.setDisplayName(OahuUtils.translate("&c&l&ki&6&lLMS&c&l&ki"));
        lm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 200, true);
        lm.addEnchant(Enchantment.DURABILITY, 200, true);
        leg.setItemMeta(lm);

        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta bm = boots.getItemMeta();
        bm.setDisplayName(OahuUtils.translate("&c&l&ki&6&lLMS&c&l&ki"));
        bm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 200, true);
        bm.addEnchant(Enchantment.DURABILITY, 200, true);
        boots.setItemMeta(bm);

        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta sm = sword.getItemMeta();
        sm.setDisplayName(OahuUtils.translate("&c&l&ki&6&lLMS&c&l&ki"));
        sm.addEnchant(Enchantment.DAMAGE_ALL, 21, true);
        sm.addEnchant(Enchantment.FIRE_ASPECT, 21, true);
        sword.setItemMeta(sm);

        ItemStack gapples = new ItemStack(Material.GOLDEN_APPLE, 64, (short) 1);

        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2000000000, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2000000000, 1));

        p.getInventory().setHelmet(helm);
        p.getInventory().setChestplate(chest);
        p.getInventory().setLeggings(leg);
        p.getInventory().setBoots(boots);
        p.getInventory().setItem(0, sword);
        p.getInventory().setItem(1, gapples);
        p.getInventory().setItem(2, helm);
        p.getInventory().setItem(3, chest);
        p.getInventory().setItem(4, leg);
        p.getInventory().setItem(5, boots);

    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
            p.sendMessage(OahuUtils.translate("&6&lLMS Commands"));
            p.sendMessage(OahuUtils.translate("&c/lms join &7- &cJoin the queue for LMS."));
            p.sendMessage(OahuUtils.translate("&c/lms leave &7- &cLeave the current LMS."));
            p.sendMessage(OahuUtils.translate("&c/lms status &7- &cCheck the current status of LMS."));
            if (p.hasPermission("lms.start")) {
                p.sendMessage(OahuUtils.translate("&c/lms start &7- &cStart an LMS."));
            }
            if (p.hasPermission("lms.admin")) {
                p.sendMessage(OahuUtils.translate("&c/lms stop &7- &cForce end the running LMS."));
                p.sendMessage(OahuUtils.translate("&c/lms setspawn &7- &cSets the spawn location."));
                p.sendMessage(OahuUtils.translate("&c/lms setlobby &7- &cSets the lobby location."));
            }
            p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
        }
        if (args.length == 1) {
            Location spawn = new Location(Bukkit.getWorld(config.getString("spawn.world")),
                    config.getDouble("spawn.x"),
                    config.getDouble("spawn.y"),
                    config.getDouble("spawn.z"),
                    config.getFloat("spawn.yaw"),
                    config.getFloat("spawn.pitch"));
            Location lobby = new Location(Bukkit.getWorld(config.getString("lobby.world")),
                    config.getDouble("lobby.x"),
                    config.getDouble("lobby.y"),
                    config.getDouble("lobby.z"),
                    config.getFloat("lobby.yaw"),
                    config.getFloat("lobby.pitch"));

            // DONE
            if (args[0].equalsIgnoreCase("start")) {
                if (!p.hasPermission("lms.start")) {
                    p.sendMessage(OahuUtils.translate("&cNo permission."));
                } else {
                    if (isRunning.get() || isWaiting.get()) {
                        p.sendMessage(OahuUtils.translate("&cAn LMS is already running."));
                    } else {
                        if (getPlayerCount() < 2) {
                            p.sendMessage(OahuUtils.translate("&cThere must be atleast 2 players online to start an LMS."));
                        } else {
                            if (isRunning.get()) {
                                p.sendMessage(OahuUtils.translate("&cAn LMS event is currently running."));
                            } else {
                                Bukkit.broadcastMessage(OahuUtils.translate(" "));
                                Bukkit.broadcastMessage(OahuUtils.translate("&6&oLMS event starting in 1 minute..."));
                                Bukkit.broadcastMessage(OahuUtils.translate("&c/lms join &7to join the queue!"));
                                Bukkit.broadcastMessage(OahuUtils.translate(" "));
                                isWaiting.set(true);
                                startingAt = System.currentTimeMillis() + 60000;
                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(OPFactionsCore.getCore(), new Runnable() {
                                    @Override
                                    public void run() {
                                        if (isWaiting.get()) {
                                            if (playersInQueue.size() < 2) {
                                                Bukkit.broadcastMessage(OahuUtils.translate("&c&lLMS has been cancelled due to a lack of players."));
                                                if (playersInQueue.size() >= 1 && playersInQueue.size() <= 2) {
                                                    for (String queue : playersInQueue) {
                                                        remove(Bukkit.getPlayer(queue));
                                                    }
                                                    playersInQueue.clear();
                                                }
                                                isWaiting.set(false);
                                                isRunning.set(false);
                                            } else {
                                                isRunning.set(true);
                                                isWaiting.set(false);
                                                for (String queue : playersInQueue) {
                                                    playersInGame.add(queue);
                                                    Bukkit.getPlayer(queue).teleport(spawn);
                                                    setupInventory(Bukkit.getPlayer(queue));
                                                    LMSListener.kills.put(queue, 0);
                                                    if (Bukkit.getPlayer(queue).getGameMode() != GameMode.SURVIVAL) {
                                                        Bukkit.getPlayer(queue).setGameMode(GameMode.SURVIVAL);
                                                    }
                                                    if (Bukkit.getPlayer(queue).getAllowFlight()) {
                                                        Bukkit.getPlayer(queue).setAllowFlight(false);
                                                    }
                                                }
                                                playersLeft = playersInQueue.size();
                                                playersInQueue.clear();
                                                Bukkit.broadcastMessage(OahuUtils.translate(" "));
                                                Bukkit.broadcastMessage(OahuUtils.translate("&6&lLMS HAS STARTED!"));
                                                Bukkit.broadcastMessage(OahuUtils.translate("&c&oGood luck to the participants!"));
                                                Bukkit.broadcastMessage(OahuUtils.translate(" "));
                                            }
                                        }
                                    }
                                }, (60 * 20));
                            }
                        }
                    }
                }
            }

            // DONE
            if (args[0].equalsIgnoreCase("stop")) {
                if (!p.hasPermission("lms.admin")) {
                    p.sendMessage(OahuUtils.translate("&cNo permission."));
                } else {
                    if (!isRunning.get() && !isWaiting.get()) {
                        p.sendMessage(OahuUtils.translate("&cNo LMS is currently running."));
                    } else if (isRunning.get() || isWaiting.get()) {
                        if (playersInGame.size() >= 1) {
                            for (String game : playersInGame) {
                                remove(Bukkit.getPlayer(game));
                            }
                            playersInGame.clear();
                        }
                        if (playersInQueue.size() >= 1) {
                            for (String queue : playersInQueue) {
                                remove(Bukkit.getPlayer(queue));
                            }
                            playersInQueue.clear();
                        }
                        Bukkit.broadcastMessage(OahuUtils.translate("&c&lThe LMS has been stopped by an Admin."));
                        isWaiting.set(false);
                        isRunning.set(false);
                    }
                }
            }

            // DONE
            if (args[0].equalsIgnoreCase("join")) {
                if (isRunning.get() && !isWaiting.get()) {
                    p.sendMessage(OahuUtils.translate("&cThe current LMS is in game."));
                } else if (!isRunning.get() && isWaiting.get()) {
                    if (playersInQueue.contains(p.getName())) {
                        p.sendMessage(OahuUtils.translate("&cYou are already in the queue!"));
                    } else {
                        playersInQueue.add(p.getName());
                        exp.put(p, p.getExp());
                        inventories.put(p, p.getInventory().getContents());
                        armor.put(p, p.getInventory().getArmorContents());
                        effects.put(p, p.getActivePotionEffects());
                        locations.put(p, p.getLocation());
                        p.teleport(lobby);
                        p.getInventory().clear();
                        p.setExp(0);
                        p.getInventory().setArmorContents(null);
                        for (PotionEffect e : p.getActivePotionEffects()) {
                            p.removePotionEffect(e.getType());
                        }
                        p.getActivePotionEffects().clear();
                        p.sendMessage(OahuUtils.translate("&aYou have joined the LMS."));
                        p.sendMessage(OahuUtils.translate("&aYour previous inventory, armor, location, and potion effects have been saved."));
                    }
                } else if (!isRunning.get() && !isWaiting.get()) {
                    p.sendMessage(OahuUtils.translate("&cThere is no LMS currently running."));
                }

            }

            // DONE
            if (args[0].equalsIgnoreCase("leave")) {
                if (playersInQueue.contains(p.getName()) && isWaiting.get()) {
                    p.sendMessage(OahuUtils.translate("&cYou have left the queue."));
                    p.sendMessage(OahuUtils.translate("&cYour previous inventory, armor, location, and potion effects have been restored."));
                    playersInQueue.remove(p.getName());
                    remove(p);
                } else if (playersInGame.contains(p.getName()) && isRunning.get()) {
                    p.sendMessage(OahuUtils.translate("&cYou have left the current LMS."));
                    playersInGame.remove(p.getName());
                    p.sendMessage(OahuUtils.translate("&cYour previous inventory, armor, location, and potion effects have been restored."));
                    for (String game : playersInGame) {
                        Bukkit.getPlayer(game).sendMessage(OahuUtils.translate("&cPlayer &6" + p.getName() + " &chas left the LMS. " + playersInGame.size() + " players remain!"));
                    }
                    remove(p);
                } else {
                    p.sendMessage(OahuUtils.translate("&cYou are not in an LMS game."));
                }
            }

            if (args[0].equalsIgnoreCase("setspawn")) {
                if (!p.hasPermission("lms.admin")) {
                    p.sendMessage(OahuUtils.translate("&cNo permission."));
                } else {
                    config.set("spawn.x", p.getLocation().getX());
                    config.set("spawn.y", p.getLocation().getY());
                    config.set("spawn.z", p.getLocation().getZ());
                    config.set("spawn.yaw", p.getLocation().getYaw());
                    config.set("spawn.pitch", p.getLocation().getPitch());
                    config.set("spawn.world", p.getWorld().getName());
                    try {
                        config.save(OPFactionsCore.getCore().getDataFolder() + File.separator + "config.yml");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    p.sendMessage(OahuUtils.translate("&aGame spawn point saved for LMS."));

                }
            }

            if (args[0].equalsIgnoreCase("setlobby")) {
                if (!p.hasPermission("lms.admin")) {
                    p.sendMessage(OahuUtils.translate("&cNo permission."));
                } else {
                    config.set("lobby.x", p.getLocation().getX());
                    config.set("lobby.y", p.getLocation().getY());
                    config.set("lobby.z", p.getLocation().getZ());
                    config.set("lobby.yaw", p.getLocation().getYaw());
                    config.set("lobby.pitch", p.getLocation().getPitch());
                    config.set("lobby.world", p.getWorld().getName());
                    try {
                        config.save(OPFactionsCore.getCore().getDataFolder() + File.separator + "config.yml");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    p.sendMessage(OahuUtils.translate("&aLobby spawn point saved for LMS."));
                }
            }

            if (args[0].equalsIgnoreCase("status")) {
                p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
                if (isRunning.get()) {
                    p.sendMessage(OahuUtils.translate("&6Status: &7In-Game"));
                    if (playersInGame.size() >= 1) {
                        p.sendMessage(OahuUtils.translate("&5Players:"));
                        for (String s : playersInGame) {
                            p.sendMessage(OahuUtils.translate(" &d- &7" + s));
                        }
                    } else {
                        p.sendMessage(OahuUtils.translate("&5Players: &7None"));
                    }
                } else if (isWaiting.get()) {
                    p.sendMessage(OahuUtils.translate("&6Status: &7Waiting"));
                    if (playersInQueue.size() >= 1) {
                        p.sendMessage(OahuUtils.translate("&5Players:"));
                        for (String s : playersInQueue) {
                            p.sendMessage(OahuUtils.translate(" &d- &7" + s));
                        }
                    } else {
                        p.sendMessage(OahuUtils.translate("&5Players: &7None"));
                    }
                } else {
                    p.sendMessage(OahuUtils.translate("&cThere is no LMS currently running."));
                }
                p.sendMessage(OahuUtils.translate("&7&m" + Lang.HEADER));
            }
        }
    }
}
