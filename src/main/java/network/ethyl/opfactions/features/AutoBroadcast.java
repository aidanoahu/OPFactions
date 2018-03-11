package network.ethyl.opfactions.features;

import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Random;

public class AutoBroadcast implements Runnable {

    /*

    Runnable that broadcasts a random message set in the config every few minutes

     */

    public String getRandomMessage() {
        List<String> broadcasts = OPFactionsCore.getCore().getConfig().getStringList("random broadcasts");
        return broadcasts.get(new Random().nextInt(broadcasts.size()));
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage(OahuUtils.translate(getRandomMessage()));
    }
}
