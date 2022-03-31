package fr.black.pm.tileEntities.custom.cable;

import fr.black.pm.energy.CustomEnergyStorage;
import fr.black.pm.tileEntities.ModTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public class CableBlockEntity extends BlockEntity {

    private static final int ENERGY_CAPACITY = 5000;
    private static final int ENERGY_RECEIVE = 500;
    private static final int CABLE_SEND = 500;

    private final CustomEnergyStorage energy = createEnergyStorage();
    private final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> energy);

    public CableBlockEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.CABLE_BLOCKENTITY.get(), pos, state);
    }

    private CustomEnergyStorage createEnergyStorage() {
        return new CustomEnergyStorage(ENERGY_CAPACITY, ENERGY_RECEIVE) {
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
                    if(blockEntity instanceof CableBlockEntity){
                        System.out.println("here");
                    }else{
                        boolean doContinue = blockEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).map(handler -> {
                            if (handler.canReceive()) {
                                int received = handler.receiveEnergy(Math.min(capacity.get(), CABLE_SEND), false);
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