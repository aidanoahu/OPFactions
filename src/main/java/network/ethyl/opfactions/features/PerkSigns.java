package network.ethyl.opfactions.features;

import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PerkSigns implements Listener {

    /*

    Place a sign with a rank name in brackets to make it a clickable sign that shows donor perks in chat of the rank

     */

    @EventHandler
    public void onSignPlace(SignChangeEvent e) {
        if (e.getLine(1).contains("[magician]")) {
            e.setLine(1, OahuUtils.translate("&c[Perks]"));
            e.setLine(2, OahuUtils.translate("&6Magician"));
        } else if (e.getLine(1).contains("[sobek]")) {
            e.setLine(1, OahuUtils.translate("&c[Perks]"));
            e.setLine(2, OahuUtils.translate("&6Sobek"));
        } else if (e.getLine(1).contains("[bast]")) {
            e.setLine(1, OahuUtils.translate("&c[Perks]"));
            e.setLine(2, OahuUtils.translate("&6Bast"));
        } else if (e.getLine(1).contains("[thoth]")) {
            e.setLine(1, OahuUtils.translate("&c[Perks]"));
            e.setLine(2, OahuUtils.translate("&6Thoth"));
        } else if (e.getLine(1).contains("[nephthys]")) {
            e.setLine(1, OahuUtils.translate("&c[Perks]"));
            e.setLine(2, OahuUtils.translate("&6Nephthys"));
        } else if (e.getLine(1).contains("[chaos]")) {
            e.setLine(1, OahuUtils.translate("&c[Perks]"));
            e.setLine(2, OahuUtils.translate("&6Chaos"));
        } else if (e.getLine(1).contains("[horus]")) {
            e.setLine(1, OahuUtils.translate("&c[Perks]"));
            e.setLine(2, OahuUtils.translate("&6Horus"));
        } else if (e.getLine(1).contains("[anubis]")) {
            e.setLine(1, OahuUtils.translate("&c[Perks]"));
            e.setLine(2, OahuUtils.translate("&6Anubis"));
        } else if (e.getLine(1).contains("[osiris]")) {
            e.setLine(1, OahuUtils.translate("&c[Perks]"));
            e.setLine(2, OahuUtils.translate("&6Osiris"));
        } else if (e.getLine(1).contains("apophis]")) {
            e.setLine(1, OahuUtils.translate("&c[Perks]"));
            e.setLine(2, OahuUtils.translate("&6Apophis"));
        } else if (e.getLine(1).contains("[vengeance]")) {
            e.setLine(1, OahuUtils.translate("&c[Perks]"));
            e.setLine(2, OahuUtils.translate("&6Vengeance"));
        } else if (e.getLine(1).contains("[ra]")) {
            e.setLine(1, OahuUtils.translate("&c[Perks]"));
            e.setLine(2, OahuUtils.translate("&6Ra Kit"));
        } else if (e.getLine(1).contains("[geb]")) {
            e.setLine(1, OahuUtils.translate("&c[Perks]"));
            e.setLine(2, OahuUtils.translate("&6Geb Kit"));
        } else if (e.getLine(1).contains("[ptah]")) {
            e.setLine(1, OahuUtils.translate("&c[Perks]"));
            e.setLine(2, OahuUtils.translate("&6Ptah Kit"));
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
            if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock().getState() instanceof Sign)) {
                Sign s = (Sign) e.getClickedBlock().getState();
                Player p = e.getPlayer();
                if (s.getLine(1).contains("[Perks]") && s.getLine(2).contains("Magician")) {
                    for (String msg : OPFactionsCore.getCore().getConfig().getStringList("perk signs.magician")) {
                        p.sendMessage(msg);
                    }
                } else if (s.getLine(1).contains("[Perks]") && s.getLine(2).contains("Sobek")) {
                    for (String msg : OPFactionsCore.getCore().getConfig().getStringList("perk signs.sobek")) {
                        p.sendMessage(msg);
                    }
                } else if (s.getLine(1).contains("[Perks]") && s.getLine(2).contains("Bast")) {
                    for (String msg : OPFactionsCore.getCore().getConfig().getStringList("perk signs.bast")) {
                        p.sendMessage(msg);
                    }
                } else if (s.getLine(1).contains("[Perks]") && s.getLine(2).contains("Thoth")) {
                    for (String msg : OPFactionsCore.getCore().getConfig().getStringList("perk signs.thoth")) {
                        p.sendMessage(msg);
                    }
                } else if (s.getLine(1).contains("[Perks]") && s.getLine(2).contains("Nephthys")) {
                    for (String msg : OPFactionsCore.getCore().getConfig().getStringList("perk signs.nephthys")) {
                        p.sendMessage(msg);
                    }
                } else if (s.getLine(1).contains("[Perks]") && s.getLine(2).contains("Chaos")) {
                    for (String msg : OPFactionsCore.getCore().getConfig().getStringList("perk signs.chaos")) {
                        p.sendMessage(msg);
                    }
                } else if (s.getLine(1).contains("[Perks]") && s.getLine(2).contains("Horus")) {
                    for (String msg : OPFactionsCore.getCore().getConfig().getStringList("perk signs.horus")) {
                        p.sendMessage(msg);
                    }
                } else if (s.getLine(1).contains("[Perks]") && s.getLine(2).contains("Anubis")) {
                    for (String msg : OPFactionsCore.getCore().getConfig().getStringList("perk signs.anubis")) {
                        p.sendMessage(msg);
                    }
                } else if (s.getLine(1).contains("[Perks]") && s.getLine(2).contains("Osiris")) {
                    for (String msg : OPFactionsCore.getCore().getConfig().getStringList("perk signs.osiris")) {
                        p.sendMessage(msg);
                    }
                } else if (s.getLine(1).contains("[Perks]") && s.getLine(2).contains("Apophis")) {
                    for (String msg : OPFactionsCore.getCore().getConfig().getStringList("perk signs.apophis")) {
                        p.sendMessage(msg);
                    }
                } else if (s.getLine(1).contains("[Perks]") && s.getLine(2).contains("Vengeance")) {
                    for (String msg : OPFactionsCore.getCore().getConfig().getStringList("perk signs.vengeance")) {
                        p.sendMessage(msg);
                    }
                } else if (s.getLine(1).contains("[Perks]") && s.getLine(2).contains("Ra Kit")) {
                    for (String msg : OPFactionsCore.getCore().getConfig().getStringList("perk signs.ra kit")) {
                        p.sendMessage(msg);
                    }
                } else if (s.getLine(1).contains("[Perks]") && s.getLine(2).contains("Geb Kit")) {
                    for (String msg : OPFactionsCore.getCore().getConfig().getStringList("perk signs.geb kit")) {
                        p.sendMessage(msg);
                    }
                } else if (s.getLine(1).contains("[Perks]") && s.getLine(2).contains("Ptah Kit")) {
                    for (String msg : OPFactionsCore.getCore().getConfig().getStringList("perk signs.ptah kit")) {
                        p.sendMessage(msg);
                    }
                }
            }
    }
}
