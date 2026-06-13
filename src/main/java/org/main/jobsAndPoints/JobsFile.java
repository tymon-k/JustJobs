package org.main.jobsAndPoints;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class JobsFile {
    static JobsAndPoints plugin;
    public JobsFile(JobsAndPoints plugin){
        JobsFile.plugin =plugin;
    }
    public static void SetPlayerLevel(Player player,String job, int level){
        File file = new File(plugin.getDataFolder(), "playersData.yml");

        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);

        data.set(player.getUniqueId().toString()+"."+job+".level", level);

        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SetPlayerXp(Player player,String job,int xp){
        File file = new File(plugin.getDataFolder(), "playersData.yml");

        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);

        data.set(player.getUniqueId().toString()+"."+job+".xp", xp);

        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SetPlayerJob(Player player,String job){
        File file = new File(plugin.getDataFolder(), "playersData.yml");

        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);

        data.set(player.getUniqueId().toString()+".selectedJob", job);

        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String GetPlayerJob(Player player){
        File file = new File(plugin.getDataFolder(), "playersData.yml");

        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);

        return data.getString(
                player.getUniqueId().toString() + ".selectedJob"
        );
    }

    public static int GetPlayerXp(Player player,String job){
        File file = new File(plugin.getDataFolder(), "playersData.yml");

        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);

        return data.getInt(
                player.getUniqueId().toString()+"."+job+".xp"
        );
    }

    public static int GetPlayerLevel(Player player,String job){
        File file = new File(plugin.getDataFolder(), "playersData.yml");

        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        int level = 1;
        if (data.contains(player.getUniqueId().toString()+"."+job+".level")){
            level = data.getInt(
                    player.getUniqueId().toString()+"."+job+".level"
            );
        }
        return level;
    }
}
