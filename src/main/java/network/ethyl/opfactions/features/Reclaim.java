package network.ethyl.opfactions.features;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import network.ethyl.opfactions.utils.PEX;
import network.ethyl.opfactions.utils.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reclaim extends CommandBase {

    /*

    Command to claim donor bonus rewards each map

     */

    public Reclaim(OPFactionsCore core) {
        super(core, "reclaim", "/(command)");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        PlayerConfig.load(p);
        if (PlayerConfig.get().getBoolean("reclaimed")) {
            p.sendMessage(OahuUtils.translate("&cYou have already redeemed your rewards for the map!"));
        } else {
            if (PEX.getPlayerGroup(p).equalsIgnoreCase("Magician")) {
                // MAGICIAN
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Kit 1");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " McMMO 1");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "addcredits " + p.getName() + " 25");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + p.getName() + " 3000");
                PlayerConfig.get().set("reclaimed", true);
                PlayerConfig.save();
                Bukkit.broadcastMessage(OahuUtils.translate("&c" + p.getName() + " &6has reclaimed their &e&l" + PEX.getPlayerGroup(p) + " Rank &6with &c/reclaim&6."));
            } else if (PEX.getPlayerGroup(p).equalsIgnoreCase("Sobek")) {
                // SOBEK
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Kit 1");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " McMMO 1");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "addcredits " + p.getName() + " 50");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + p.getName() + " 5000");
                PlayerConfig.get().set("reclaimed", true);
                PlayerConfig.save();
                Bukkit.broadcastMessage(OahuUtils.translate("&c" + p.getName() + " &6has reclaimed their &e&l" + PEX.getPlayerGroup(p) + " Rank &6with &c/reclaim&6."));
            } else if (PEX.getPlayerGroup(p).equalsIgnoreCase("Bast")
                    || PEX.getPlayerGroup(p).equalsIgnoreCase("Translator")) {
                // BAST & TRANSLATOR
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Kit 1");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " McMMO 1");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Mystery 1");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "addcredits " + p.getName() + " 100");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + p.getName() + " 10000");
                PlayerConfig.get().set("reclaimed", true);
                PlayerConfig.save();
                Bukkit.broadcastMessage(OahuUtils.translate("&c" + p.getName() + " &6has reclaimed their &e&l" + PEX.getPlayerGroup(p) + " Rank &6with &c/reclaim&6."));
            } else if (PEX.getPlayerGroup(p).equalsIgnoreCase("Thoth")
                    || PEX.getPlayerGroup(p).equalsIgnoreCase("Builder")) {
                // THOTH & BUILDER
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Kit 2");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " McMMO 2");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Mystery 1");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "addcredits " + p.getName() + " 150");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + p.getName() + " 20000");
                PlayerConfig.get().set("reclaimed", true);
                PlayerConfig.save();
                Bukkit.broadcastMessage(OahuUtils.translate("&c" + p.getName() + " &6has reclaimed their &e&l" + PEX.getPlayerGroup(p) + " Rank &6with &c/reclaim&6."));
            } else if (PEX.getPlayerGroup(p).equalsIgnoreCase("Nephthys")) {
                // NEPHTHYS
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Kit 2");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " McMMO 2");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Mystery 2");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "addcredits " + p.getName() + " 200");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + p.getName() + " 30000");
                PlayerConfig.get().set("reclaimed", true);
                PlayerConfig.save();
                Bukkit.broadcastMessage(OahuUtils.translate("&c" + p.getName() + " &6has reclaimed their &e&l" + PEX.getPlayerGroup(p) + " Rank &6with &c/reclaim&6."));
            } else if (PEX.getPlayerGroup(p).equalsIgnoreCase("Chaos")) {
                // CHAOS
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Kit 3");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " McMMO 3");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Mystery 2");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "addcredits " + p.getName() + " 250");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + p.getName() + " 40000");
                PlayerConfig.get().set("reclaimed", true);
                PlayerConfig.save();
                Bukkit.broadcastMessage(OahuUtils.translate("&c" + p.getName() + " &6has reclaimed their &e&l" + PEX.getPlayerGroup(p) + " Rank &6with &c/reclaim&6."));
            } else if (PEX.getPlayerGroup(p).equalsIgnoreCase("Horus")
                    || PEX.getPlayerGroup(p).equalsIgnoreCase("YouTube")
                    || PEX.getPlayerGroup(p).equalsIgnoreCase("Twitch")) {
                // HORUS, YOUTUBE, & TWITCH
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Kit 3");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " McMMO 3");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Mystery 3");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "addcredits " + p.getName() + " 300");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + p.getName() + " 50000");
                PlayerConfig.get().set("reclaimed", true);
                PlayerConfig.save();
                Bukkit.broadcastMessage(OahuUtils.translate("&c" + p.getName() + " &6has reclaimed their &e&l" + PEX.getPlayerGroup(p) + " Rank &6with &c/reclaim&6."));
            } else if (PEX.getPlayerGroup(p).equalsIgnoreCase("Osiris")) {
                // OSIRIS
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Kit 4");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " McMMO 4");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Mystery 3");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Ethyl 1");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "addcredits " + p.getName() + " 400");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + p.getName() + " 100000");
                PlayerConfig.get().set("reclaimed", true);
                PlayerConfig.save();
                Bukkit.broadcastMessage(OahuUtils.translate("&c" + p.getName() + " &6has reclaimed their &e&l" + PEX.getPlayerGroup(p) + " Rank &6with &c/reclaim&6."));
            } else if (PEX.getPlayerGroup(p).equalsIgnoreCase("Apophis")) {
                // OSIRIS
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Kit 5");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " McMMO 5");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Mystery 4");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Ethyl 2");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "addcredits " + p.getName() + " 450");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + p.getName() + " 150000");
                PlayerConfig.get().set("reclaimed", true);
                PlayerConfig.save();
                Bukkit.broadcastMessage(OahuUtils.translate("&c" + p.getName() + " &6has reclaimed their &e&l" + PEX.getPlayerGroup(p) + " Rank &6with &c/reclaim&6."));
            } else if (PEX.getPlayerGroup(p).equalsIgnoreCase("Vengeance")
                    || PEX.getPlayerGroup(p).equalsIgnoreCase("Famous")) {
                // VENGEANCE & FAMOUS
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Kit 6");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " McMMO 6");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Mystery 5");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate gk " + p.getName() + " Ethyl 3");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "addcredits " + p.getName() + " 500");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + p.getName() + " 200000");
                PlayerConfig.get().set("reclaimed", true);
                PlayerConfig.save();
                Bukkit.broadcastMessage(OahuUtils.translate("&c" + p.getName() + " &6has reclaimed their &e&l" + PEX.getPlayerGroup(p) + " Rank &6with &c/reclaim&6."));
            } else {
                p.sendMessage(OahuUtils.translate("&cYou must have Magician rank or higher to use /reclaim! Donate at store.ethyl.network."));
            }
        }
    }
}
