package network.ethyl.opfactions.features.scoreboard;

import java.util.List;

import org.bukkit.entity.Player;

public interface ScoreboardEntryProvider {

    /*

    SCOREBOARD API by DEATHSTREAMS
    https://github.com/Deathstreams/ScoreboardWrapper

     */

    String getTitle();

    List<String> getLines(Player player);

}
