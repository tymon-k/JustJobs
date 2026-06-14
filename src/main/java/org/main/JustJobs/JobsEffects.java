package org.main.JustJobs;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class JobsEffects {
    private static final HashMap<UUID, String> lastJob = new HashMap<>();
    static final Random random = new Random();

    public static void giveEffectForJob(Player player) {

        UUID uuid = player.getUniqueId();

        String currentJob = JobsFile.GetPlayerJob(player);
        String oldJob = lastJob.get(uuid);

        if (currentJob == null) return;
        if (oldJob == null) {
            lastJob.put(uuid, currentJob);
            oldJob = currentJob;
        }

        if (!oldJob.equals(currentJob)) {

            switch (oldJob) {
                case "Miner" -> player.removePotionEffect(PotionEffectType.HASTE);
                case "Fisherman" -> player.removePotionEffect(PotionEffectType.LUCK);
                case "Trader" -> player.removePotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE);
                case "Explorer" -> player.removePotionEffect(PotionEffectType.SPEED);
                case "Warrior" -> player.removePotionEffect(PotionEffectType.STRENGTH);
                case "Builder" -> player.getAttribute(Attribute.SAFE_FALL_DISTANCE).setBaseValue(3.0);
            }

            lastJob.put(uuid, currentJob);
            oldJob = currentJob;
        }

        int lvl;

        switch (currentJob) {

            case "Miner":
                lvl = JobsFile.GetPlayerLevel(player, "Miner");
                player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 40, lvl));
                break;

            case "Fisherman":
                lvl = JobsFile.GetPlayerLevel(player, "Fisherman");
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 40, lvl));
                break;

            case "Warrior":
                lvl = JobsFile.GetPlayerLevel(player, "Warrior");
                player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 40, lvl / 2));
                break;

            case "Explorer":
                lvl = JobsFile.GetPlayerLevel(player, "Explorer");
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, lvl));
                break;

            case "Trader":
                lvl = JobsFile.GetPlayerLevel(player, "Trader");
                player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 40, lvl));
                break;

            case "Builder":
                lvl = JobsFile.GetPlayerLevel(player, "Builder");
                player.getAttribute(Attribute.SAFE_FALL_DISTANCE).setBaseValue(3.0 + lvl);
                break;
        }
    }

}
