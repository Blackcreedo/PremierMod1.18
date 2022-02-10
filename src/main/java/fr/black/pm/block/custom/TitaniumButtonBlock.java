package fr.black.pm.block.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class TitaniumButtonBlock extends ButtonBlock {
	
	   public TitaniumButtonBlock(BlockBehaviour.Properties p_57060_) {
	      super(false, p_57060_);
	   }

	   protected SoundEvent getSound(boolean p_57062_) {
	      return p_57062_ ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF;
	   }
	}
