package org.main.JustJobs;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import static org.main.JustJobs.Job.ShowXpAndLevelAndJob;

public class WarriorJob implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        Player killer = event.getEntity().getKiller();
        if (killer == null) return;
        if (!"Warrior".equals(JobsFile.GetPlayerJob(killer)))
            return;
        if (killer != null) {
            int maxHealth = (int) event.getEntity().getAttribute(Attribute.MAX_HEALTH).getValue();

            int oldXp = JobsFile.GetPlayerXp(killer, "Warrior");

            JobsFile.SetPlayerXp(killer, "Warrior", oldXp + maxHealth/2);
        }
        ShowXpAndLevelAndJob(
                killer,
                "Warrior",
                JobsFile.GetPlayerLevel(killer,"Warrior"),
                JobsFile.GetPlayerXp(killer,"Warrior")
        );
    }
}
