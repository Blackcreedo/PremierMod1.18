package fr.black.pm.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {

	public static final CreativeModeTab PREMIER_MOD_TAB = new CreativeModeTab("premierModTab") {
		
		@Override
		public ItemStack makeIcon() {
			// TODO Auto-generated method stub
			return new ItemStack(ModItems.TITANIUM_INGOT.get());
		}
	};

	public static final CreativeModeTab CURSES_TAB = new CreativeModeTab("curses") {

		@Override
		public ItemStack makeIcon() {
			// TODO Auto-generated method stub
			return new ItemStack(ModItems.LIGHTNING_STRIKE_CURSE1.get());
		}
	};
			
}
