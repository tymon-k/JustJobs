package org.main.jobsAndPoints;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import static org.main.jobsAndPoints.JobsEffects.random;

public class FarmerEffects implements Listener {


    @EventHandler
    public static void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (!"Farmer".equals(JobsFile.GetPlayerJob(player))) return;
        if (block.getBlockData() instanceof Ageable ageable) {
            if (!(ageable.getAge()>=ageable.getMaximumAge())){
                return;
            }
        }

        Material type = block.getType();
        int level = JobsFile.GetPlayerLevel(player, "Farmer");

        int chance = level*10;
        if (chance <= 0) return;


        Block below = block.getRelative(BlockFace.DOWN);

        int roll = random.nextInt(100) + 1;
        if (!(below.getType()==Material.FARMLAND)) {return;}
        if (roll <= chance) {
            ItemStack item = new ItemStack(type, 1);

            block.getWorld().dropItem(
                    block.getLocation(),
                    item
            );
            event.setCancelled(true);
            block.setType(block.getType());
        }
    }
}
