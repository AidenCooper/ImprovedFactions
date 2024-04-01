package io.github.toberocat.improvedfactions.modules.power.commands

import io.github.toberocat.improvedfactions.ImprovedFactionsPlugin
import io.github.toberocat.improvedfactions.factions.Faction
import io.github.toberocat.improvedfactions.factions.FactionHandler
import io.github.toberocat.improvedfactions.factions.PowerAccumulationChangeReason
import io.github.toberocat.improvedfactions.modules.power.impl.FactionPowerRaidModuleHandleImpl
import io.github.toberocat.improvedfactions.permissions.Permissions
import io.github.toberocat.improvedfactions.utils.arguments.EnumArgument
import io.github.toberocat.improvedfactions.utils.arguments.IntegerArgument
import io.github.toberocat.improvedfactions.utils.arguments.PowerArgument
import io.github.toberocat.improvedfactions.utils.arguments.entity.FactionArgument
import io.github.toberocat.improvedfactions.utils.command.CommandCategory
import io.github.toberocat.improvedfactions.utils.command.CommandMeta
import io.github.toberocat.improvedfactions.utils.options.FactionPermissionOption
import io.github.toberocat.improvedfactions.utils.options.InFactionOption
import io.github.toberocat.improvedfactions.utils.options.addFactionNameOption
import io.github.toberocat.toberocore.command.SubCommand
import io.github.toberocat.toberocore.command.arguments.Argument
import io.github.toberocat.toberocore.command.exceptions.CommandException
import io.github.toberocat.toberocore.command.options.Options
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.transactions.transaction

@CommandMeta(
    description = "base.command.power.set.description",
    category = CommandCategory.ADMIN_CATEGORY
)
class PowerSetCommand(
    private val plugin: ImprovedFactionsPlugin
) : SubCommand("set") {
    override fun options() = Options.getFromConfig(plugin, label) { options, _ ->
        options.addFactionNameOption(2)
    }
    override fun arguments() = arrayOf<Argument<*>>(
        EnumArgument(PowerType::class.java, "base.command.args.power-type"),
        PowerArgument(),
        FactionArgument(),
    )

    override fun handleCommand(sender: CommandSender, args: Array<String>): Boolean {
        val faction: Faction
        val power: Int
        val powerType: PowerType
        if (sender is Player) {
            val arguments = parseArgs(sender, args)
            powerType = arguments.get<PowerType>(0) ?: return false
            power = arguments.get<Int>(1) ?: return false
            faction = arguments.get<Faction>(2) ?: return false
        } else {
            if (args.size < 3) {
                throw CommandException("base.exceptions.arg-doesnt-match", emptyMap())
            }
            powerType = PowerType.valueOf(args[0].uppercase())
            power = args[1].toIntOrNull() ?: throw CommandException("base.exceptions.arg-doesnt-match", emptyMap())
            faction = FactionHandler.getFaction(args.drop(2).joinToString(separator = " ")) ?: throw CommandException("base.exceptions.arg-doesnt-match", emptyMap())
        }

        transaction {
            when (powerType) {
                PowerType.ACCUMULATED -> faction.setAccumulatedPower(power, PowerAccumulationChangeReason.OTHER)
                PowerType.MAXIMUM -> faction.setMaxPower(power)
            }
        }

        return true
    }
}

enum class PowerType {
    ACCUMULATED,
    MAXIMUM
}