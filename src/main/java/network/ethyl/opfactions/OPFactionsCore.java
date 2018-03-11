package network.ethyl.opfactions;

import me.aidan.lib.apihandlers.OahuRegister;
import net.milkbowl.vault.economy.Economy;
import network.ethyl.opfactions.features.*;
import network.ethyl.opfactions.features.chatcolor.ChatColorCommand;
import network.ethyl.opfactions.features.chatcolor.ColorListener;
import network.ethyl.opfactions.features.duels.DuelCommand;
import network.ethyl.opfactions.features.duels.DuelListener;
import network.ethyl.opfactions.features.invrestore.Invrestore;
import network.ethyl.opfactions.features.invrestore.InvrestoreListener;
import network.ethyl.opfactions.features.killstreak.KillstreakCommand;
import network.ethyl.opfactions.features.killstreak.KillstreakListener;
import network.ethyl.opfactions.features.koth.*;
import network.ethyl.opfactions.features.lms.LMSCommand;
import network.ethyl.opfactions.features.lms.LMSListener;
import network.ethyl.opfactions.features.perks.Immunities;
import network.ethyl.opfactions.features.perks.PerksCommand;
import network.ethyl.opfactions.features.snowball.SnowballCommand;
import network.ethyl.opfactions.features.snowball.SnowballListener;
import network.ethyl.opfactions.features.spawners.BuySigns;
import network.ethyl.opfactions.features.spawners.GiveSpawner;
import network.ethyl.opfactions.features.spawners.SilkSpawners;
import network.ethyl.opfactions.features.spawners.SpawnerType;
import network.ethyl.opfactions.features.stats.Stats;
import network.ethyl.opfactions.features.stats.StatsListener;
import network.ethyl.opfactions.features.stats.StatsResetCommand;
import network.ethyl.opfactions.features.stats.StatsResetListener;
import network.ethyl.opfactions.features.tab.TabListener;
import network.ethyl.opfactions.features.voting.NameMCVoting;
import network.ethyl.opfactions.features.voting.VotePartyListener;
import network.ethyl.opfactions.patches.*;
import network.ethyl.opfactions.features.scoreboard.ScoreboardManager;
import network.ethyl.opfactions.features.scoreboard.provider.SidebarProvider;
import network.ethyl.opfactions.utils.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.util.logging.Logger;

public class OPFactionsCore extends JavaPlugin implements Listener {


    private static final Logger log = Logger.getLogger("Minecraft");
    private static OPFactionsCore core;
    private static Economy econ = null;

    private Scoreboard s;

    public void onEnable() {

        core = this;

        s = Bukkit.getScoreboardManager().getNewScoreboard();

        new ScoreboardManager(this, new SidebarProvider());

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new AutoBroadcast(), 2400L, 2400L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new SuperKoTHBroadcast(), 2400L, 2400L);

        register();

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
        }

        saveDefaultConfig();

    }

    public void onDisable() {

    }

    private void register() {
        OahuRegister.registerListener(this, this,
                new DefaultMessagePatch(),
                new DeathMessages(),
                new StatsResetListener(),
                new LMSListener(),
                new FenceGlitch(),
                new FirstJoinMessage(),
                new MultiKillMessages(),
                new KillstreakListener(),
                new SnowballListener(),
                new SuperKoTHListener(),
                new CrateListener(),
                new WeatherPatch(),
                new SilkSpawners(),
                new SpawnerType(),
                new ColorListener(),
                new PerkSigns(),
                new Immunities(),
                new TabListener(),
                new InvrestoreListener(),
                new DuelListener(),
                new VotePartyListener(),
                new ThrowPatch(),
                new BuySigns(),
                new StatsListener(),
                new PexPromoteBlock());

        OahuRegister.registerCommand(this, new Donate(this),
                new Reddit(this),
                new Ping(this),
                new Teamspeak(this),
                new Twitter(this),
                new Stats(this),
                new StatsResetCommand(this),
                new Rename(this),
                new LMSCommand(this),
                new Discord(this),
                new KillstreakCommand(this),
                new Help(this),
                new SnowballCommand(this),
                new GiveKoTHCrate(this),
                new GiveKoTHKey(this),
                new KoTHLoot(this),
                new GiveSpawner(this),
                new Lore(this),
                new ChatColorCommand(this),
                new NameMCVoting(this),
                new PerksCommand(this),
                new Reclaim(this),
                new Invrestore(this),
                new DuelCommand(this),
                new PotionsCommand(this),
                new Borders(this));
    }

    public static OPFactionsCore getCore() {
        return core;
    }

    /*public void registerHealthBar() {
        if (s.getObjective("health") != null) {
            s.getObjective("health").unregister();
        }
        Objective o = s.registerNewObjective("health", "health");
        o.setDisplayName(ChatColor.RED + "❤");
        o.setDisplaySlot(DisplaySlot.BELOW_NAME);
    }*/

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        PlayerConfig.create(p);
    }

    public void addMoney(Player p, double amount) {
        econ.depositPlayer(p, amount);
    }

    public void takeMoney(Player p, double amount) {
        econ.withdrawPlayer(p, amount);
    }

    public boolean hasEnoughMoney(Player p, double amount) {
        if (econ.has(p, amount)) {
            return true;
        }
        return false;
    }

    public String getBalance(Player p) {
        return formatMoney(econ.getBalance(p));
    }

    public String formatMoney(double d) {
        return econ.format(d);
    }
}
