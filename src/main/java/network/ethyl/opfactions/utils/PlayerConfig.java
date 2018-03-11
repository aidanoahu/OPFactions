package network.ethyl.opfactions.utils;

import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class PlayerConfig {

    /*

    Class to save and load a players data to a personal yml file

     */

    static File cFile;
    static FileConfiguration config;
    static File folder = new File(OPFactionsCore.getCore().getDataFolder(), "data" + File.separator);
    static File df = OPFactionsCore.getCore().getDataFolder();

    public static void create(Player p) {
        cFile = new File(df, "data" + File.separator + p.getUniqueId() + ".yml");
        if (!df.exists()) df.mkdir();
        if (!cFile.exists()) {
            try {
                cFile.createNewFile();
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error creating " + cFile.getName());
            }
            config = YamlConfiguration.loadConfiguration(cFile);
            config.set("settings.chatcolor", "null");
            config.set("jewels", 0);
            config.set("magic shards", 0);
            config.set("kills", 0);
            config.set("deaths", 0);
            config.set("head value", 0);
            config.set("reclaimed", false);
            config.set("redeemednamemc", false);
            save();
        }
        config = YamlConfiguration.loadConfiguration(cFile);
    }

    public static File getFolder() {
        return folder;
    }

    public static File getFile() {
        return cFile;
    }

    public static void load(Player p) {
        cFile = new File(df, "data" + File.separator + p.getUniqueId() + ".yml");
        config = YamlConfiguration.loadConfiguration(cFile);
    }

    public static void load(UUID uuid) {
        File f = new File(df, "data" + File.separator + uuid + ".yml");
        if (!f.exists()) {
            cFile = new File(df, "data" + File.separator + uuid + ".yml");
            config = YamlConfiguration.loadConfiguration(cFile);
        }
    }

    public static boolean isNull(UUID uuid) {
        File f = new File(df, "data" + File.separator + uuid + ".yml");
        if (!f.exists()) {
            return true;
        }
        return false;
    }

    public static FileConfiguration get() {
        return config;
    }

    public static void save() {
        try {
            config.save(cFile);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + cFile.getName());
        }
    }
}
