package org.main.jobsAndPoints;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static org.main.jobsAndPoints.JobsFile.plugin;

public final class JobsAndPoints extends JavaPlugin {

    @Override
    public void onEnable() {
        plugin=this;
        VaultAPI.setupEconomy(plugin);
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
        Bukkit.getPluginManager().registerEvents(new AcceptPageListener(), this);
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {

            for (Player player : Bukkit.getOnlinePlayers()) {
                JobsEffects.giveEffectForJob(player);
            }

        }, 0L, 20L);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholders().register();
        }
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
                                    ).then(
                                    Commands.literal("levelToMoney")
                                            .then(Commands.literal("Miner")
                                                    .executes(ctx -> {
                                                        Player player = (Player) ctx.getSource().getSender();

                                                        if (JobsFile.GetPlayerLevel(player, "Miner") >= 10) {
                                                            LevelToVault.ExchangeLevelToVault(player, "Miner");
                                                        } else {
                                                            player.sendMessage("§cYou don't have enough level!");
                                                        }

                                                        return 1;
                                                    })
                                            )
                                            .then(Commands.literal("Fisherman")
                                                    .executes(ctx -> {
                                                        Player player = (Player) ctx.getSource().getSender();

                                                        if (JobsFile.GetPlayerLevel(player, "Fisherman") >= 10) {
                                                            LevelToVault.ExchangeLevelToVault(player, "Fisherman");
                                                        } else {
                                                            player.sendMessage("§cYou don't have enough level!");
                                                        }

                                                        return 1;
                                                    })
                                            )
                                            .then(Commands.literal("Warrior")
                                                    .executes(ctx -> {
                                                        Player player = (Player) ctx.getSource().getSender();

                                                        if (JobsFile.GetPlayerLevel(player, "Warrior") >= 10) {
                                                            LevelToVault.ExchangeLevelToVault(player, "Warrior");
                                                        } else {
                                                            player.sendMessage("§cYou don't have enough level!");
                                                        }

                                                        return 1;
                                                    })
                                            )
                                            .then(Commands.literal("Lumberjack")
                                                    .executes(ctx -> {
                                                        Player player = (Player) ctx.getSource().getSender();

                                                        if (JobsFile.GetPlayerLevel(player, "Lumberjack") >= 10) {
                                                            LevelToVault.ExchangeLevelToVault(player, "Lumberjack");
                                                        } else {
                                                            player.sendMessage("§cYou don't have enough level!");
                                                        }

                                                        return 1;
                                                    })
                                            )
                                            .then(Commands.literal("Farmer")
                                                    .executes(ctx -> {
                                                        Player player = (Player) ctx.getSource().getSender();

                                                        if (JobsFile.GetPlayerLevel(player, "Farmer") >= 10) {
                                                            LevelToVault.ExchangeLevelToVault(player, "Farmer");
                                                        } else {
                                                            player.sendMessage("§cYou don't have enough level!");
                                                        }

                                                        return 1;
                                                    })
                                            )
                                            .then(Commands.literal("Builder")
                                                    .executes(ctx -> {
                                                        Player player = (Player) ctx.getSource().getSender();

                                                        if (JobsFile.GetPlayerLevel(player, "Builder") >= 10) {
                                                            LevelToVault.ExchangeLevelToVault(player, "Builder");
                                                        } else {
                                                            player.sendMessage("§cYou don't have enough level!");
                                                        }

                                                        return 1;
                                                    })
                                            )
                                            .then(Commands.literal("Trader")
                                                    .executes(ctx -> {
                                                        Player player = (Player) ctx.getSource().getSender();

                                                        if (JobsFile.GetPlayerLevel(player, "Trader") >= 10) {
                                                            LevelToVault.ExchangeLevelToVault(player, "Trader");
                                                        } else {
                                                            player.sendMessage("§cYou don't have enough level!");
                                                        }

                                                        return 1;
                                                    })
                                            )
                                            .then(Commands.literal("Explorer")
                                                    .executes(ctx -> {
                                                        Player player = (Player) ctx.getSource().getSender();

                                                        if (JobsFile.GetPlayerLevel(player, "Explorer") >= 10) {
                                                            LevelToVault.ExchangeLevelToVault(player, "Explorer");
                                                        } else {
                                                            player.sendMessage("§cYou don't have enough level!");
                                                        }

                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(
                                            Commands.literal("getMoney")
                                                    .executes(ctx -> {
                                                        ctx.getSource().getSender().sendMessage(Double.toString(VaultAPI.getPlayerMoney((Player) ctx.getSource().getSender())));
                                                        return 1;
                                                    })
                                    )
                                    .build()
                            );
                }
        );
    }
}