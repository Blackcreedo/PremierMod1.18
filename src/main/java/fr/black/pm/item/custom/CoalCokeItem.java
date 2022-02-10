package fr.black.pm.item.custom;

import javax.annotation.Nullable;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class CoalCokeItem extends Item{

	public CoalCokeItem(Properties pProperties) {
		super(pProperties);
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
		return 2000;
	}
	
}
