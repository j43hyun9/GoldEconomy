package com.j43hyun9.goldeconomy1.command;

import com.j43hyun9.goldeconomy1.hooks.VaultHooks;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class EconomyCommand implements CommandExecutor {

    Player testmoneyplayer;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        // check
        if(commandSender instanceof Player)
            return false;

        Player player = (Player) commandSender; Player testmoneyplayer = player;
        EcoCommands cmd = EcoCommands.valueOf(strings[0].toUpperCase(Locale.ENGLISH));
        Economy econ    = VaultHooks.getEconomy();

        switch(cmd) {
            case TEST: {
                testMoney();
                break;
            }
            case SEND:
                //player.sendMoney();
        }
        return false;
    }

    private enum EcoCommands {
        SEND, TEST
    }

    private boolean testMoney() {
        Economy econ    = VaultHooks.getEconomy();
        EconomyResponse er = econ.depositPlayer((OfflinePlayer) testmoneyplayer, 1.05);
        if(er.transactionSuccess()) {
            testmoneyplayer.sendMessage(String.format("You were given %s and now have %s", econ.format(er.amount), econ.format(er.balance)));
        } else {
            testmoneyplayer.sendMessage(String.format("An error occured: %s", er.errorMessage));
        }

        return false;
    }
}
