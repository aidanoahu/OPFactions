package network.ethyl.opfactions.features.scoreboard;


import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class PlayerScoreboard {

    /*

    SCOREBOARD API by DEATHSTREAMS
    https://github.com/Deathstreams/ScoreboardWrapper

     */

    public static final String[] ENTRY_NAMES;

    private final Player player;
    private Scoreboard scoreboard;
    private Objective objective;

    private long createdTime;
    private boolean ready;

    public PlayerScoreboard(Player player) {
        this.player = player;
        this.scoreboard = player.getScoreboard();
        Objective objective = scoreboard.getObjective("PlayerScoreboard");
        if (objective == null) objective = scoreboard.registerNewObjective("PlayerScoreboard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective = objective;
        this.createdTime = System.currentTimeMillis();
        this.ready = true;
    }

    public void send() {
            if (!ready) return;
            ScoreboardEntryProvider provider = ScoreboardManager.getInstance().getProvider();
            List<String> entries = provider.getLines(player);
            title: {
                objective.setDisplayName(provider.getTitle());
                break title;
            }
            cleanupOld: {
                if (scoreboard.getEntries().size() != entries.size()) {
                    scoreboard.getEntries().forEach(this::removeEntry);
                }
                break cleanupOld;
            }
            sendNew: {
            int index = 0;
            for (String entry : entries) {
                Entry split = split(entry);
                Team team = scoreboard.getTeam(ENTRY_NAMES[index]);
                if (team == null) {
                    try {
                        team = scoreboard.registerNewTeam(ENTRY_NAMES[index]);
                    } catch (IllegalArgumentException exception) {
                        exception.printStackTrace();
                    }
                    team.addEntry(team.getName());
                }
                team.setPrefix(split.left);
                team.setSuffix(split.right);
                objective.getScore(team.getName()).setScore(15 - index);
                index++;
            }
            break sendNew;
        }
    }

    private Entry split(String text) {
        Entry entry = new Entry();
        if (text.length() <= 16) {
            entry.left = text;
        } else {
            String prefix = text.substring(0, 16), suffix = "";
            if (prefix.endsWith("\u00a7")) {
                prefix = prefix.substring(0, prefix.length() - 1);
                suffix = "\u00a7" + suffix;
            }
            suffix = StringUtils.left(ChatColor.getLastColors(prefix) + suffix + text.substring(16), 16);
            entry.left = prefix;
            entry.right = suffix;
        }
        return entry;
    }

    public void clear() {
        ready = false;
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    private void removeEntry(String id) {
        removeEntry(id, false);
    }

    private void removeEntry(String id, boolean deleteTeam) {
        scoreboard.resetScores(id);
        if (deleteTeam) {
            Team team = scoreboard.getTeam(id);
            if (team != null) team.unregister();
        }
    }

    private class Entry {

        private String left = "", right = "";

    }

    static {
        ENTRY_NAMES = new String[15];
        for (int i = 0; i < 15; i ++) {
            ENTRY_NAMES[i] = ChatColor.AQUA + ChatColor.values()[i].toString() + ChatColor.RESET;
        }
    }

}
