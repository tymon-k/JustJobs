package org.main.jobsAL;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import static org.main.jobsAL.Job.ShowXpAndLevelAndJob;

public class FisherJob implements Listener {
    @EventHandler
    public void onFish(PlayerFishEvent event) {

        Player player = event.getPlayer();

        if (!"Fisherman".equals(JobsFile.GetPlayerJob(player)))
            return;

        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {

            int xp = JobsFile.GetPlayerXp(player,"Fisherman");

            xp += 10;

            ShowXpAndLevelAndJob(
                    player,
                    "Fisherman",
                    JobsFile.GetPlayerLevel(player,"Fisherman"),
                    xp
            );
        }
    }
}
