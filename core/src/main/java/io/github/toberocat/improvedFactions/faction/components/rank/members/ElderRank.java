package io.github.toberocat.improvedFactions.faction.components.rank.members;

import io.github.toberocat.core.utility.Utility;
import io.github.toberocat.core.utility.language.Language;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ElderRank extends FactionRank {

    public static final String registry = "Elder";

    public ElderRank(int priority) {
        super("faction.ranks.elder", registry, priority, false);
    }

    @Override
    public String description(Player player) {
        return null;
    }

    @Override
    public ItemStack getItem(Player player) {
        return Utility.getSkull("https://textures.minecraft.net/texture/845a1ff7aefa1fdc8db690620d02fc6005fdbe81b823e1b7fee9115de073b435", 1,
                Language.getMessage("rank.elder.title", player),
                Language.getLore("rank.elder.lore", player));
    }
}
