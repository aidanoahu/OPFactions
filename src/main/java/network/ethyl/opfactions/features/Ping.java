package network.ethyl.opfactions.features;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.OahuUtils;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Ping extends CommandBase {

    /*

    Command to display your ping to the server

     */

    public Ping(OPFactionsCore core) {
        super(core, "ping", "/(command) <player>");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (args.length == 0) {
            CraftPlayer cp = (CraftPlayer) p;
            EntityPlayer ep = cp.getHandle();
            p.sendMessage(OahuUtils.translate("&c" + p.getName() + "&7's current ping is &6" + ep.ping + "ms&7."));
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                p.sendMessage(OahuUtils.translate("&cPlease specify a valid player."));
            } else {
                CraftPlayer cp = (CraftPlayer) target;
                EntityPlayer ep = cp.getHandle();
                p.sendMessage(OahuUtils.translate("&c" + target.getName() + "&7's current ping is &6" + ep.ping + "ms&7."));
            }
        }
    }
}
