package fr.black.pm.tileEntities.custom.battery;

import fr.black.pm.block.ModBlocks;
import fr.black.pm.energy.CustomEnergyStorage;
import fr.black.pm.tileEntities.ModTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class BatteryBlockEntity extends BlockEntity {

    private static final int ENERGY_CAPACITY = 1000;
    private static final int ENERGY_RECEIVE = 500;
    private static final int BATTERY_SEND = 500;

    private CustomEnergyStorage energy = createEnergyStorage(ENERGY_CAPACITY);
    private LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> energy);

    public BatteryBlockEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.BATTERY_BLOCKENTITY.get(), pos, state);
    }

    public void updateEnergyStorage(){
        System.out.println("updateEnergyStorage Called");
        int number = numberBatteryConnected();
        int totalCapacity = number*ENERGY_CAPACITY;
        energy = createEnergyStorage(totalCapacity);
        System.out.println(energy.getMaxEnergyStored());
    }

    private int numberBatteryConnected(){
        int count = 1;
        LinkedList<Block> frontier = new LinkedList<Block>();
        LinkedList<Direction> originDirections = new LinkedList<Direction>();
        for(Direction direction : Direction.values()) {
            Block block = level.getBlockState(worldPosition.relative(direction)).getBlock(); //doesn't work "this.level" is null
            if(block == ModBlocks.BATTERY_STORAGE.get()){
                System.out.println("yes");
                frontier.add(block);
                originDirections.add(direction);
            }
        }
        while(!frontier.isEmpty()){
            count++;
            System.out.println(count);
            Block block = frontier.pop();
            Direction origin = originDirections.pop();
            for(Direction direction : Direction.values()) {
                if(level.getBlockState(getBlockPos(block)))



                if(level.getBlockEntity(worldPosition.relative(direction)) instanceof BatteryBlockEntity && direction!=origin){
                    System.out.println("yes");
                    frontier.add(level.getBlockEntity(block.getBlockPos().relative(direction)));
                    originDirections.add(direction);
                }
            }
        }

        return count;
    }


    private CustomEnergyStorage createEnergyStorage(int capacity) {
        return new CustomEnergyStorage(capacity, ENERGY_RECEIVE) {
            @Override
            protected void onEnergyChange() {
                setChanged();
            }
        };
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        energyHandler.invalidate();
    }

    public void tickServer() {
        sendOutPower();
    }

    private void sendOutPower(){
        AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
        if(capacity.get() > 0){
            for(Direction direction : Direction.values()){
                BlockEntity blockEntity = level.getBlockEntity(worldPosition.relative(direction));
                if(blockEntity != null){
                    boolean doContinue = blockEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).map(handler -> {
                        if (handler.canReceive()) {
                            int received = handler.receiveEnergy(Math.min(capacity.get(), BATTERY_SEND), false);
                            capacity.addAndGet(-received);
                            energy.consumeEnergy(received);
                            setChanged();
                            return capacity.get() > 0;
                        } else {
                            return true;
                        }
                    }).orElse(true);
                    if(!doContinue){
                        return;
                    }
                }
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        saveClientData(tag);
        tag.put("Energy", energy.serializeNBT());
    }

    private void saveClientData(CompoundTag tag) {
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        if (tag.contains("Energy")) {
            energy.deserializeNBT(tag.get("Energy"));
        }
    }


    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return energyHandler.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }

}
