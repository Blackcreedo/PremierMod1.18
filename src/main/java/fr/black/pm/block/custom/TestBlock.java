package fr.black.pm.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class TestBlock extends Block {
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");

    public TestBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CLICKED, false));
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
                                 InteractionHand interactionHand, BlockHitResult result) {
        if(!level.isClientSide()){
            if(interactionHand == InteractionHand.MAIN_HAND){
                boolean currentState = blockState.getValue(CLICKED);
                level.setBlock(blockPos, blockState.setValue(CLICKED, !currentState), 3);
                this.registerDefaultState((this.defaultBlockState().setValue(CLICKED, !currentState)));
            }

        }

        return super.use(blockState, level, blockPos, player, interactionHand, result);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLICKED);
    }
}
