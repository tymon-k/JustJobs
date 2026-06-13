package org.main.jobsAndPoints;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class JobsPage implements InventoryHolder {
    private String levelToProgressBar(int level){
        return switch (level) {
            case 0 -> "§7|§1--§9--§5--§c--§4--§7|";
            case 1 -> "§7|§1█-§9--§5--§c--§4--§7|";
            case 2 -> "§7|§1██§9--§5--§c--§4--§7|";
            case 3 -> "§7|§1██§9█-§5--§c--§4--§7|";
            case 4 -> "§7|§1██§9██§5--§c--§4--§7|";
            case 5 -> "§7|§1██§9██§5█-§c--§4--§7|";
            case 6 -> "§7|§1██§9██§5██§c--§4--§7|";
            case 7 -> "§7|§1██§9██§5██§c█-§4--§7|";
            case 8 -> "§7|§1██§9██§5██§c██§4--§7|";
            case 9 -> "§7|§1██§9██§5██§c██§4█-§7|";
            case 10 -> "§7|§1██§9██§5██§c██§4██§7|";
            default -> "Error report to the admin!";
        };
    }
    private Material jobToMaterialEnum(String job){
        return switch (job){
            case "Miner" -> Material.NETHERITE_PICKAXE;
            case "Fisherman" -> Material.FISHING_ROD;
            case "Warrior" -> Material.NETHERITE_SWORD;
            case "Lumberjack" -> Material.NETHERITE_AXE;
            case "Farmer" -> Material.NETHERITE_HOE;
            case "Builder" -> Material.BRICKS;
            case "Magician" -> Material.POTION;
            case "Trader" -> Material.EMERALD;
            case "Explorer" -> Material.COMPASS;
            default -> Material.GRASS_BLOCK;
        };
    }
    private String levelAndJobToLore(String job,int level){
        switch (job){
            case "Miner":
                return "Adds haste "+ level;
            case "Fisherman":
                return "Adds luck "+ level;
            case "Warrior":
                return "Adds strength "+ level /2;
            case "Lumberjack":
                return level*10+"% to place tree sapling";
            case "Farmer":
                return level*10+"% to place seeds";
            case "Builder":
                return "Adds safe fall distance +"+ level *2+" blocks";
            case "Trader":
                return "Adds hero of the village "+ level;
            case "Explorer":
                return "Adds speed "+ level;
            default:
                return "Error! Report to the admin!";
        }
    }
    private ItemStack getItemStackToGui(String job, int level) {


        ItemStack item = new ItemStack(jobToMaterialEnum(job), level+1);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§2" + job + "!");

        meta.setLore(Arrays.asList(
                levelToProgressBar(level),
                "§b" + levelAndJobToLore(job, level)
        ));

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);
        return item;
    }
    private final Inventory inventory;

    public JobsPage(JobsAndPoints plugin, Player player) {
        // Create an Inventory with 9 slots, `this` here is our InventoryHolder.
        this.inventory = plugin.getServer().createInventory(this, 27,"Select Job To Work:");
        int player_Miner = JobsFile.GetPlayerLevel(player,"Miner");
        int player_Fisherman = JobsFile.GetPlayerLevel(player,"Fisherman");
        int player_Warrior = JobsFile.GetPlayerLevel(player,"Warrior");
        int player_Lumberjack = JobsFile.GetPlayerLevel(player,"Lumberjack");
        int player_Farmer = JobsFile.GetPlayerLevel(player,"Farmer");
        int player_Builder = JobsFile.GetPlayerLevel(player,"Builder");
        int player_Trader = JobsFile.GetPlayerLevel(player,"Trader");
        int player_Explorer = JobsFile.GetPlayerLevel(player,"Explorer");
        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);

        int slot = 0;

        for (int i = 0; i < 4 && slot < 27; i++) {
            this.inventory.setItem(slot++, glass);
        }

        if (slot < 27) {
            this.inventory.setItem(slot++, getItemStackToGui("Miner", player_Miner));
        }

        for (int i = 0; i < 4 && slot < 27; i++) {
            this.inventory.setItem(slot++, glass);
        }

        if (slot < 27) {
            this.inventory.setItem(slot++, glass);
        }

        if (slot < 27) this.inventory.setItem(slot++, getItemStackToGui("Fisherman", player_Fisherman));
        if (slot < 27) this.inventory.setItem(slot++, getItemStackToGui("Warrior", player_Warrior));
        if (slot < 27) this.inventory.setItem(slot++, getItemStackToGui("Lumberjack", player_Lumberjack));
        if (slot < 27) this.inventory.setItem(slot++, getItemStackToGui("Farmer", player_Farmer));
        if (slot < 27) this.inventory.setItem(slot++, getItemStackToGui("Builder", player_Builder));
        if (slot < 27) this.inventory.setItem(slot++, getItemStackToGui("Trader", player_Trader));
        if (slot < 27) this.inventory.setItem(slot++, getItemStackToGui("Explorer", player_Explorer));

        if (slot < 27) {
            this.inventory.setItem(slot++, glass);
        }

        for (int i = 0; i < 10 && slot < 27; i++) {
            this.inventory.setItem(slot++, glass);
        }
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

}