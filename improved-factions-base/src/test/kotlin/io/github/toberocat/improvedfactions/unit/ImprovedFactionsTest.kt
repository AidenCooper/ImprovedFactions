package io.github.toberocat.improvedfactions.unit

import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import be.seeseemelk.mockbukkit.WorldMock
import dev.s7a.base64.Base64ItemStack
import io.github.toberocat.improvedfactions.ImprovedFactionsPlugin
import io.github.toberocat.improvedfactions.factions.Faction
import io.github.toberocat.improvedfactions.factions.FactionHandler
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*


open class ImprovedFactionsTest {
    protected lateinit var server: ServerMock
    protected lateinit var plugin: ImprovedFactionsPlugin

    @BeforeEach
    open fun setUp() {
        System.setProperty("bstats.relocatecheck", "false")
        server = MockBukkit.mock()
        plugin = MockBukkit.load(ImprovedFactionsPlugin::class.java)
    }

    @AfterEach
    fun tearDown() {
        MockBukkit.unmock()
    }


    fun testWorld() = WorldMock(Material.DIRT, 3)

    fun testFaction(owner: UUID = UUID.randomUUID()) = FactionHandler.createFaction(owner, "TestFaction")
}