package org.main.jobsAL;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static org.main.jobsAL.JobsFile.plugin;

public final class JobsAL extends JavaPlugin {

    @Override
    public void onEnable() {
        plugin=this;
        Bukkit.getPluginManager().registerEvents(new JobsPageListener(), this);
        Bukkit.getPluginManager().registerEvents(new JobBossBarClean(), this);
        Bukkit.getPluginManager().registerEvents(new MinerJob(), this);
        Bukkit.getPluginManager().registerEvents(new FishermanJob(), this);
        Bukkit.getPluginManager().registerEvents(new WarriorJob(), this);
        Bukkit.getPluginManager().registerEvents(new LumberjackJob(), this);
        Bukkit.getPluginManager().registerEvents(new FarmerJob(), this);
        Bukkit.getPluginManager().registerEvents(new BuilderJob(), this);
        Bukkit.getPluginManager().registerEvents(new TraderJob(), this);
        Bukkit.getPluginManager().registerEvents(new ExplorerJob(), this);
        Bukkit.getPluginManager().registerEvents(new LumberjackEffects(), this);
        Bukkit.getPluginManager().registerEvents(new FarmerEffects(), this);
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {

            for (Player player : Bukkit.getOnlinePlayers()) {
                JobsEffects.giveEffectForJob(player);
            }

        }, 0L, 1L);
        getLifecycleManager().registerEventHandler(
                LifecycleEvents.COMMANDS,
                event -> {

                    event.registrar().register(
                            Commands.literal("jobs").then(
                                        Commands.literal("select")
                                                .executes(ctx -> {
                                                    Player player = (Player) ctx.getSource().getSender();
                                                    JobsPage page = new JobsPage(this, player);
                                                    player.openInventory(page.getInventory());
                                                    return 1;
                                                })
                                    )
                                    .build()
                    );
                }
        );
        // effects as bonus
        new BukkitRunnable() {

            @Override
            public void run() {

                for (Player player : Bukkit.getOnlinePlayers()) {

                    player.sendActionBar(
                            Component.text("HP: " + player.getHealth())
                    );
                }
            }

        }.runTaskTimer(this, 0L, 1L);
    }
}