package fr.black.pm.item.custom;



import fr.black.pm.util.PremierModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class Firestone extends Item {

	public Firestone(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (!context.getLevel().isClientSide()) {
			Level level = context.getLevel();
			BlockPos positionClicked = context.getClickedPos();
			Block blockClicked = level.getBlockState(positionClicked).getBlock();
			Player player = context.getPlayer();
			boolean isPlayerOnFire = player.isOnFire();

			if (Math.random() > 0.2) {
				if (canFirestone(blockClicked) && !isPlayerOnFire) {
					// give effect to player and destroy block
					gainEffectAndDestroyBlock(player, 200, positionClicked, level);
				} else {
					// set the block on fire
					setBlockOnFire(context);
				}
			} else {
				// light the player on fire
				setPlayerOnFire(player, 7);
			}
			context.getItemInHand().hurtAndBreak(1, context.getPlayer(), p -> {
				p.broadcastBreakEvent(context.getHand());
			});
		}
		return super.useOn(context);
	}

	private static void gainEffectAndDestroyBlock(Player player, int time, BlockPos position, Level level) {
		player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, time));
		level.destroyBlock(position, false);
	}

	private static void setBlockOnFire(UseOnContext context) {
		Level level = context.getLevel();
		Player player = context.getPlayer();
		BlockPos position = context.getClickedPos();
	    BlockPos blockpos1 = position.relative(context.getClickedFace());
        if (BaseFireBlock.canBePlacedAt(level, blockpos1, context.getHorizontalDirection())) {
           level.playSound(player, blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
           BlockState blockstate1 = BaseFireBlock.getState(level, blockpos1);
           level.setBlock(blockpos1, blockstate1, 11);
           level.gameEvent(player, GameEvent.BLOCK_PLACE, position);
        }
	}

	private static void setPlayerOnFire(Player player, int time) {
		player.setSecondsOnFire(time);
	}

	private boolean canFirestone(Block block) {
		return PremierModTags.Blocks.FIRESTONE_CLICKABLE_BLOCKS.contains(block);

	}

}
