package org.main.jobsAndPoints;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static org.main.jobsAndPoints.Job.ShowXpAndLevelAndJob;

public class BuilderJob implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (!JobsFile.GetPlayerJob(player).equals("Builder")) return;
        JobsFile.SetPlayerXp(player, "Builder",
                JobsFile.GetPlayerXp(player, "Builder") + 1);

        ShowXpAndLevelAndJob(
                player,
                "Builder",
                JobsFile.GetPlayerLevel(player, "Builder"),
                JobsFile.GetPlayerXp(player, "Builder")
        );
    }
}
