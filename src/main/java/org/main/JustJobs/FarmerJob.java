package org.main.JustJobs;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Objects;

import static org.main.JustJobs.Job.ShowXpAndLevelAndJob;

public class FarmerJob implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();
        Material type = block.getType();

        if (!JobsFile.GetPlayerJob(player).equals("Farmer")) return;

        switch (type) {
            case WHEAT:
            case CARROTS:
            case POTATOES:
            case BEETROOTS:
            case NETHER_WART:
            case MELON_STEM:
            case PUMPKIN_STEM:
            case BAMBOO:
            case SUGAR_CANE:
            case COCOA:
                JobsFile.SetPlayerXp(player, "Farmer",
                        JobsFile.GetPlayerXp(player, "Farmer") + 25);

                ShowXpAndLevelAndJob(
                        player,
                        "Farmer",
                        JobsFile.GetPlayerLevel(player, "Farmer"),
                        JobsFile.GetPlayerXp(player, "Farmer")
                );
                break;
        }
    }
    @EventHandler
    public static void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = event.getBlock().getType();
        if (!Objects.equals(JobsFile.GetPlayerJob(player), "Farmer")){
            return;
        }
        if (block.getBlockData() instanceof Ageable ageable) {
            if (!(ageable.getAge()>=ageable.getMaximumAge())){
                return;
            }
        }
        switch (blockType) {
            case Material.WHEAT_SEEDS:
            case Material.WHEAT:
            case Material.BEETROOTS:
            case Material.BEETROOT:
            case Material.CARROTS:
            case Material.POTATOES:
            case Material.POISONOUS_POTATO:
            case Material.MELON_SEEDS:
            case Material.MELON:
            case Material.MELON_SLICE:
            case Material.PUMPKIN_SEEDS:
            case Material.PUMPKIN:
            case Material.CARVED_PUMPKIN:
            case Material.TORCHFLOWER_SEEDS:
            case Material.TORCHFLOWER:
            case Material.PITCHER_POD:
            case Material.PITCHER_PLANT:
            case Material.BAMBOO:
            case Material.COCOA_BEANS:
            case Material.SUGAR_CANE:
            case Material.SWEET_BERRIES:
            case Material.CACTUS:
            case Material.BROWN_MUSHROOM:
            case Material.RED_MUSHROOM:
            case Material.KELP:
            case Material.SEA_PICKLE:
            case Material.GLOW_BERRIES:
            case Material.NETHER_WART:
            case Material.CRIMSON_FUNGUS:
            case Material.WARPED_FUNGUS:
            case Material.CHORUS_FLOWER:
            case Material.CHORUS_PLANT:
            case Material.CHORUS_FRUIT:
                JobsFile.SetPlayerXp(player, "Farmer",
                        JobsFile.GetPlayerXp(player, "Farmer") + 45);

                ShowXpAndLevelAndJob(
                        player,
                        "Farmer",
                        JobsFile.GetPlayerLevel(player, "Farmer"),
                        JobsFile.GetPlayerXp(player, "Farmer")
                );
                break;
        }
    }
}
