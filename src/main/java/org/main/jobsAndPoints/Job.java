package org.main.jobsAndPoints;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Job {
    public static final Map<UUID, BossBar> bars = new HashMap<>();
    private static final Map<UUID, BukkitTask> hideTasks = new HashMap<>();
    static int[] levelUp = {2000,5000,8000,12000,18000,24000,28000,30000,40000,50000};

    public static void ShowXpAndLevelAndJob(Player player, String job, int level, int xp) {

        while (level < levelUp.length && xp >= levelUp[level]) {
            xp -= levelUp[level];
            level++;
            player.getWorld().spawnParticle(
                    Particle.CLOUD,
                    player.getLocation(),
                    1000,
                    0, 0, 0,
                    0.5
            );
            player.getWorld().spawnParticle(
                    Particle.TOTEM_OF_UNDYING,
                    player.getLocation(),
                    1000,
                    0, 0, 0,
                    0.5
            );
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

        double progress = (double) xp / max;
        if (progress > 1.0) progress = 1.0;
        if (progress < 0.0) progress = 0.0;

        bar.setTitle("§2" + job + " §bxp: " + xp + " §9Level: " + level);
        bar.setProgress(progress);

        UUID uuid = player.getUniqueId();

        if (hideTasks.containsKey(uuid)) {
            hideTasks.get(uuid).cancel();
        }

        BukkitTask task = Bukkit.getScheduler().runTaskLater(
                JobsFile.plugin,
                () -> {
                    BossBar b = bars.get(uuid);
                    if (b != null) {
                        b.removeAll();
                        bars.remove(uuid);
                    }
                },
                20L
        );

        hideTasks.put(uuid, task);
    }
}
