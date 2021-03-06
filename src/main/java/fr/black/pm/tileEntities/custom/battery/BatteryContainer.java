package fr.black.pm.tileEntities.custom.battery;

import fr.black.pm.energy.CustomEnergyStorage;
import fr.black.pm.tileEntities.ModTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class BatteryContainer extends AbstractContainerMenu{

    private BlockEntity blockEntity;
    private Player player;
    private IItemHandler playerInventory;

    public BatteryContainer(int windowId, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModTileEntities.BATTERY_CONTAINER.get(), windowId);
        this.blockEntity = player.getCommandSenderWorld().getBlockEntity(pos);
        this.player = player;
        this.playerInventory = new InvWrapper(playerInventory);

        if(blockEntity != null){
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h ->{
                addSlot(new SlotItemHandler(h,0,64,24));
            });
        }
        layoutPlayerInventorySlots(10,70);
        trackPower();
    }

    private void trackPower() {
        // need to split our 32 bit integer into two 16 bit integer
        addDataSlot(new DataSlot(){
            @Override
            public int get() {
                return getEnergy() & 0xffff;
            }

            @Override
            public void set(int value) {
                blockEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h ->{
                    int energyStored = h.getEnergyStored() & 0xffff0000;
                    ((CustomEnergyStorage)h).setEnergy(energyStored + (value & 0xffff));
                });
            }
        });

        addDataSlot(new DataSlot(){
            @Override
            public int get() {
                return (getEnergy() >> 16) & 0xffff;
            }

            @Override
            public void set(int value) {
                blockEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h ->{
                    int energyStored = h.getEnergyStored() & 0x0000ffff;
                    ((CustomEnergyStorage)h).setEnergy(energyStored | (value << 16));
                });
            }
        });
    }

    public int getEnergy() {
        return blockEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    private void layoutPlayerInventorySlots(int left, int top) {
        //player Inventory
        addSlotBox(playerInventory, 9, left, top, 9,18,3,18);

        //hotbar
        top += 58;
        addSlotRange(playerInventory, 0,left, top, 9,18);
    }

    private void addSlotBox(IItemHandler handler, int index, int left, int top, int horAmount, int dx, int verAmount, int dy) {
        for(int i=0; i<verAmount; i++){
            index = addSlotRange(handler, index, left, top, horAmount, dx);
            top += dy;
        }
    }


    private int addSlotRange(IItemHandler handler, int index, int left, int top, int amount, int dx) {
        for(int j=0; j<amount; j++){
            addSlot(new SlotItemHandler(handler, index, left, top));
            left+=dx;
            index++;
        }
        return index;
    }


    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), player, ModTileEntities.BATTERY.get());
    }
}
