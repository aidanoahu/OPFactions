package network.ethyl.opfactions.utils;

import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.ArrayList;
import java.util.List;

public class PEX {

    /*

    PEX API UTILS

     */

    static PermissionManager pex = PermissionsEx.getPermissionManager();

    public static String[] getGroups() {
        List<String> groupList = new ArrayList<>();
        PermissionGroup[] groups = pex.getGroups();
        for (PermissionGroup g : groups) {
            groupList.add(g.getName());
        }
        String[] groupArray = groupList.toArray(new String[groupList.size()]);
        return groupArray;
    }

    public static String getPlayerGroup(Player p) {
        String playerName = p.getName();
        PermissionUser user = pex.getUser(playerName);
        String[] playerGroups = user.getGroupsNames();
        String playerGroup = playerGroups[0];
        return playerGroup;
    }
}
