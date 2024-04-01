package io.github.toberocat.improvedfactions.modules.wilderness

import io.github.toberocat.improvedfactions.ImprovedFactionsPlugin
import io.github.toberocat.improvedfactions.modules.base.BaseModule
import io.github.toberocat.improvedfactions.modules.wilderness.config.WildernessModuleConfig
import io.github.toberocat.toberocore.command.CommandExecutor

class WildernessModule : BaseModule {
    override val moduleName = MODULE_NAME

    val wildernessConfig = WildernessModuleConfig()
    override fun onEnable(plugin: ImprovedFactionsPlugin) {

    }

    override fun reloadConfig(plugin: ImprovedFactionsPlugin) {
        wildernessConfig.reload(plugin.config)
    }

    override fun addCommands(plugin: ImprovedFactionsPlugin, executor: CommandExecutor) {
    }


    companion object {
        const val MODULE_NAME = "wilderness"
        fun wildernessModule() =
            (ImprovedFactionsPlugin.modules[MODULE_NAME] as? WildernessModule) ?: throw IllegalStateException()

        fun dynmapPair() = MODULE_NAME to WildernessModule()
    }
}