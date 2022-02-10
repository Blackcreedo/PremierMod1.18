package fr.black.pm.block.custom;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class FireStoneBlock extends Block{

	public FireStoneBlock(Properties pProperties) {
		super(pProperties);
	}

	
	
	@SuppressWarnings("deprecation")
	@Override
	public InteractionResult use(BlockState pBlockState, Level pLevel, BlockPos pBlockPos, Player player,
			InteractionHand Hand, BlockHitResult Hit) {
		if(!pLevel.isClientSide()) {
			if(Hand == InteractionHand.MAIN_HAND) {
				player.sendMessage(new TextComponent("Fire Stone Block have been right clicked"), Util.NIL_UUID);
			}
		}

		return super.use(pBlockState, pLevel, pBlockPos, player, Hand, Hit);
	}
	
// Don't know what the overridden method is
//	@Override
//	public ItemStack getPickBlock(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
//		player.sendMessage(new TextComponent("Fire Stone Block have been middle clicked"), Util.NIL_UUID);
//		return super.getPickBlock(state, target, world, pos, player);
//	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void attack(BlockState pBlockState, Level pLevel, BlockPos pBlockPos, Player player) {
		if(!pLevel.isClientSide()) {
			player.sendMessage(new TextComponent("Fire Stone Block have been left clicked"), Util.NIL_UUID);
		}
		super.attack(pBlockState, pLevel, pBlockPos, player);
	}
	
	
	@Override
	public void stepOn(Level pLevel, BlockPos pBlockPos, BlockState pBlockState, Entity pEntity) {
		if(!pLevel.isClientSide()) {
			if(pEntity instanceof LivingEntity) {
				LivingEntity entity = ((LivingEntity) pEntity);
				entity.setSecondsOnFire(5);
			}
		}
		super.stepOn(pLevel, pBlockPos, pBlockState, pEntity);
	}
	
	
	
	
}

















