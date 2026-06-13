package org.main.jobsAndPoints;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Objects;

import static org.main.jobsAndPoints.Job.ShowXpAndLevelAndJob;

public class LumberjackJob implements Listener {
    @EventHandler
    public static void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = event.getBlock().getType();
        if (!Objects.equals(JobsFile.GetPlayerJob(player), "Lumberjack")){
            return;
        }
        switch (blockType){
            case Material.ACACIA_LOG:
            case Material.BIRCH_LOG:
            case Material.CHERRY_LOG:
            case Material.DARK_OAK_LOG:
            case Material.JUNGLE_LOG:
            case Material.MANGROVE_LOG:
            case Material.OAK_LOG:
            case Material.PALE_OAK_LOG:
            case Material.SPRUCE_LOG:
                JobsFile.SetPlayerXp(player,"Lumberjack",JobsFile.GetPlayerXp(player,"Lumberjack")+75);
                ShowXpAndLevelAndJob(player,"Lumberjack",JobsFile.GetPlayerLevel(player,"Lumberjack"),JobsFile.GetPlayerXp(player,"Lumberjack"));
        }
    }
}
