package fr.black.pm.tileEntities.custom.lightningChanneler;

import fr.black.pm.tileEntities.ModTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class LightningChannelerContainer extends AbstractContainerMenu {

    private BlockEntity blockEntity;
    private Player player;
    private IItemHandler playerInventory;


    public LightningChannelerContainer(int windowId, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModTileEntities.LIGHTNING_CHANNELER_CONTAINER.get(), windowId);
        this.blockEntity = player.getCommandSenderWorld().getBlockEntity(pos);
        this.player = player;
        this.playerInventory = new InvWrapper(playerInventory);

        if(blockEntity != null){
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h ->{
                addSlot(new SlotItemHandler(h,0,80,31));
                addSlot(new SlotItemHandler(h,1,80,53));
            });
        }
        layoutPlayerInventorySlots(8,86);
    }

    public boolean isLightningStorm() {
        return blockEntity.getLevel().isThundering() || blockEntity.getLevel().isRaining();
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
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), player, ModTileEntities.LIGHTNING_CHANNELER.get());
    }


    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()){
            ItemStack stack = slot.getItem();
            itemStack = stack.copy();
            if(index == 0 || index == 1){
                if(this.moveItemStackTo(stack,2,38,true)){
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemStack);
            } else {
                if (!this.moveItemStackTo(stack, 0, 2, false)) {
                    return ItemStack.EMPTY;
                }
                if(index < 29){
                    if(!this.moveItemStackTo(stack, 29,38,false)){
                        return ItemStack.EMPTY;
                    }
                } else if(index < 38 && !this.moveItemStackTo(stack, 2,29,false)){
                    return ItemStack.EMPTY;
                }
            }

            if(stack.isEmpty()){
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if(stack.getCount() == itemStack.getCount()){
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }
        return itemStack;
    }

}
