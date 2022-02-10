package fr.black.pm.util;

import fr.black.pm.PremierMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class PremierModTags {


	public static class Blocks{
		

		public static final Tags.IOptionalNamedTag<Block> FIRESTONE_CLICKABLE_BLOCKS =
				createTag("firestone_clickable_blocks");
		
		private static Tags.IOptionalNamedTag<Block> createTag(String name){
			return BlockTags.createOptional(new ResourceLocation(PremierMod.MOD_ID, name));
		}
		
		@SuppressWarnings("unused")
		private static Tags.IOptionalNamedTag<Block> createForgeTag(String name){
			return BlockTags.createOptional(new ResourceLocation("forge", name));
		}
		
	}
	
	public static class Items{
		
		public static final Tags.IOptionalNamedTag<Item> TITANIUM_INGOTS = createForgeTag("ingots/titanium");
		public static final Tags.IOptionalNamedTag<Item> RUBY = createForgeTag("gems/ruby");
		
		
		@SuppressWarnings("unused")
		private static Tags.IOptionalNamedTag<Item> createTag(String name){
			return ItemTags.createOptional(new ResourceLocation(PremierMod.MOD_ID, name));
		}

		private static Tags.IOptionalNamedTag<Item> createForgeTag(String name){
			return ItemTags.createOptional(new ResourceLocation("forge", name));
		}
		
	}
	
	
}