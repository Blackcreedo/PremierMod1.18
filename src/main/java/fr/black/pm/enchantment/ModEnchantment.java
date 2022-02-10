package fr.black.pm.enchantment;

import fr.black.pm.PremierMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantment {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS
            = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, PremierMod.MOD_ID);

    public static final RegistryObject<Enchantment> LIGHTNING_STRIKER
            = ENCHANTMENTS.register("lightning_striker", () -> new LightningStrikerEnchantment(
            Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> LIGHTNING_STRIKER_BIS
            = ENCHANTMENTS.register("lightning_striker_bis", () -> new LightningStrikerEnchantmentBis(
            Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));


    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
