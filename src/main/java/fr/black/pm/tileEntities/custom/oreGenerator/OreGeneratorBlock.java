package fr.black.pm.tileEntities.custom.oreGenerator;

import fr.black.pm.tileEntities.custom.powergen.PowergenBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OreGeneratorBlock extends Block implements EntityBlock {
    public static final String MESSAGE_OREGEN = "message.oregen";

    private static final VoxelShape SHAPE_DOWN = Shapes.box(0,0.2,0,1,1,1);
    private static final VoxelShape SHAPE_UP = Shapes.box(0,0,0,1,0.8,1);
    private static final VoxelShape SHAPE_NORTH = Shapes.box(0,0,0.2,1,1,1);
    private static final VoxelShape SHAPE_SOUTH = Shapes.box(0,0,0,1,1,0.8);
    private static final VoxelShape SHAPE_EAST = Shapes.box(0,0,0,0.8,1,1);
    private static final VoxelShape SHAPE_WEST = Shapes.box(0.2,0,0,1,1,1);

    public OreGeneratorBlock() {
        super(Properties.of(Material.METAL).sound(SoundType.METAL).strength(2f).noOcclusion());
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable BlockGetter reader, List<Component> list, TooltipFlag flags) {
        list.add(new TranslatableComponent(MESSAGE_OREGEN).withStyle(ChatFormatting.BLUE));
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter getter, BlockPos pos) {
        return switch (state.getValue(BlockStateProperties.FACING)){
            case DOWN -> SHAPE_DOWN;
            case UP -> SHAPE_UP;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
        };
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide()) {
            return (lvl, pos, stt, te) -> {
                if (te instanceof OreGeneratorBlockEntity generator) generator.tickServer();
            };
        }
        return null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new OreGeneratorBlockEntity(blockPos, blockState);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(level.isClientSide()){
            BlockEntity be = level.getBlockEntity(pos);
            if(be instanceof OreGeneratorBlockEntity){
                OreGeneratorBlockEntity generator = ((OreGeneratorBlockEntity) be);
                Direction direction = result.getDirection();
                Direction facing = state.getValue(BlockStateProperties.FACING);
                if(direction == facing){
                    Vec3 hit = result.getLocation().subtract(pos.getX(), pos.getY(), pos.getZ());
                    // get 2D location
                    double x = getXFromHit(facing, hit);
                    double y = getYFromHit(facing, hit);
                    if(x<0.5){
                        // put ore
                        player.sendMessage(new TextComponent("Put Ore"), Util.NIL_UUID);
                        generator.setCollecting(!generator.isCollecting());
                    }
                    else if(y < 0.5){
                        // start generating
                        player.sendMessage(new TextComponent("start generating"), Util.NIL_UUID);
                        generator.setGenerating(!generator.isGenerating());
                    }
                    else{
                        // get ingots
                        player.sendMessage(new TextComponent("get ingots"), Util.NIL_UUID);
                        ItemStack item = player.getItemInHand(hand);
                        if(item.getItem() instanceof BlockItem blockItem){
                            var blockState  = blockItem.getBlock().defaultBlockState();
                            generator.setGeneratingBlock(blockState);
                        }
                    }
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    private double getXFromHit(Direction facing, Vec3 hit){
        return switch(facing){
            case UP, DOWN, NORTH -> 1-hit.x;
            case SOUTH -> hit.x;
            case WEST -> hit.z;
            case EAST -> 1-hit.z;
        };
    }

    private double getYFromHit(Direction facing, Vec3 hit){
        return switch(facing){
            case UP -> hit.z;
            case DOWN -> 1-hit.z;
            case NORTH, SOUTH, EAST, WEST -> hit.y;
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
