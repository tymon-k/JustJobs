package org.main.jobsAndPoints;

import io.papermc.paper.event.player.PlayerTradeEvent;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

import static org.main.jobsAndPoints.Job.ShowXpAndLevelAndJob;

public class TraderJob implements Listener {
    @EventHandler
    public void onTrade(PlayerTradeEvent event) {
        Player player = event.getPlayer();
        if (Objects.equals(JobsFile.GetPlayerJob(player), "Trader")) return;
        if (!(event.getVillager() instanceof Villager villager)) {
            return; // np. wandering trader
        }

        int level = villager.getVillagerLevel();

        JobsFile.SetPlayerXp(
                player,
                "Trader",
                JobsFile.GetPlayerXp(player, "Trader") + 40*level
        );

        ShowXpAndLevelAndJob(player,"Trader",JobsFile.GetPlayerLevel(player,"Trader"),JobsFile.GetPlayerXp(player,"Trader"));
    }
}
