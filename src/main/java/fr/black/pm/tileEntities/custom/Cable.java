package fr.black.pm.tileEntities.custom;

import fr.black.pm.tileEntities.ModTileEntities;
import fr.black.pm.util.VoxelShapeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class Cable extends Block implements EntityBlock {

    //public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty EAST_CONNECTION = BooleanProperty.create("east_connection");
    public static final BooleanProperty WEST_CONNECTION = BooleanProperty.create("west_connection");
    public static final BooleanProperty NORTH_CONNECTION = BooleanProperty.create("north_connection");
    public static final BooleanProperty SOUTH_CONNECTION = BooleanProperty.create("south_connection");
    public static final BooleanProperty UP_CONNECTION = BooleanProperty.create("up_connection");
    public static final BooleanProperty DOWN_CONNECTION = BooleanProperty.create("down_connection");

    private static final VoxelShape SHAPE_CENTER = Shapes.box(0.25,0.25,0.25,0.75,0.75,0.75);
    private static final VoxelShape SHAPE_DOWN = Shapes.box(0.31,0,0.31,0.69,0.69,0.69);
    private static final VoxelShape SHAPE_NORTH = Shapes.box(0.31,0.31,0,0.69,0.69,0.69);
    private static final VoxelShape SHAPE_WEST = Shapes.box(0,0.31,0.31,0.69,0.69,0.69);
    private static final VoxelShape SHAPE_UP = Shapes.box(0.31,0.31,0.31,0.69,1,0.69);
    private static final VoxelShape SHAPE_SOUTH = Shapes.box(0.31,0.31,0.31,0.69,0.69,1);
    private static final VoxelShape SHAPE_EAST = Shapes.box(0.31,0.31,0.31,1,0.69,0.69);

    public Cable(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.defaultBlockState().setValue(UP_CONNECTION, Boolean.valueOf(false)).setValue(DOWN_CONNECTION, Boolean.valueOf(false)).setValue(NORTH_CONNECTION, Boolean.valueOf(false)).setValue(SOUTH_CONNECTION, Boolean.valueOf(false)).setValue(EAST_CONNECTION, Boolean.valueOf(false)).setValue(WEST_CONNECTION, Boolean.valueOf(false)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        List<VoxelShape> shapes = new ArrayList<>();
        boolean east_connection = state.getValue(EAST_CONNECTION);
        boolean west_connection = state.getValue(WEST_CONNECTION);
        boolean north_connection = state.getValue(NORTH_CONNECTION);
        boolean south_connection = state.getValue(SOUTH_CONNECTION);
        boolean up_connection = state.getValue(UP_CONNECTION);
        boolean down_connection = state.getValue(DOWN_CONNECTION);

        // If a cable is connected we need to add the related part to the list
        if(east_connection)  {shapes.add(SHAPE_EAST);}
        if(west_connection)  {shapes.add(SHAPE_WEST);}
        if(north_connection) {shapes.add(SHAPE_NORTH);}
        if(south_connection) {shapes.add(SHAPE_SOUTH);}
        if(up_connection)    {shapes.add(SHAPE_UP);}
        if(down_connection)  {shapes.add(SHAPE_DOWN);}
        // If there is an angle we need to add the center part to the list
        if(((up_connection || down_connection) && (north_connection || south_connection || east_connection || west_connection)) || (north_connection || south_connection) && (east_connection || west_connection)){shapes.add(SHAPE_CENTER);}
        // If there is no connection, we return the center part
        if(shapes.size() == 0){return SHAPE_CENTER;}
        // If the is only one connection, we need to the opposite connection part to the list
        if(shapes.size() == 1){
            if(east_connection)  {shapes.add(SHAPE_WEST);}
            if(west_connection)  {shapes.add(SHAPE_EAST);}
            if(north_connection) {shapes.add(SHAPE_SOUTH);}
            if(south_connection) {shapes.add(SHAPE_NORTH);}
            if(up_connection)    {shapes.add(SHAPE_DOWN);}
            if(down_connection)  {shapes.add(SHAPE_UP);}
        }
        // Return the combine shapes
        VoxelShape shape = VoxelShapeUtils.combine(shapes);
        return shape;
    }

    @Override
    public BlockState updateShape(BlockState state1, Direction direction, BlockState state2, LevelAccessor accessor, BlockPos blockPos, BlockPos neighbor) {
        if(!accessor.isClientSide()){
            // Waterlog
        /*if (state1.getValue(WATERLOGGED)) {
            accessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(accessor));
        }*/

            boolean needConnect = accessor.getBlockEntity(neighbor) != null || accessor.getBlockState(neighbor).getBlock() == ModTileEntities.CABLE.get();
            if(direction == Direction.UP){accessor.setBlock(blockPos, state1.setValue(UP_CONNECTION, needConnect), Block.UPDATE_ALL);}
            if(direction == Direction.DOWN){accessor.setBlock(blockPos, state1.setValue(DOWN_CONNECTION, needConnect), Block.UPDATE_ALL);}
            if(direction == Direction.NORTH){accessor.setBlock(blockPos, state1.setValue(NORTH_CONNECTION, needConnect), Block.UPDATE_ALL);}
            if(direction == Direction.SOUTH){accessor.setBlock(blockPos, state1.setValue(SOUTH_CONNECTION, needConnect), Block.UPDATE_ALL);}
            if(direction == Direction.EAST){accessor.setBlock(blockPos, state1.setValue(EAST_CONNECTION, needConnect), Block.UPDATE_ALL);}
            if(direction == Direction.WEST){accessor.setBlock(blockPos, state1.setValue(WEST_CONNECTION, needConnect), Block.UPDATE_ALL);}
        }
        return super.updateShape(state1, direction, state2, accessor, blockPos, neighbor);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        return this.defaultBlockState().setValue(EAST_CONNECTION, level.getBlockState(context.getClickedPos().east()).getBlock() == ModTileEntities.CABLE.get() || level.getBlockEntity(context.getClickedPos().east()) != null)
                .setValue(WEST_CONNECTION, level.getBlockState(context.getClickedPos().west()).getBlock() == ModTileEntities.CABLE.get() || level.getBlockEntity(context.getClickedPos().west()) != null)
                .setValue(NORTH_CONNECTION, level.getBlockState(context.getClickedPos().north()).getBlock() == ModTileEntities.CABLE.get() || level.getBlockEntity(context.getClickedPos().north()) != null)
                .setValue(SOUTH_CONNECTION, level.getBlockState(context.getClickedPos().south()).getBlock() == ModTileEntities.CABLE.get() || level.getBlockEntity(context.getClickedPos().south()) != null)
                .setValue(UP_CONNECTION, level.getBlockState(context.getClickedPos().above()).getBlock() == ModTileEntities.CABLE.get() || level.getBlockEntity(context.getClickedPos().above()) != null)
                .setValue(DOWN_CONNECTION, level.getBlockState(context.getClickedPos().below()).getBlock() == ModTileEntities.CABLE.get() || level.getBlockEntity(context.getClickedPos().below()) != null);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(EAST_CONNECTION);
        builder.add(WEST_CONNECTION);
        builder.add(NORTH_CONNECTION);
        builder.add(SOUTH_CONNECTION);
        builder.add(UP_CONNECTION);
        builder.add(DOWN_CONNECTION);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return null;
    }
}
