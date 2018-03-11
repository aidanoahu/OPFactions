package network.ethyl.opfactions.features.spawners;

import me.aidan.lib.api.CommandBase;
import me.aidan.lib.api.OahuUtils;
import network.ethyl.opfactions.OPFactionsCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveSpawner extends CommandBase {

    /*

    Command to give players different spawner types

     */

    public GiveSpawner(OPFactionsCore core) {
        super(core, "givespawner", "/(command) <player> <type> <amount>");
    }

    public ItemStack spawner(String name, int amount) {
        ItemStack stack = new ItemStack(Material.MOB_SPAWNER, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(OahuUtils.translate("&c" + name + " SPAWNER"));
        stack.setItemMeta(meta);
        return stack;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if (args.length != 3) {
            sender.sendMessage(ChatColor.RED + "Usage: " + getUsage());
        }

        if (args.length == 3) {
            Player target = Bukkit.getPlayer(args[0]);
            String type = args[1];
            int amount = Integer.parseInt(args[2]);

            if (target == null || amount < 1) sender.sendMessage(OahuUtils.translate("&cPlease specify a valid player and amount."));


            switch (type) {
                case "sheep":
                    target.getInventory().addItem(spawner("SHEEP", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Sheep Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Sheep Spawner(s) to " + target.getName() + "."));
                    break;
                case "creeper":
                    target.getInventory().addItem(spawner("CREEPER", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Creeper Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Creeper Spawner(s) to " + target.getName() + "."));
                    break;
                case "skeleton":
                    target.getInventory().addItem(spawner("SKELETON", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Skeleton Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Skeleton Spawner(s) to " + target.getName() + "."));
                    break;
                case "spider":
                    target.getInventory().addItem(spawner("SPIDER", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Spider Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Spider Spawner(s) to " + target.getName() + "."));
                    break;
                case "giant":
                    target.getInventory().addItem(spawner("GIANT", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Giant Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Giant Spawner(s) to " + target.getName() + "."));
                    break;
                case "zombie":
                    target.getInventory().addItem(spawner("ZOMBIE", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Zombie Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Zombie Spawner(s) to " + target.getName() + "."));
                    break;
                case "slime":
                    target.getInventory().addItem(spawner("SLIME", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Slime Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Slime Spawner(s) to " + target.getName() + "."));
                    break;
                case "ghast":
                    target.getInventory().addItem(spawner("GHAST", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Ghast Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Ghast Spawner(s) to " + target.getName() + "."));
                    break;
                case "pigman":
                    target.getInventory().addItem(spawner("PIGMAN", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Pigman Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Pigman Spawner(s) to " + target.getName() + "."));
                    break;
                case "enderman":
                    target.getInventory().addItem(spawner("ENDERMAN", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Enderman Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Enderman Spawner(s) to " + target.getName() + "."));
                    break;
                case "cavespider":
                    target.getInventory().addItem(spawner("CAVESPIDER", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Cave Spider Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Cave Spider Spawner(s) to " + target.getName() + "."));
                    break;
                case "silverfish":
                    target.getInventory().addItem(spawner("SILVERFISH", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Silverfish Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Silverfish Spawner(s) to " + target.getName() + "."));
                    break;
                case "blaze":
                    target.getInventory().addItem(spawner("BLAZE", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Blaze Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Blaze Spawner(s) to " + target.getName() + "."));
                    break;
                case "magmacube":
                    target.getInventory().addItem(spawner("MAGMACUBE", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Magma Cube Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Magma Cube Spawner(s) to " + target.getName() + "."));
                    break;
                case "enderdragon":
                    target.getInventory().addItem(spawner("ENDERDRAGON", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Enderdragon Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Enderdragon Spawner(s) to " + target.getName() + "."));
                    break;
                case "pig":
                    target.getInventory().addItem(spawner("PIG", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Pig Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Pig Spawner(s) to " + target.getName() + "."));
                    break;
                case "cow":
                    target.getInventory().addItem(spawner("COW", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Cow Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Cow Spawner(s) to " + target.getName() + "."));
                    break;
                case "chicken":
                    target.getInventory().addItem(spawner("CHICKEN", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Chicken Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Chicken Spawner(s) to " + target.getName() + "."));
                    break;
                case "squid":
                    target.getInventory().addItem(spawner("SQUID", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Squid Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Squid Spawner(s) to " + target.getName() + "."));
                    break;
                case "wolf":
                    target.getInventory().addItem(spawner("WOLF", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Wolf Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Wolf Spawner(s) to " + target.getName() + "."));
                    break;
                case "mooshroom":
                    target.getInventory().addItem(spawner("MOOSHROOM", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Mooshroom Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Mooshroom Spawner(s) to " + target.getName() + "."));
                    break;
                case "snowgolem":
                    target.getInventory().addItem(spawner("SNOWGOLEM", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Snow Golem Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Snow Golem Spawner(s) to " + target.getName() + "."));
                    break;
                case "ocelot":
                    target.getInventory().addItem(spawner("OCELOT", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Ocelot Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Ocelot Spawner(s) to " + target.getName() + "."));
                    break;
                case "villager":
                    target.getInventory().addItem(spawner("VILLAGER", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Villager Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Villager Spawner(s) to " + target.getName() + "."));
                    break;
                case "irongolem":
                    target.getInventory().addItem(spawner("IRONGOLEM", amount));
                    target.sendMessage(OahuUtils.translate("&7You have received " + amount + "x Iron Golem Spawner(s)."));
                    sender.sendMessage(OahuUtils.translate("&7You have given " + amount + " Iron Golem Spawner(s) to " + target.getName() + "."));
            }


        }
    }
}
