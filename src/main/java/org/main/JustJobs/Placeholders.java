package org.main.JustJobs;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class Placeholders extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "JustJobs";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Kresu";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        int[] levelUp = ConfigFile.GetLevelUpTable();
        if (player == null) return "";

        // %JobsAndPoints_selectedJob%
        if (params.equalsIgnoreCase("selectedJob")) {
            return JobsFile.GetPlayerJob(player);
        }

        // %JobsAndPoints_xp_Miner%
        if (params.startsWith("xp_")) {

            String jobName = params.substring(3);
            return String.valueOf(JobsFile.GetPlayerXp(player, jobName));
        }
        if (params.startsWith("fullXp_")) {
            String jobName = params.substring(7);
            int all = JobsFile.GetPlayerXp(player,jobName);
            for (int i = 0;i<JobsFile.GetPlayerLevel(player,jobName);i++){
                all+=levelUp[i];
            }

            return String.valueOf(all);
        }
        // %JobsAndPoints_level_Miner%
        if (params.startsWith("level_")) {

            String jobName = params.substring(6);
            return String.valueOf(JobsFile.GetPlayerLevel(player, jobName));
        }

        return "";
    }
}