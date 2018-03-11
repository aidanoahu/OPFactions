package network.ethyl.opfactions.features.spawners;

import me.aidan.lib.api.OahuUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpawnerType implements Listener {

    /*

    Correct spawner type on place of mob spawner

     */

    public void setSpawner(Block block, EntityType entityType) {
        BlockState blockState = block.getState();
        CreatureSpawner spawner = ((CreatureSpawner) blockState);
        spawner.setSpawnedType(entityType);
        blockState.update();
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlockPlaced();
        ItemStack hand = p.getItemInHand();

        if (b == null || hand == null || hand.getItemMeta() == null || hand.getItemMeta().getDisplayName() == null) return;

        if (b.getType() == Material.MOB_SPAWNER && hand.getType() == Material.MOB_SPAWNER) {
            ItemMeta handm = hand.getItemMeta();
            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cSHEEP SPAWNER"))) {
                setSpawner(b, EntityType.SHEEP);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cCREEPER SPAWNER"))) {
                setSpawner(b, EntityType.CREEPER);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cSKELETON SPAWNER"))) {
                setSpawner(b, EntityType.SKELETON);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cSPIDER SPAWNER"))) {
                setSpawner(b, EntityType.SPIDER);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cGIANT SPAWNER"))) {
                setSpawner(b, EntityType.GIANT);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cZOMBIE SPAWNER"))) {
                setSpawner(b, EntityType.ZOMBIE);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cSLIME SPAWNER"))) {
                setSpawner(b, EntityType.SLIME);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cGHAST SPAWNER"))) {
                setSpawner(b, EntityType.GHAST);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cPIGMAN SPAWNER"))) {
                setSpawner(b, EntityType.PIG_ZOMBIE);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cENDERMAN SPAWNER"))) {
                setSpawner(b, EntityType.ENDERMAN);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cCAVESPIDER SPAWNER"))) {
                setSpawner(b, EntityType.CAVE_SPIDER);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cSILVERFISH SPAWNER"))) {
                setSpawner(b, EntityType.SILVERFISH);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cBLAZE SPAWNER"))) {
                setSpawner(b, EntityType.BLAZE);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cMAGMACUBE SPAWNER"))) {
                setSpawner(b, EntityType.MAGMA_CUBE);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cENDERDRAGON SPAWNER"))) {
                setSpawner(b, EntityType.ENDER_DRAGON);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cPIG SPAWNER"))) {
                setSpawner(b, EntityType.PIG);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cCOW SPAWNER"))) {
                setSpawner(b, EntityType.COW);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cCHICKEN SPAWNER"))) {
                setSpawner(b, EntityType.CHICKEN);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cSQUID SPAWNER"))) {
                setSpawner(b, EntityType.SQUID);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cWOLF SPAWNER"))) {
                setSpawner(b, EntityType.WOLF);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cMOOSHROOM SPAWNER"))) {
                setSpawner(b, EntityType.MUSHROOM_COW);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cSNOWGOLEM SPAWNER"))) {
                setSpawner(b, EntityType.SNOWMAN);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cOCELOT SPAWNER"))) {
                setSpawner(b, EntityType.OCELOT);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cVILLAGER SPAWNER"))) {
                setSpawner(b, EntityType.VILLAGER);
            }

            if (handm.getDisplayName().equalsIgnoreCase(OahuUtils.translate("&cIRONGOLEM SPAWNER"))) {
                setSpawner(b, EntityType.IRON_GOLEM);
            }
        }
    }
}
