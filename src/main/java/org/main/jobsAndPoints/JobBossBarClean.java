package org.main.jobsAndPoints;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class JobBossBarClean implements Listener {
    @EventHandler
    public void onQuit(org.bukkit.event.player.PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        BossBar bar = Job.bars.remove(uuid);
        if (bar != null) {
            bar.removeAll();
        }
    }
}