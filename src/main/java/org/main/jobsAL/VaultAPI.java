package org.main.jobsAL;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.getServer;

public class VaultAPI {
    private static Economy econ;

    public static boolean setupEconomy(JavaPlugin plugin) {

        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp =
                plugin.getServer().getServicesManager().getRegistration(Economy.class);

        if (rsp == null) return false;

        econ = rsp.getProvider();
        return econ != null;
    }
    public static double getPlayerMoney(Player player){
        return econ.getBalance(player);
    }
    public static void addPlayerMoney(Player player,double money){
        econ.depositPlayer(player,money);
        player.sendMessage("§aAdded §6"+money+" "+econ.currencyNamePlural()+"§ato your account!");
    }
    public static void removePlayerMoney(Player player,double money){
        econ.withdrawPlayer(player,money);
        player.sendMessage("§Removed §6"+money+" "+econ.currencyNamePlural()+"§afrom your account!");
    }
}
