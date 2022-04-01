package fr.black.pm.tileEntities.custom.battery;

import fr.black.pm.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class Battery extends Block implements EntityBlock {

    public static final String SCREEN_BATTERY = "screen.battery";

    public Battery() {
        super(Properties.of(Material.METAL).sound(SoundType.METAL).strength(2f).noOcclusion().requiresCorrectToolForDrops());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BatteryBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide()) {
            return (lvl, pos, stt, be) -> {
                if (be instanceof BatteryBlockEntity battery) battery.tickServer();
            };
        }
        return null;
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block p_60512_, BlockPos neighbor, boolean p_60514_) {
        System.out.println("neighborChanged");
        if(world.getBlockState(neighbor).getBlock() == ModBlocks.BATTERY_STORAGE.get()){
            if (world.getBlockEntity(pos) instanceof BatteryBlockEntity){
                System.out.println("call update energy storage method");
                BatteryBlockEntity battery = (BatteryBlockEntity) world.getBlockEntity(pos);
                battery.updateEnergyStorage();
            }
        }
        super.neighborChanged(state, world, pos, p_60512_, neighbor, p_60514_);
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader world, BlockPos pos, BlockPos neighbor) {
        System.out.println("onNeighborChange");

        super.onNeighborChange(state, world, pos, neighbor);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof BatteryBlockEntity) {
                MenuProvider containerProvider = new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return new TranslatableComponent(SCREEN_BATTERY);
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
                        return new BatteryContainer(windowId, pos, playerInventory, playerEntity);
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
