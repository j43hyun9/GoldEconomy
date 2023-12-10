package com.j43hyun9.goldeconomy1.hooks;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class VaultHooks extends JavaPlugin {



    private static Economy econ = null;
/*
    private static Permission perms = null;
    private static Chat chat = null;
*/

    @Override
    public void onDisable() {
        getLogger().info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    @Override
    public void onEnable() {
        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Vault에 대한 Dependencies를 찾을 수 없습니다.", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    /*    setupPermissions();
        setupChat();

     */
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

  /*  public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }
   */
}
