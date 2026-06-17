package org.main.JustJobs;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.main.JustJobs.JobsFile.plugin;

public class ConfigFile {

    public static void SetupConfigFile() {
        File file = new File(plugin.getDataFolder(), "config.yml");

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();

                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

                config.set("LevelUpXp", Arrays.asList(
                        2000,5000,8000,12000,18000,24000,28000,30000,40000,50000
                ));

                config.set("LevelToMoney", Arrays.asList(
                        200,500,800,1200,1800,2400,2800,3000,4000,5000
                ));

                config.set("ExchangeSpeed", 5000);
                config.set("StartExchangeLevel", 4);

                config.save(file);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static int[] GetLevelUpTable(){
        File file = new File(plugin.getDataFolder(), "config.yml");

        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);

        List<Integer> list = data.getIntegerList("LevelUpXp");

        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public static int[] GetLevelToMoneyTable(){
        File file = new File(plugin.getDataFolder(), "config.yml");

        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);

        List<Integer> list = data.getIntegerList("LevelToMoney");

        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public static int GetExchangeSpeed(){
        File file = new File(plugin.getDataFolder(), "config.yml");

        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);

        return data.getInt(
                "ExchangeSpeed"
        );
    }

    public static int GetStartExchangeLevel(){
        File file = new File(plugin.getDataFolder(), "config.yml");

        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);

        return data.getInt(
                "StartExchangeLevel"
        );
    }
}
