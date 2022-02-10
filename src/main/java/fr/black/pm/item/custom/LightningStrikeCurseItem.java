package fr.black.pm.item.custom;

import fr.black.pm.effect.ModEffects;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class LightningStrikeCurseItem extends Item {
    private int level;
    public LightningStrikeCurseItem(Properties properties, int level) {
        super(properties);
        this.level = level;
    }


    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity entity, InteractionHand interactionHand) {
        player.sendMessage(new TextComponent("interactLivingEntity  called"), Util.NIL_UUID);
        if (entity.level.isClientSide) return net.minecraft.world.InteractionResult.PASS;
        ServerLevel world = (ServerLevel) entity.level;

        if(!player.isCreative()) {
            player.getMainHandItem().shrink(1);
        }

        if (Math.random() > 0.1) {
            BlockPos position = entity.blockPosition();
            EntityType.LIGHTNING_BOLT.spawn(world, null, null, position, MobSpawnType.TRIGGERED, true, true);

            entity.addEffect(new MobEffectInstance(ModEffects.LIGHTNING_STRIKE.get(), 100000, this.level-1, false, false));
        } else {
            BlockPos position = player.blockPosition();
            EntityType.LIGHTNING_BOLT.spawn(world, null, null, position, MobSpawnType.TRIGGERED, true, true);

            player.addEffect(new MobEffectInstance(ModEffects.LIGHTNING_STRIKE.get(), 100000, this.level-1, false, false));
        }


        return super.interactLivingEntity(itemStack, player, entity, interactionHand);
    }
}
