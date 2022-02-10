package fr.black.pm.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SpeedyBlock extends Block{

	public SpeedyBlock(Properties pProperties) {
		super(pProperties);
	}

/*	This completely changes in 1.18 need to rework
	@SuppressWarnings("deprecation")
	@Override
	public InteractionResult use(BlockState pBlockState, Level pLevel, BlockPos pBlockPos, Player pPlayer,
			InteractionHand pInteractionHand, BlockHitResult pBlockHitResult) {
		if (!pLevel.isClientSide()) {
			if (!pPlayer.isCrouching()) {
				MinecraftServer server = pLevel.getServer();

				if (server != null) {
					if (pLevel.dimension() == ModDimensions.FIRST_DIMENSION) {
						ServerLevel overWorld = server.getLevel(Level.OVERWORLD);
						if (overWorld != null) {
							pPlayer.changeDimension(overWorld, new Teleporter(pBlockPos, false));
						}
					} else {
						ServerLevel firstDimension = server.getLevel(ModDimensions.FIRST_DIMENSION);
						if (firstDimension != null) {
							pPlayer.changeDimension(firstDimension, new Teleporter(pBlockPos, true));
						}
					}
					return InteractionResult.SUCCESS;
				}
			}
		}
		return super.use(pBlockState, pLevel, pBlockPos, pPlayer, pInteractionHand, pBlockHitResult);
	}	
*/
	
	
	@Override
	public void stepOn(Level pLevel, BlockPos pBlockPos, BlockState pBlockState, Entity pEntity) {
		if(!pLevel.isClientSide()) {
			if(pEntity instanceof LivingEntity) {
				LivingEntity entity = ((LivingEntity) pEntity);
				entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, 20));
			}
		}
		
		super.stepOn(pLevel, pBlockPos, pBlockState, pEntity);
	}
	
	
	
}
