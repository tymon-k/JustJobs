package org.main.jobsAL;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Objects;

import static org.main.jobsAL.Job.ShowXpAndLevelAndJob;

public class LumberjackJob implements Listener {
    public static void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = event.getBlock().getType();
        if (!Objects.equals(JobsFile.GetPlayerJob(player), "Lumberjack")){
            return;
        }
        switch (blockType){
            case Material.ACACIA_WOOD:
            case Material.BIRCH_WOOD:
            case Material.CHERRY_WOOD:
            case Material.DARK_OAK_WOOD:
            case Material.JUNGLE_WOOD:
            case Material.MANGROVE_WOOD:
            case Material.OAK_WOOD:
            case Material.PALE_OAK_WOOD:
            case Material.SPRUCE_WOOD:
                JobsFile.SetPlayerXp(player,"Lumberjack",JobsFile.GetPlayerXp(player,"Lumberjack")+75);
                ShowXpAndLevelAndJob(player,"Miner",JobsFile.GetPlayerLevel(player,"Miner"),JobsFile.GetPlayerXp(player,"Miner"));
        }
    }
}
