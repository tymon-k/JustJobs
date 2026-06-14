package org.main.JustJobs;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;
import java.util.function.Consumer;

import static org.main.JustJobs.AcceptPage.waiting;

public class AcceptPageListener implements Listener {

    private static final int YES_SLOT = 0;
    private static final int NO_SLOT = 7;

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (!(event.getInventory().getHolder() instanceof AcceptPage)) return;

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();

        if (event.getClickedInventory() == null) return;

        Boolean result = null;

        if (event.getSlot() == YES_SLOT) {
            result = true;
        }

        if (event.getSlot() == NO_SLOT) {
            result = false;
        }

        if (result == null) return;

        player.closeInventory();

        Consumer<Boolean> callback = waiting.remove(uuid);
        if (callback != null) {
            callback.accept(result);
        }
    }
}
