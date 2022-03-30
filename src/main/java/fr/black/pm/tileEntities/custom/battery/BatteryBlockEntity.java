package fr.black.pm.tileEntities.custom.battery;

import fr.black.pm.energy.CustomEnergyStorage;
import fr.black.pm.tileEntities.ModTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class BatteryBlockEntity extends BlockEntity {

    private static final int ENERGY_CAPACITY = 100000;
    private static final int ENERGY_RECEIVE = 500;

    private final CustomEnergyStorage energy = createEnergyStorage();
    private final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> energy);

    public BatteryBlockEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.BATTERY_BLOCKENTITY.get(), pos, state);
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
