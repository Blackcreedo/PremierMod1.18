package fr.black.pm.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;

public class ModMobEffect extends MobEffect {
    protected ModMobEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int p_19468_) {
        if(this == ModEffects.LIGHTNING_STRIKE.get()){
            if(entity instanceof Player){
                Player player = (Player) entity;
                if(!player.level.isClientSide()){
                    ServerLevel world = (ServerLevel) player.level;
                    BlockPos position = player.blockPosition();
                    EntityType.LIGHTNING_BOLT.spawn(world, null, null, position, MobSpawnType.TRIGGERED, true, true);
                }
            } else{
                ServerLevel world = (ServerLevel) entity.level;
                BlockPos position = entity.blockPosition();
                EntityType.LIGHTNING_BOLT.spawn(world, null, null, position, MobSpawnType.TRIGGERED, true, true);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        if (this == ModEffects.LIGHTNING_STRIKE.get()) {
            int i = (300*40) >> p_19456_;
            if (i > 0) {
                return p_19455_ % i == 0;
            } else {
                return true;
            }
        }

        return super.isDurationEffectTick(p_19455_, p_19456_);
    }

}
