package network.ethyl.opfactions.features.voting;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import network.ethyl.opfactions.utils.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class NameMCVoting extends CommandBase {

    /*

    Command that checks the NameMC API if a player's profile has voted for the server so they can recieve a reward

     */

    public NameMCVoting(OPFactionsCore core) {
        super(core, "namemc", "/(command)");
    }

    private boolean verify(UUID uuid)
    {
        try
        {
            URL url = new URL("https://api.namemc.com/server/ethyl.network/likes?profile=" + uuid);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            boolean ret = false;
            while ((line = bufferedReader.readLine()) != null)
            {
                line = line.toLowerCase();
                if (line.contains("true")) {
                    ret = true;
                }
            }
            bufferedReader.close();
            return ret;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        int votes = OPFactionsCore.getCore().getConfig().getInt("vote party");
        if (verify(p.getUniqueId())) {
            PlayerConfig.load(p);
            if (!PlayerConfig.get().getBoolean("redeemednamemc")) {
                PlayerConfig.get().set("redeemednamemc", true);
                PlayerConfig.save();
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + sender.getName() + " Mystery 1");
                Bukkit.broadcastMessage(OahuUtils.translate("&cEthyl &8» &6" + p.getName() + " &7has voted for the server on NameMC and received a &cMystery Key&7!"));
                OPFactionsCore.getCore().getConfig().set("vote party", votes + 3);
            } else {
                p.sendMessage(OahuUtils.translate("&cYou have already redeemed your NameMC reward!"));
            }
        } else {
            p.sendMessage(OahuUtils.translate("&cYou have not yet liked this server on NameMC! Like it here: namemc.com/server/ethyl.network"));
        }
    }
}
