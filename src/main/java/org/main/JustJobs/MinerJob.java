package org.main.JustJobs;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Objects;

import static org.main.JustJobs.Job.ShowXpAndLevelAndJob;

public class MinerJob implements Listener {
    static int[] levelUp = {2000,5000,8000,12000,18000,24000,28000,30000,40000,50000};

    @EventHandler
    public static void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = event.getBlock().getType();
        if (!Objects.equals(JobsFile.GetPlayerJob(player), "Miner")){
            return;
        }
        switch (blockType){
            case Material.DIAMOND_ORE:
            case Material.DEEPSLATE_DIAMOND_ORE:
                JobsFile.SetPlayerXp(player,"Miner",JobsFile.GetPlayerXp(player,"Miner")+35);
                ShowXpAndLevelAndJob(player,"Miner",JobsFile.GetPlayerLevel(player,"Miner"),JobsFile.GetPlayerXp(player,"Miner"));
                break;
            case Material.ANCIENT_DEBRIS:
                JobsFile.SetPlayerXp(player,"Miner",JobsFile.GetPlayerXp(player,"Miner")+40);
                ShowXpAndLevelAndJob(player,"Miner",JobsFile.GetPlayerLevel(player,"Miner"),JobsFile.GetPlayerXp(player,"Miner"));
                break;
            case Material.DEEPSLATE_COAL_ORE:
            case Material.COAL_ORE:
                JobsFile.SetPlayerXp(player,"Miner",JobsFile.GetPlayerXp(player,"Miner")+5);
                ShowXpAndLevelAndJob(player,"Miner",JobsFile.GetPlayerLevel(player,"Miner"),JobsFile.GetPlayerXp(player,"Miner"));
                break;
            case Material.COPPER_ORE:
            case Material.DEEPSLATE_COPPER_ORE:
                JobsFile.SetPlayerXp(player,"Miner",JobsFile.GetPlayerXp(player,"Miner")+10);
                ShowXpAndLevelAndJob(player,"Miner",JobsFile.GetPlayerLevel(player,"Miner"),JobsFile.GetPlayerXp(player,"Miner"));
                break;
            case Material.IRON_ORE:
            case Material.DEEPSLATE_IRON_ORE:
                JobsFile.SetPlayerXp(player,"Miner",JobsFile.GetPlayerXp(player,"Miner")+15);
                ShowXpAndLevelAndJob(player,"Miner",JobsFile.GetPlayerLevel(player,"Miner"),JobsFile.GetPlayerXp(player,"Miner"));
                break;
            case Material.LAPIS_ORE:
            case Material.DEEPSLATE_LAPIS_ORE:
                JobsFile.SetPlayerXp(player,"Miner",JobsFile.GetPlayerXp(player,"Miner")+25);
                ShowXpAndLevelAndJob(player,"Miner",JobsFile.GetPlayerLevel(player,"Miner"),JobsFile.GetPlayerXp(player,"Miner"));
                break;
            case Material.GOLD_ORE:
            case Material.DEEPSLATE_GOLD_ORE:
                JobsFile.SetPlayerXp(player,"Miner",JobsFile.GetPlayerXp(player,"Miner")+20);
                ShowXpAndLevelAndJob(player,"Miner",JobsFile.GetPlayerLevel(player,"Miner"),JobsFile.GetPlayerXp(player,"Miner"));
                break;
            case Material.REDSTONE_ORE:
            case Material.DEEPSLATE_REDSTONE_ORE:
                JobsFile.SetPlayerXp(player,"Miner",JobsFile.GetPlayerXp(player,"Miner")+30);
                ShowXpAndLevelAndJob(player,"Miner",JobsFile.GetPlayerLevel(player,"Miner"),JobsFile.GetPlayerXp(player,"Miner"));
                break;
            case Material.EMERALD_ORE:
            case Material.DEEPSLATE_EMERALD_ORE:
                JobsFile.SetPlayerXp(player,"Miner",JobsFile.GetPlayerXp(player,"Miner")+45);
                ShowXpAndLevelAndJob(player,"Miner",JobsFile.GetPlayerLevel(player,"Miner"),JobsFile.GetPlayerXp(player,"Miner"));
                break;
            case Material.NETHER_QUARTZ_ORE:
            case Material.NETHER_GOLD_ORE:
                JobsFile.SetPlayerXp(player,"Miner",JobsFile.GetPlayerXp(player,"Miner")+2);
                ShowXpAndLevelAndJob(player,"Miner",JobsFile.GetPlayerLevel(player,"Miner"),JobsFile.GetPlayerXp(player,"Miner"));
                break;
            case Material.STONE, Material.DEEPSLATE:
                JobsFile.SetPlayerXp(player,"Miner",JobsFile.GetPlayerXp(player,"Miner")+1);
                ShowXpAndLevelAndJob(player,"Miner",JobsFile.GetPlayerLevel(player,"Miner"),JobsFile.GetPlayerXp(player,"Miner"));
                break;
        }
    }
}
