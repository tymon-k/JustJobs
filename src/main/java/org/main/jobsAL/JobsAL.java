package org.main.jobsAL;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class JobsAL extends JavaPlugin {

    @Override
    public void onEnable() {
        JobsFile.plugin=this;
        Bukkit.getPluginManager().registerEvents(new JobsPage_Listener(), this);
        Bukkit.getPluginManager().registerEvents(new MinerJob(), this);
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