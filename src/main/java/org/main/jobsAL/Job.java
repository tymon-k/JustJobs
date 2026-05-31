package org.main.jobsAL;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Job {
    private static final Map<UUID, BossBar> bars = new HashMap<>();
    private static final Map<UUID, Long> lastUpdate = new HashMap<>();

    public static void ShowXpAndLevelAndJob(Player player, String job, int level, int xp, int[] levelUp) {

        while (level < levelUp.length && xp >= levelUp[level]) {
            xp -= levelUp[level];
            level++;
        }

        JobsFile.SetPlayerLevel(player, job, level);
        JobsFile.SetPlayerXp(player, job, xp);

        updateBar(player, job, level, xp, levelUp);
    }

    private static BossBar getBar(Player player) {

        return bars.computeIfAbsent(player.getUniqueId(), uuid -> {
            BossBar bar = Bukkit.createBossBar(
                    "",
                    BarColor.RED,
                    BarStyle.SOLID
            );
            bar.addPlayer(player);
            return bar;
        });
    }

    public static void updateBar(Player player, String job, int level, int xp, int[] levelUp) {

        BossBar bar = getBar(player);

        int max = levelUp[Math.min(level, levelUp.length - 1)];
        double progress = Math.max(0.0, Math.min(1.0, xp / (double) max));

        bar.setTitle("§2" + job + " §bxp: " + xp + " §9Level: " + level);
        bar.setProgress(progress);

        lastUpdate.put(player.getUniqueId(), System.currentTimeMillis());
    }
}
