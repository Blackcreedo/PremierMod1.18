package fr.black.pm.enchantment;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LightningStrikerEnchantment extends Enchantment {
    boolean called;

    protected LightningStrikerEnchantment(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot... p_44678_) {
        super(p_44676_, p_44677_, p_44678_);
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if (called){called = false;} // fix the 2 times call
        else{
            called = true;
            ServerPlayer player = (ServerPlayer) pAttacker;
            player.sendMessage(new TextComponent("doPostAttack called"), Util.NIL_UUID);
            if(!pAttacker.level.isClientSide()){
                if(pTarget instanceof LivingEntity) {
                    LivingEntity target = ((LivingEntity) pTarget);
                    generateLightning(pAttacker, pTarget, pLevel);
                }
            }
        }
        super.doPostAttack(pAttacker, pTarget, pLevel);
    }

    private void generateLightning(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        ServerLevel world = (ServerLevel) pAttacker.level;
        ServerPlayer player = (ServerPlayer) pAttacker;
        BlockPos position = pTarget.blockPosition();
        player.sendMessage(new TextComponent("generateLightning called"), Util.NIL_UUID);
        for (int i=0; i<pLevel; i++){
            EntityType.LIGHTNING_BOLT.spawn(world, null, null, position, MobSpawnType.TRIGGERED, true, true);
        }
    }


    @Override
    public int getMaxLevel() {
        return 5;
    }

}
