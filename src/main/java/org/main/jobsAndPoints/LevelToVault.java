package org.main.jobsAndPoints;

import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.main.jobsAndPoints.JobsFile.plugin;

public class LevelToVault {

    public static void animateDrain(Player player, String job) {

        final Map<UUID, BossBar> bars = new HashMap<>();

        int[] levelUp = Job.levelUp;

        int level = JobsFile.GetPlayerLevel(player, job);
        int xp = JobsFile.GetPlayerXp(player, job);


        new BukkitRunnable() {

            int ticks = 0;

            int currentLevel = level;
            int currentXp = xp;

            int drainPerTick = 500;

            @Override
            public void run() {

                ticks++;

                currentXp -= drainPerTick;

                while (currentXp <= 0 && currentLevel > 0) {
                    currentLevel--;
                    VaultAPI.addPlayerMoney(player, (double) levelUp[Math.min(currentLevel + 1, 9)] /10);
                    player.getWorld().strikeLightningEffect(player.getLocation());
                    int prevLevelMax = levelUp[Math.max(0, currentLevel)];
                    currentXp += prevLevelMax;
                    player.getWorld().spawnParticle(
                            Particle.END_ROD,
                            player.getLocation(),
                            50, 0.2, 0.4, 0.2, 0.01
                    );
                    player.getWorld().spawnParticle(Particle.DRAGON_BREATH, player.getLocation(), 3000, 0, 0, 0, -1.0, 1.0f);
                    JobsFile.SetPlayerLevel(player, job, currentLevel);
                    JobsFile.SetPlayerXp(player, job, currentXp);
                }

                if (currentLevel <= 0) {
                    currentLevel = 0;
                    currentXp = 0;
                }

                int maxXp = levelUp[Math.min(currentLevel, levelUp.length - 1)];

                double progress = maxXp == 0
                        ? 0
                        : Math.max(0.0, Math.min(1.0, (double) currentXp / maxXp));

                BossBar bar = bars.computeIfAbsent(player.getUniqueId(), uuid -> {
                    BossBar b = Bukkit.createBossBar("", BarColor.RED, BarStyle.SOLID);
                    b.addPlayer(player);
                    return b;
                });

                bar.setTitle("§2" + job + " §bxp: " + currentXp + " §9Level: " + currentLevel);

                bar.setProgress(progress);



                if (currentLevel<=0&&currentXp<=0) {
                    JobsFile.SetPlayerLevel(player, job, 0);
                    JobsFile.SetPlayerXp(player, job, 0);
                    bar.removePlayer(player);
                    cancel();
                }
            }

        }.runTaskTimer(plugin, 0L, 1L);
    }

    public static void ExchangeLevelToVault(Player player, String job) {
        AcceptPage page = new AcceptPage(player);

        page.ask(player, result -> {
            if (result) {

                Location loc = player.getLocation();
                World world = player.getWorld();

                world.spawnParticle(Particle.DRAGON_BREATH, loc, 3000, 0, 0, 0, 0.2, 1.0f);
                world.spawnParticle(Particle.COMPOSTER, loc, 3000, 1, 1, 1, 0.1);
                world.spawnParticle(Particle.DRIPPING_LAVA, loc, 3000, 1, 1, 1, 0.05);
                world.spawnParticle(Particle.ENCHANT, loc, 5000, 1, 1, 1, 1);
                world.spawnParticle(Particle.END_ROD, loc, 4500, 1, 1, 1, 0.05);

                world.strikeLightningEffect(loc);
                world.strikeLightningEffect(loc);
                world.strikeLightningEffect(loc);
                world.strikeLightningEffect(loc);

                animateDrain(player, job);
            } else {
                player.sendMessage("Wybrano NO");
            }
        });
    }
}