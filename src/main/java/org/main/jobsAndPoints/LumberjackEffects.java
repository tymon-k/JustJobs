package org.main.jobsAndPoints;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import static org.main.jobsAndPoints.JobsEffects.random;

public class LumberjackEffects implements Listener {
    private static int getSaplingChance(int level) {
        switch (level) {
            case 1: return 10;
            case 2: return 20;
            case 3: return 30;
            case 4: return 40;
            case 5: return 50;
            case 6: return 70;
            case 7: return 80;
            case 8: return 100;
            default: return 0;
        }
    }
    private static boolean isValidGround(Material mat) {
        switch (mat) {
            case GRASS_BLOCK:
            case DIRT:
            case COARSE_DIRT:
            case PODZOL:
            case ROOTED_DIRT:
            case FARMLAND:
            case MYCELIUM:
                return true;
            default:
                return false;
        }
    }
    private static Material getSapling(Material log) {

        switch (log) {

            case OAK_LOG:
            case STRIPPED_OAK_LOG:
                return Material.OAK_SAPLING;

            case BIRCH_LOG:
            case STRIPPED_BIRCH_LOG:
                return Material.BIRCH_SAPLING;

            case SPRUCE_LOG:
            case STRIPPED_SPRUCE_LOG:
                return Material.SPRUCE_SAPLING;

            case JUNGLE_LOG:
            case STRIPPED_JUNGLE_LOG:
                return Material.JUNGLE_SAPLING;

            case ACACIA_LOG:
            case STRIPPED_ACACIA_LOG:
                return Material.ACACIA_SAPLING;

            case DARK_OAK_LOG:
            case STRIPPED_DARK_OAK_LOG:
                return Material.DARK_OAK_SAPLING;

            case MANGROVE_LOG:
            case STRIPPED_MANGROVE_LOG:
                return Material.MANGROVE_PROPAGULE;

            case CHERRY_LOG:
            case STRIPPED_CHERRY_LOG:
                return Material.CHERRY_SAPLING;

            case PALE_OAK_LOG:
            case STRIPPED_PALE_OAK_LOG:
                return Material.OAK_SAPLING; // fallback

            default:
                return null;
        }
    }

    @EventHandler
    public static void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (!"Lumberjack".equals(JobsFile.GetPlayerJob(player))) return;

        Material type = block.getType();
        int level = JobsFile.GetPlayerLevel(player, "Lumberjack");

        int chance = level*10;
        if (chance <= 0) return;

        Material sapling = getSapling(type);
        if (sapling == null) return;

        Block below = block.getRelative(BlockFace.DOWN);

        int roll = random.nextInt(100) + 1;
        if (isValidGround(below.getType())) {return;}
        if (roll <= chance) {
            ItemStack item = new ItemStack(type, 1);

            block.getWorld().dropItem(
                    block.getLocation(),
                    item
            );
            event.setCancelled(true);
            block.setType(sapling);
        }
    }
}
