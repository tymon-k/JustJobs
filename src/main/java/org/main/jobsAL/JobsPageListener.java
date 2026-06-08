package org.main.jobsAL;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JobsPageListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        if (!(event.getInventory().getHolder() instanceof JobsPage)) return;

        event.setCancelled(true);

        ItemStack item = event.getCurrentItem();
        if (item == null || item.getType().isAir()) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) return;

        String name = meta.getDisplayName();

        // usuwa kolory i !
        name = name.replace("!", "").trim();

        player.closeInventory();
        player.sendMessage("§dJob selected: " + name);
        name = name.replace("§2", "").trim();
        JobsFile.SetPlayerJob(player,name);
    }
}
