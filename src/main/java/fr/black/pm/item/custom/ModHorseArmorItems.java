package fr.black.pm.item.custom;

import fr.black.pm.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ModHorseArmorItems extends HorseArmorItem{

    public ModHorseArmorItems(int pProtection, String pMaterial, Properties pProperties) {
        super(pProtection, pMaterial, pProperties);
    }

    @Override
    public void onHorseArmorTick(ItemStack stack, Level world, Mob horse){
        if(!world.isClientSide()) {
            if (horse instanceof Horse){
                Horse horse1 = (Horse)horse;
                if (horse1.getArmor().getItem() == ModItems.RUBY_HORSE_ARMOR.get()){
                    horse.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,20,3));
                }
            }
        }
    }

}
