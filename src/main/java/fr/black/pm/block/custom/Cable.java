package fr.black.pm.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class Cable extends Block {

    //public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    //private static final VoxelShape SHAPE_UNCONNECT = Shapes.box(0.2,0.2,0.2,0.8,0.8,0.8);
    private static final VoxelShape SHAPE_DOWN_UP = Shapes.box(0.3,0,0.3,0.7,1,0.7);
    private static final VoxelShape SHAPE_NORTH_SOUTH = Shapes.box(0.3,0.3,0,0.7,0.7,1);
    private static final VoxelShape SHAPE_EAST_WEST = Shapes.box(0,0.3,0.3,1,0.7,0.7);

    public Cable(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(BlockStateProperties.FACING)){
            case DOWN, UP -> SHAPE_DOWN_UP;
            case EAST, WEST -> SHAPE_EAST_WEST;
            case NORTH, SOUTH -> SHAPE_NORTH_SOUTH;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(BlockStateProperties.FACING, context.getNearestLookingDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }

}
