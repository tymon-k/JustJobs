package org.main.JustJobs;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import static org.main.JustJobs.Job.ShowXpAndLevelAndJob;

public class FishermanJob implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent event) {

        Player player = event.getPlayer();

        if (!"Fisherman".equals(JobsFile.GetPlayerJob(player)))
            return;

        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            double chance = event.getHook().getBiteChance()*100;
            int xp = JobsFile.GetPlayerXp(player,"Fisherman");

            xp += (int) Math.sqrt(10000-chance*chance)*5;
            JobsFile.SetPlayerXp(player,"Fisherman",xp);

            ShowXpAndLevelAndJob(
                    player,
                    "Fisherman",
                    JobsFile.GetPlayerLevel(player,"Fisherman"),
                    xp
            );
        }
    }
}
