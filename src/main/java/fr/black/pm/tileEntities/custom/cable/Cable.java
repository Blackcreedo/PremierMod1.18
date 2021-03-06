package fr.black.pm.tileEntities.custom.cable;

import fr.black.pm.energy.CustomEnergyStorage;
import fr.black.pm.tileEntities.ModTileEntities;
import fr.black.pm.util.VoxelShapeUtils;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class Cable extends Block implements EntityBlock {

    public static final String SCREEN_CABLE = "screen.cable";

    //public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty EAST_CONNECTION = BooleanProperty.create("east_connection");
    public static final BooleanProperty WEST_CONNECTION = BooleanProperty.create("west_connection");
    public static final BooleanProperty NORTH_CONNECTION = BooleanProperty.create("north_connection");
    public static final BooleanProperty SOUTH_CONNECTION = BooleanProperty.create("south_connection");
    public static final BooleanProperty UP_CONNECTION = BooleanProperty.create("up_connection");
    public static final BooleanProperty DOWN_CONNECTION = BooleanProperty.create("down_connection");
    public static final BooleanProperty CENTER = BooleanProperty.create("center");


    private static final VoxelShape SHAPE_CENTER = Shapes.box(0.25,0.25,0.25,0.75,0.75,0.75);
    private static final VoxelShape SHAPE_DOWN = Shapes.box(0.31,0,0.31,0.69,0.5,0.69);
    private static final VoxelShape SHAPE_NORTH = Shapes.box(0.31,0.31,0,0.69,0.69,0.5);
    private static final VoxelShape SHAPE_WEST = Shapes.box(0,0.31,0.31,0.5,0.69,0.69);
    private static final VoxelShape SHAPE_UP = Shapes.box(0.31,0.5,0.31,0.69,1,0.69);
    private static final VoxelShape SHAPE_SOUTH = Shapes.box(0.31,0.31,0.5,0.69,0.69,1);
    private static final VoxelShape SHAPE_EAST = Shapes.box(0.5,0.31,0.31,1,0.69,0.69);

    public Cable(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.defaultBlockState().setValue(CENTER, Boolean.valueOf(true)).setValue(UP_CONNECTION, Boolean.valueOf(false)).setValue(DOWN_CONNECTION, Boolean.valueOf(false)).setValue(NORTH_CONNECTION, Boolean.valueOf(false)).setValue(SOUTH_CONNECTION, Boolean.valueOf(false)).setValue(EAST_CONNECTION, Boolean.valueOf(false)).setValue(WEST_CONNECTION, Boolean.valueOf(false)));
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
        // If the is only one connection, we need to add the center part to the list
        if(shapes.size() == 1){
            shapes.add(SHAPE_CENTER);
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
            boolean east_connection = state1.getValue(EAST_CONNECTION);
            boolean west_connection = state1.getValue(WEST_CONNECTION);
            boolean north_connection = state1.getValue(NORTH_CONNECTION);
            boolean south_connection = state1.getValue(SOUTH_CONNECTION);
            boolean up_connection = state1.getValue(UP_CONNECTION);
            boolean down_connection = state1.getValue(DOWN_CONNECTION);

            boolean needConnect = accessor.getBlockEntity(neighbor) != null || accessor.getBlockState(neighbor).getBlock() == ModTileEntities.CABLE.get();
            if(direction == Direction.UP){
                up_connection = needConnect;
                boolean updateCenter = needCenter(up_connection, down_connection, north_connection, south_connection, east_connection, west_connection);
                accessor.setBlock(blockPos, state1.setValue(UP_CONNECTION, needConnect).setValue(CENTER, updateCenter), Block.UPDATE_ALL);
            }
            if(direction == Direction.DOWN){
                down_connection = needConnect;
                boolean updateCenter = needCenter(up_connection, down_connection, north_connection, south_connection, east_connection, west_connection);
                accessor.setBlock(blockPos, state1.setValue(DOWN_CONNECTION, needConnect).setValue(CENTER, updateCenter), Block.UPDATE_ALL);
            }
            if(direction == Direction.NORTH){
                north_connection = needConnect;
                boolean updateCenter = needCenter(up_connection, down_connection, north_connection, south_connection, east_connection, west_connection);
                accessor.setBlock(blockPos, state1.setValue(NORTH_CONNECTION, needConnect).setValue(CENTER, updateCenter), Block.UPDATE_ALL);
            }
            if(direction == Direction.SOUTH){
                south_connection = needConnect;
                boolean updateCenter = needCenter(up_connection, down_connection, north_connection, south_connection, east_connection, west_connection);
                accessor.setBlock(blockPos, state1.setValue(SOUTH_CONNECTION, needConnect).setValue(CENTER, updateCenter), Block.UPDATE_ALL);
            }
            if(direction == Direction.EAST){
                east_connection = needConnect;
                boolean updateCenter = needCenter(up_connection, down_connection, north_connection, south_connection, east_connection, west_connection);
                accessor.setBlock(blockPos, state1.setValue(EAST_CONNECTION, needConnect).setValue(CENTER, updateCenter), Block.UPDATE_ALL);
            }
            if(direction == Direction.WEST){
                west_connection = needConnect;
                boolean updateCenter = needCenter(up_connection, down_connection, north_connection, south_connection, east_connection, west_connection);
                accessor.setBlock(blockPos, state1.setValue(WEST_CONNECTION, needConnect).setValue(CENTER, updateCenter), Block.UPDATE_ALL);
            }
        }
        return super.updateShape(state1, direction, state2, accessor, blockPos, neighbor);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        boolean east_connection = level.getBlockState(context.getClickedPos().east()).getBlock() == ModTileEntities.CABLE.get() || level.getBlockEntity(context.getClickedPos().east()) != null;
        boolean west_connection = level.getBlockState(context.getClickedPos().west()).getBlock() == ModTileEntities.CABLE.get() || level.getBlockEntity(context.getClickedPos().west()) != null;
        boolean north_connection = level.getBlockState(context.getClickedPos().north()).getBlock() == ModTileEntities.CABLE.get() || level.getBlockEntity(context.getClickedPos().north()) != null;
        boolean south_connection = level.getBlockState(context.getClickedPos().south()).getBlock() == ModTileEntities.CABLE.get() || level.getBlockEntity(context.getClickedPos().south()) != null;
        boolean up_connection = level.getBlockState(context.getClickedPos().above()).getBlock() == ModTileEntities.CABLE.get() || level.getBlockEntity(context.getClickedPos().above()) != null;
        boolean down_connection = level.getBlockState(context.getClickedPos().below()).getBlock() == ModTileEntities.CABLE.get() || level.getBlockEntity(context.getClickedPos().below()) != null;


        return this.defaultBlockState().setValue(EAST_CONNECTION, level.getBlockState(context.getClickedPos().east()).getBlock() == ModTileEntities.CABLE.get() || level.getBlockEntity(context.getClickedPos().east()) != null)
                .setValue(WEST_CONNECTION, west_connection)
                .setValue(NORTH_CONNECTION, north_connection)
                .setValue(SOUTH_CONNECTION, south_connection)
                .setValue(UP_CONNECTION, up_connection)
                .setValue(DOWN_CONNECTION, down_connection)
                .setValue(CENTER, needCenter(up_connection, down_connection, north_connection, south_connection, east_connection, west_connection));
    }

    private boolean needCenter(boolean up_connection, boolean down_connection, boolean north_connection, boolean south_connection, boolean east_connection, boolean west_connection){
        int count = 0;
        if(up_connection){count++;}
        if(down_connection){count++;}
        if(north_connection){count++;}
        if(south_connection){count++;}
        if(east_connection){count++;}
        if(west_connection){count++;}
        return (((up_connection || down_connection) && (north_connection || south_connection || east_connection || west_connection)) || (north_connection || south_connection) && (east_connection || west_connection))
                || count<=1;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(EAST_CONNECTION);
        builder.add(WEST_CONNECTION);
        builder.add(NORTH_CONNECTION);
        builder.add(SOUTH_CONNECTION);
        builder.add(UP_CONNECTION);
        builder.add(DOWN_CONNECTION);
        builder.add(CENTER);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CableBlockEntity(pos, state);
    }


    @javax.annotation.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> entityType) {
        if(!level.isClientSide()){
            return (lvl, pos, state, t) -> {
                if (t instanceof CableBlockEntity tile){
                    //System.out.println(tile.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0));
                    tile.tickServer();
                }
            };
        }
        return null;
    }


    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof CableBlockEntity) {
                MenuProvider containerProvider = new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return new TranslatableComponent(SCREEN_CABLE);
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
                        return new CableContainer(windowId, pos, playerInventory, playerEntity);
                    }
                };
                NetworkHooks.openGui((ServerPlayer) player, containerProvider, be.getBlockPos());
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }
}
