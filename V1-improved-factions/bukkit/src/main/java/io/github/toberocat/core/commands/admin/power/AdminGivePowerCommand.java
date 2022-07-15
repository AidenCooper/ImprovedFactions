package io.github.toberocat.core.commands.admin.power;

import io.github.toberocat.core.factions.local.LocalFaction;
import io.github.toberocat.core.factions.local.FactionUtility;
import io.github.toberocat.core.utility.Utility;
import io.github.toberocat.core.utility.command.SubCommand;
import io.github.toberocat.core.utility.command.SubCommandSettings;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

public class AdminGivePowerCommand extends SubCommand {
    public AdminGivePowerCommand() {
        super("give", "admin.power.add", "command.admin.power.add.description", false);
    }

    @Override
    public SubCommandSettings getSettings() {
        return super.getSettings().setArgLength(2);
    }

    @Override
    protected void CommandExecute(Player player, String[] args) {
        if (!Utility.isNumber(args[1])) return;
        int amount = Integer.parseInt(args[1]);

        LocalFaction faction = FactionUtility.getFactionByRegistry(args[0]);

        faction.getPowerManager().setCurrentPower(faction.getPowerManager().getCurrentPower() + amount);
    }

    @Override
    protected List<String> CommandTab(Player player, String[] args) {
        LinkedList<String> tab = new LinkedList<>();
        if (args.length <= 1) tab.addAll(FactionUtility.getAllFactions());
        else tab.add("<amount>");

        return tab;
    }
}