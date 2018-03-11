package network.ethyl.opfactions.features.scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import lombok.Getter;

public class ScoreboardManager implements Listener {

    /*

    SCOREBOARD API by DEATHSTREAMS
    https://github.com/Deathstreams/ScoreboardWrapper

     */

    @Getter
    private static ScoreboardManager instance;

    @Getter
    private ScoreboardEntryProvider provider;

    private final JavaPlugin plugin;
    private Map<UUID, PlayerScoreboard> scoreboards;
    private BukkitTask updateTask;

    public ScoreboardManager(JavaPlugin plugin, ScoreboardEntryProvider provider) {
        ScoreboardManager.instance = this;
        this.plugin = plugin;
        this.provider = provider;
        this.scoreboards = new HashMap<>();
        this.updateTask = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this::updateAll, 2, 2);
        Bukkit.getPluginManager().registerEvents(this, plugin);
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            setup(p);
        }
    }

    private void setup(Player player) {
        PlayerScoreboard current = scoreboards.remove(player.getUniqueId());
        if (current != null) {
            current.clear();
        }
        if (player.getScoreboard() == Bukkit.getScoreboardManager().getMainScoreboard()) {
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }
        scoreboards.put(player.getUniqueId(), new PlayerScoreboard(player));
    }

    private void remove(Player player) {
        PlayerScoreboard current = scoreboards.remove(player.getUniqueId());
        if (current != null) {
            current.clear();
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        setup(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        remove(player);
    }

    @EventHandler
    public void onDisable(PluginDisableEvent event) {
        if (event.getPlugin() == plugin) {
            try {
                updateTask.cancel();
            } catch(IllegalStateException exception) {}
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                remove(p);
            }
            HandlerList.unregisterAll(this);
            scoreboards.clear();
        }
    }

    public void updateAll() {
        scoreboards.keySet().stream().filter(id -> Bukkit.getPlayer(id) != null).map(id -> scoreboards.get(id)).forEach(PlayerScoreboard::send);
    }

}
