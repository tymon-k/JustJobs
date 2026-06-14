package org.main.JustJobs;

import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import java.util.Arrays;

import static org.main.JustJobs.JobsFile.plugin;

public final class JustJobs extends JavaPlugin {
    private LiteralArgumentBuilder<CommandSourceStack> registerLevelToMoney(
            LiteralArgumentBuilder<CommandSourceStack> builder) {

        for (String job : Arrays.stream(JobsEnum.values())
                            .map(Enum::name)
                            .toArray(String[]::new))
        {

            builder.then(
                    Commands.literal(job)
                            .executes(ctx -> {
                                Player player = (Player) ctx.getSource().getSender();

                                if (JobsFile.GetPlayerLevel(player, job) >= 10) {
                                    LevelToVault.ExchangeLevelToVault(player, job);
                                } else {
                                    player.sendMessage("§cYou don't have enough level!");
                                }
                                return 1;
                            })
            );
        }

        return builder;
    }
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
                            Commands.literal("jobs").requires(src -> src.getSender().hasPermission("jobs"))
                                    .then(Commands.literal("select").requires(src -> src.getSender().hasPermission("jobs.select"))
                                            .executes(ctx -> {
                                                Player player = (Player) ctx.getSource().getSender();
                                                player.openInventory(new JobsPage(this, player).getInventory());
                                                return 1;
                                            })
                                    )
                                    .then(registerLevelToMoney(Commands.literal("levelToMoney"))).requires(src -> src.getSender().hasPermission("jobs.levelToMoney"))
                                    .then(Commands.literal("admin")
                                            .then(Commands.literal("setXp").requires(src -> src.getSender().hasPermission("jobs.admin.setXp"))
                                                    .then(Commands.argument("player", ArgumentTypes.player())
                                                            .then(Commands.argument("job", StringArgumentType.word())
                                                                    .suggests((ctx, builder) -> {
                                                                        for (JobsEnum job : JobsEnum.values()) {
                                                                            builder.suggest(job.name());
                                                                        }
                                                                        return builder.buildFuture();
                                                                    })
                                                                    .then(Commands.argument("xp", LongArgumentType.longArg())
                                                                            .executes(ctx -> {
                                                                                final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("player", PlayerSelectorArgumentResolver.class);
                                                                                final Player player = targetResolver.resolve(ctx.getSource()).getFirst();
                                                                                String jobName = StringArgumentType.getString(ctx, "job");
                                                                                boolean validJob = isJobValid(jobName);
                                                                                if (!validJob){
                                                                                    ctx.getSource().getSender().sendMessage("§cJob is not valid!");
                                                                                    return 0;
                                                                                }
                                                                                long xp = LongArgumentType.getLong(ctx, "xp");
                                                                                player.sendMessage(jobName+" "+xp);
                                                                                JobsFile.SetPlayerXp(player, jobName, (int) xp);

                                                                                return 1;
                                                                            })
                                                                    )
                                                            )
                                                    )
                                            )
                                            .then(Commands.literal("setLevel").requires(src -> src.getSender().hasPermission("jobs.admin.setLevel"))
                                                    .then(Commands.argument("player", ArgumentTypes.player())
                                                            .then(Commands.argument("job", StringArgumentType.word())
                                                                    .suggests((ctx, builder) -> {
                                                                        for (JobsEnum job : JobsEnum.values()) {
                                                                            builder.suggest(job.name());
                                                                        }
                                                                        return builder.buildFuture();
                                                                    })
                                                                    .then(Commands.argument("level", LongArgumentType.longArg())
                                                                            .executes(ctx -> {
                                                                                final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("player", PlayerSelectorArgumentResolver.class);
                                                                                final Player player = targetResolver.resolve(ctx.getSource()).getFirst();
                                                                                String jobName = StringArgumentType.getString(ctx, "job");
                                                                                boolean validJob = isJobValid(jobName);
                                                                                if (!validJob){
                                                                                    ctx.getSource().getSender().sendMessage("§cJob is not valid!");
                                                                                    return 0;
                                                                                }
                                                                                long level = LongArgumentType.getLong(ctx, "level");

                                                                                JobsFile.SetPlayerLevel(player, jobName, (int) level);

                                                                                return 1;
                                                                            })
                                                                    )
                                                            )
                                                    )
                                            )
                                    ).build()
                            );
                }
        );
    }

    private static boolean isJobValid(String jobName) {
        boolean isJobValid = false;
        for (JobsEnum job : JobsEnum.values()) {
            if (job.name().equals(jobName)){
                isJobValid=true;
            }
        }
        return isJobValid;
    }
}