package fr.black.pm.effect;


import fr.black.pm.PremierMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static final DeferredRegister<MobEffect> CURSES =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, PremierMod.MOD_ID);

    public static final RegistryObject<MobEffect> LIGHTNING_STRIKE =
            CURSES.register("lightning_strike", () -> new ModMobEffect(MobEffectCategory.HARMFUL, 0xffffff));

    public static void register(IEventBus eventBus) {
        CURSES.register(eventBus);
    }
}
