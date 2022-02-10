package fr.black.pm.item.custom.baguette;

import fr.black.pm.item.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Baguette extends Item {
    public Baguette(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if(entity instanceof Player){
            Player player = (Player) entity;
            player.addItem(new ItemStack(ModItems.BAGUETTE_BITE_1.get()));
        }
        return super.finishUsingItem(itemStack, level, entity);
    }
}
