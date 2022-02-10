package fr.black.pm.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ModTiers {

    public static final ForgeTier TITANIUM = new ForgeTier(2, 500, 7.0F, 2.0F, 20,
            Tags.Blocks.NEEDS_GOLD_TOOL,() -> Ingredient.of(ModItems.TITANIUM_INGOT.get()));

    public static final ForgeTier RUBY = new ForgeTier(3, 200, 9.0F, 3.0F, 20,
            Tags.Blocks.NEEDS_GOLD_TOOL,() -> Ingredient.of(ModItems.RUBY.get()));
}
