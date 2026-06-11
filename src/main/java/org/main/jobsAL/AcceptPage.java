package org.main.jobsAL;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.main.jobsAL.JobsFile.plugin;

public class AcceptPage implements InventoryHolder, Listener {
    private final Inventory inventory;
    public static Map<UUID, Boolean> playerSelected = new HashMap<>();
    public static final Map<UUID, Consumer<Boolean>> waiting = new HashMap<>();
    public AcceptPage(Player player) {
        this.inventory = plugin.getServer().createInventory(this, 9,"Yes or Not:");
        ItemStack greenGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
        ItemMeta meta_greenGlass = greenGlass.getItemMeta();
        meta_greenGlass.setDisplayName("§aYes");
        meta_greenGlass.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        greenGlass.setItemMeta(meta_greenGlass);
        this.inventory.addItem(greenGlass);
        for (int i = 1; i < 6; i++) {
            ItemStack darkGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
            ItemMeta meta_darkGlass = darkGlass.getItemMeta();
            meta_darkGlass.setDisplayName("");
            meta_darkGlass.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            darkGlass.setItemMeta(meta_darkGlass);
            this.inventory.setItem(i, darkGlass);
        }

        ItemStack redGlass = new ItemStack(Material.RED_STAINED_GLASS_PANE,1);
        ItemMeta meta_redGlass = redGlass.getItemMeta();
        meta_redGlass.setDisplayName("§cNo");
        meta_redGlass.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        redGlass.setItemMeta(meta_redGlass);
        this.inventory.addItem(redGlass);
        player.openInventory(getInventory());
    }

    private static boolean getPlayerSelectedOption(Player player){
        return playerSelected.get(player.getUniqueId());
    }
    private void waitForClick(Player player, Consumer<Boolean> callback) {
        waiting.put(player.getUniqueId(), callback);
    }
    public void ask(Player player, Consumer<Boolean> callback) {
        waitForClick(player, callback);
        player.openInventory(getInventory());
    }
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
