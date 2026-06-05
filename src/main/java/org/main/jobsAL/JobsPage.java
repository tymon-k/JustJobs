package org.main.jobsAL;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class JobsPage implements InventoryHolder {
    private String levelToProgressBar(int level){
        return switch (level) {
            case 1 -> "§7|§1--§9--§5--§c--§4--";
            case 2 -> "§7|§1█-§9--§5--§c--§4--";
            case 3 -> "§7|§1██§9--§5--§c--§4--";
            case 4 -> "§7|§1██§9█-§5--§c--§4--";
            case 5 -> "§7|§1██§9██§5--§c--§4--";
            case 6 -> "§7|§1██§9██§5█-§c--§4--";
            case 7 -> "§7|§1██§9██§5██§c--§4--";
            case 8 -> "§7|§1██§9██§5██§c█-§4--";
            case 9 -> "§7|§1██§9██§5██§c██§4--";
            case 10 -> "§7|§1██§9██§5██§c██§4█-";
            case 11 -> "§7|§1██§9██§5██§c██§4██";
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
        int leveltouse = level-1;
        if (leveltouse == 0){
            return "No bonus at this level";
        }
        switch (job){
            case "Miner":
                return "Adds haste "+leveltouse;
            case "Fisherman":
                return "Adds luck "+leveltouse;
            case "Warrior":
                return "Adds strength "+leveltouse/2;
            case "Lumberjack":
                switch (leveltouse){
                    case 1:
                        return "10% to place tree sapling";
                    case 2:
                        return "20% to place tree sapling";
                    case 3:
                        return "30% to place tree sapling";
                    case 4:
                        return "40% to place tree sapling";
                    case 5:
                        return "50% to place tree sapling";
                    case 6:
                        return "70% to place tree sapling";
                    case 7:
                        return "80% to place tree sapling";
                    case 8:
                        return "100% to place tree sapling";
                    default:
                        return "No bonus at this level";
                }
            case "Farmer":
                switch (leveltouse){
                    case 1:
                        return "10% to place seeds";
                    case 2:
                        return "20% to place seeds";
                    case 3:
                        return "30% to place seeds";
                    case 4:
                        return "40% to place seeds";
                    case 5:
                        return "50% to place seeds";
                    case 6:
                        return "70% to place seeds";
                    case 7:
                        return "80% to place seeds";
                    case 8:
                        return "100% to place seeds";
                    default:
                        return "No bonus at this level";
                }
            case "Builder":
                return "Adds safe fall distance +"+leveltouse*2+" blocks";
            case "Trader":
                return "Adds hero of the village "+leveltouse;
            case "Explorer":
                return "Adds speed "+leveltouse;
            default:
                return "Error! Report to the admin!";
        }
    }
    private ItemStack getItemStackToGui(String job,int level){
        ItemStack item = new ItemStack(jobToMaterialEnum(job),level);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§2"+job+"!");

        meta.setLore(Arrays.asList(
                levelToProgressBar(level),
                "§b"+levelAndJobToLore(job,level)
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }
    private final Inventory inventory;

    public JobsPage(JobsAL plugin, Player player) {
        // Create an Inventory with 9 slots, `this` here is our InventoryHolder.
        this.inventory = plugin.getServer().createInventory(this, 27,"Select Job To Work:");
        int player_Miner = 1;
        int player_Fisherman = 1;
        int player_Warrior = 1;
        int player_Lumberjack = 1;
        int player_Farmer = 1;
        int player_Builder = 1;
        int player_Trader = 1;
        int player_Explorer = 1;
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