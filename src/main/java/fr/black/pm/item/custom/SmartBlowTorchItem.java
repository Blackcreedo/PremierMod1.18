package fr.black.pm.item.custom;


import java.util.List;


import com.google.common.collect.ImmutableMap;

import fr.black.pm.block.ModBlocks;
import fr.black.pm.item.ModItems;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;

public class SmartBlowTorchItem extends Item {
	
	private static class ItemNumber {
		
		private Item item;
		private Integer count;
		
		public Item getItem() {
			return item;
		}
		public Integer getCount() {
			return count;
		}
		public ItemNumber(Item item, Integer count) {
			this.item = item;
			this.count = count;
		}	
	}
	
	
	private static final ImmutableMap<Block, ItemNumber> BLOW_TORCH_ITEM_CRAFT = 
			new ImmutableMap.Builder<Block, ItemNumber>()
					.put(ModBlocks.TITANIUM_BLOCK.get(), new ItemNumber(ModItems.TITANIUM_INGOT.get(), 9))
					.put(ModBlocks.TITANIUM_ORE.get(), new ItemNumber(ModItems.TITANIUM_INGOT.get(), 4))
					.put(ModBlocks.RUBY_BLOCK.get(), new ItemNumber(ModItems.RUBY.get(), 9))
					.put(ModBlocks.RUBY_ORE.get(), new ItemNumber(ModItems.RUBY.get(), 4))
					.put(Blocks.DEEPSLATE_IRON_ORE, new ItemNumber(Items.IRON_INGOT, 4))
					.put(Blocks.IRON_ORE, new ItemNumber(Items.IRON_INGOT, 4))
					.put(Blocks.DEEPSLATE_GOLD_ORE, new ItemNumber(Items.GOLD_INGOT, 4))
					.put(Blocks.GOLD_ORE, new ItemNumber(Items.GOLD_INGOT, 4))
					.put(Blocks.DEEPSLATE_COPPER_ORE, new ItemNumber(Items.COPPER_INGOT, 9))
					.put(Blocks.COPPER_ORE, new ItemNumber(Items.COPPER_INGOT, 9))
					.put(Blocks.SAND, new ItemNumber(Blocks.GLASS.asItem(), 1))
					.build();
	
		
    public SmartBlowTorchItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            Level level = pContext.getLevel();
            BlockPos positionClicked = pContext.getClickedPos();
            Block blockClicked = level.getBlockState(positionClicked).getBlock();
			ItemStack stack = pContext.getItemInHand();

			if(blockClicked == Blocks.MAGMA_BLOCK){
				if(pContext.getHand() == InteractionHand.MAIN_HAND){
					CompoundTag tag = new CompoundTag();

					level.destroyBlock(positionClicked, false);

					if(stack.hasTag()){
						increaseCharge(stack);
					} else {
						tag.putInt("charge", 5);
						stack.setTag(tag);
					}

					return InteractionResult.SUCCESS;
				}
			} else {
				if(canBlowTorch(blockClicked)) {
					if(stack.hasTag() && stack.getTag().getInt("charge") > 0){
						ItemEntity entityItem = new ItemEntity(level,
								positionClicked.getX(), positionClicked.getY(), positionClicked.getZ(),
								new ItemStack(BLOW_TORCH_ITEM_CRAFT.get(blockClicked).getItem(), BLOW_TORCH_ITEM_CRAFT.get(blockClicked).getCount()));

						level.destroyBlock(positionClicked, false);
						level.addFreshEntity(entityItem);
						decreaseCharge(stack);
						pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), p -> {
							p.broadcastBreakEvent(pContext.getHand());
						});
					} else {
						pContext.getPlayer().sendMessage(new TextComponent("Not enough charge left!"),
								Util.NIL_UUID);
					}
				} else {
					pContext.getPlayer().sendMessage(new TextComponent("Cannot blow torch this Block!"),
							Util.NIL_UUID);
				}
			}
        }

        return InteractionResult.SUCCESS;
    }

    private boolean canBlowTorch(Block block) {
        return BLOW_TORCH_ITEM_CRAFT.containsKey(block);
    }

	private void increaseCharge(ItemStack stack){
		stack.getTag().putInt("charge", stack.getTag().getInt("charge") + 5);
	}

	private void decreaseCharge(ItemStack stack){
		stack.getTag().putInt("charge", stack.getTag().getInt("charge") - 1);
	}
    
    
    @Override
	public void appendHoverText(ItemStack pItemStack, Level pLevel, List<Component> tooltip, TooltipFlag Flag) {
		if(pItemStack.hasTag()){
			tooltip.add(new TextComponent("Charge " + pItemStack.getTag().getInt("charge")));
		}
		if(Screen.hasShiftDown()) {
			tooltip.add(new TranslatableComponent("tooltip.pm.smart_blow_torch_shift"));
		} else {
			tooltip.add(new TranslatableComponent("tooltip.pm.smart_blow_torch"));
		}
		super.appendHoverText(pItemStack, pLevel, tooltip, Flag);
	}
}
