package org.main.jobsAL;

import io.papermc.paper.event.player.PlayerTradeEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static org.main.jobsAL.Job.ShowXpAndLevelAndJob;

public class ExplorerJob implements Listener {
    private final HashMap<UUID, Location> lastCheckpoint = new HashMap<>();

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getFrom().getBlockX() == event.getTo().getBlockX()
                && event.getFrom().getBlockY() == event.getTo().getBlockY()
                && event.getFrom().getBlockZ() == event.getTo().getBlockZ()) {
            return;
        }

        Player player = event.getPlayer();
        if (!Objects.equals(JobsFile.GetPlayerJob(player), "Explorer")) return;
        Location last = lastCheckpoint.get(player.getUniqueId());

        if (last == null) {
            lastCheckpoint.put(player.getUniqueId(), player.getLocation());
            return;
        }

        if (last.distance(player.getLocation()) >= 16) {
            JobsFile.SetPlayerXp(
                    player,
                    "Explorer",
                    JobsFile.GetPlayerXp(player, "Explorer") + 25
            );

            ShowXpAndLevelAndJob(player,"Explorer",JobsFile.GetPlayerLevel(player,"Explorer"),JobsFile.GetPlayerXp(player,"Explorer"));

            lastCheckpoint.put(player.getUniqueId(), player.getLocation());
        }
    }
}