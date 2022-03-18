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

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicInteger;

public class CableBlockEntity extends BlockEntity {

    public static final int CABLE_CAPACITY = 500000;
    public static final int CABLE_RECEIVE = 100;
    public static final int CABLE_SEND = 200;

    private final CustomEnergyStorage energyStorage = createEnergy();
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);


    public CableBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModTileEntities.CABLE_BLOCKENTITY.get(), pos, blockState);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        energy.invalidate();
    }

    public void tickServer(){
        sendOutPower();
    }

    private void sendOutPower(){
        AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
        if(capacity.get() > 0){
            for(Direction direction : Direction.values()){
                BlockEntity blockEntity = level.getBlockEntity(worldPosition.relative(direction));
                if(blockEntity != null){
                    boolean doContinue = blockEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).map(handler -> {
                        if (handler.canReceive()) {
                            int received = handler.receiveEnergy(Math.min(capacity.get(), CABLE_SEND), false);
                            capacity.addAndGet(-received);
                            energyStorage.consumeEnergy(received);
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
    public void load(CompoundTag tag) {
        if(tag.contains("Energy")){
            energyStorage.deserializeNBT(tag.get("Energy"));
        }
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("Energy", energyStorage.serializeNBT());
    }


    private CustomEnergyStorage createEnergy(){
        return new CustomEnergyStorage(CABLE_CAPACITY, CABLE_RECEIVE) {
            @Override
            protected void onEnergyChange() {setChanged();}
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if(cap == CapabilityEnergy.ENERGY){
            return energy.cast();
        }
        return super.getCapability(cap);
    }

}
