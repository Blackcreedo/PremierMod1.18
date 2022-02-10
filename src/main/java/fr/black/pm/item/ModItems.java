package fr.black.pm.item;

import fr.black.pm.PremierMod;
import fr.black.pm.block.ModBlocks;
import fr.black.pm.item.custom.*;
import fr.black.pm.item.custom.baguette.Baguette;
import fr.black.pm.item.custom.baguette.BaguetteBite;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems 
{

	public static final DeferredRegister<Item> ITEMS = 
			DeferredRegister.create(ForgeRegistries.ITEMS, PremierMod.MOD_ID);
	
	public static final RegistryObject<Item> TITANIUM_INGOT =
			ITEMS.register("titanium_ingot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	
	public static final RegistryObject<Item> TITANIUM_NUGGET = 
			ITEMS.register("titanium_nugget", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	
	public static final RegistryObject<Item> RAW_TITANIUM = 
			ITEMS.register("raw_titanium", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	
	public static final RegistryObject<Item> RUBY = 
			ITEMS.register("ruby", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	
	public static final RegistryObject<Item> TOMATO = 
			ITEMS.register("tomato", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)
					.food(new FoodProperties.Builder().nutrition(2).saturationMod(.2f).build())));

	public static final RegistryObject<Item> PEPPER =
			ITEMS.register("pepper", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)
					.food(new FoodProperties.Builder().nutrition(3).saturationMod(.2f).build())));
	
	public static final RegistryObject<Item> SMART_BLOW_TORCH = 
			ITEMS.register("smart_blow_torch", () -> new SmartBlowTorchItem(new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)
					.durability(500)));
	
	public static final RegistryObject<Item> FIRESTONE = 
			ITEMS.register("firestone", () -> new Firestone(new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)
					.durability(30)));
	
	public static final RegistryObject<Item> COAL_COKE = 
			ITEMS.register("coal_coke", () -> new CoalCokeItem(new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));


	/* TITANIUM TOOLS AND ARMOR */
	public static final RegistryObject<Item> TITANIUM_SWORD =
			ITEMS.register("titanium_sword", () -> new SwordItem(ModTiers.TITANIUM, 3,1f,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> TITANIUM_PICKAXE =
			ITEMS.register("titanium_pickaxe", () -> new PickaxeItem(ModTiers.TITANIUM, 1,1f,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> TITANIUM_AXE =
			ITEMS.register("titanium_axe", () -> new AxeItem(ModTiers.TITANIUM, 4,1f,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> TITANIUM_SHOVEL =
			ITEMS.register("titanium_shovel", () -> new ShovelItem(ModTiers.TITANIUM, 1,1f,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> TITANIUM_HOE =
			ITEMS.register("titanium_hoe", () -> new HoeItem(ModTiers.TITANIUM, 1,1f,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> TITANIUM_HELMET =
			ITEMS.register("titanium_helmet", () -> new ModArmorItems(ModArmorMaterials.TITANIUM, EquipmentSlot.HEAD,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> TITANIUM_CHESTPLATE =
			ITEMS.register("titanium_chestplate", () -> new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.CHEST,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> TITANIUM_LEGGINGS =
			ITEMS.register("titanium_leggings", () -> new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.LEGS,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> TITANIUM_BOOTS =
			ITEMS.register("titanium_boots", () -> new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.FEET,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));


	/* RUBY TOOLS AND ARMOR*/
	public static final RegistryObject<Item> RUBY_SWORD =
			ITEMS.register("ruby_sword", () -> new SwordItem(ModTiers.RUBY, 5,1f,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> RUBY_PICKAXE =
			ITEMS.register("ruby_pickaxe", () -> new PickaxeItem(ModTiers.RUBY, 1,1f,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> RUBY_AXE =
			ITEMS.register("ruby_axe", () -> new AxeItem(ModTiers.RUBY, 6,1f,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> RUBY_SHOVEL =
			ITEMS.register("ruby_shovel", () -> new ShovelItem(ModTiers.RUBY, 1,1f,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> RUBY_HOE =
			ITEMS.register("ruby_hoe", () -> new HoeItem(ModTiers.RUBY, 1,1f,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> RUBY_HELMET =
			ITEMS.register("ruby_helmet", () -> new ModArmorItems(ModArmorMaterials.RUBY, EquipmentSlot.HEAD,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> RUBY_CHESTPLATE =
			ITEMS.register("ruby_chestplate", () -> new ArmorItem(ModArmorMaterials.RUBY, EquipmentSlot.CHEST,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> RUBY_LEGGINGS =
			ITEMS.register("ruby_leggings", () -> new ArmorItem(ModArmorMaterials.RUBY, EquipmentSlot.LEGS,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	public static final RegistryObject<Item> RUBY_BOOTS =
			ITEMS.register("ruby_boots", () -> new ArmorItem(ModArmorMaterials.RUBY, EquipmentSlot.FEET,
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));

	/* HORSE ARMOR */
	public static final RegistryObject<Item> TITANIUM_HORSE_ARMOR =
			ITEMS.register("titanium_horse_armor", () -> new HorseArmorItem(5, "titanium",
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));

	/* CUSTOM HORSE ARMOR */
	public static final RegistryObject<Item> RUBY_HORSE_ARMOR =
			ITEMS.register("ruby_horse_armor", () -> new ModHorseArmorItems(10, "ruby",
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));

	public static final RegistryObject<Item> TOMATO_SEEDS = ITEMS.register("tomato_seeds",
			() -> new ItemNameBlockItem(ModBlocks.TOMATO_PLANT.get(),
					new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));


	public static final RegistryObject<Item> PEPPER_SEEDS = ITEMS.register("pepper_seeds",
			() -> new ItemNameBlockItem(ModBlocks.PEPPER_PLANT.get(),
			new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));

	public static final RegistryObject<Item> LIGHTNING_STRIKE_CURSE1 =
			ITEMS.register("lightning_strike_curse1", () -> new LightningStrikeCurseItem(new Item.Properties().tab(ModCreativeModeTab.CURSES_TAB), 1));
	public static final RegistryObject<Item> LIGHTNING_STRIKE_CURSE2 =
			ITEMS.register("lightning_strike_curse2", () -> new LightningStrikeCurseItem(new Item.Properties().tab(ModCreativeModeTab.CURSES_TAB), 2));
	public static final RegistryObject<Item> LIGHTNING_STRIKE_CURSE3 =
			ITEMS.register("lightning_strike_curse3", () -> new LightningStrikeCurseItem(new Item.Properties().tab(ModCreativeModeTab.CURSES_TAB), 3));
	public static final RegistryObject<Item> LIGHTNING_STRIKE_CURSE4 =
			ITEMS.register("lightning_strike_curse4", () -> new LightningStrikeCurseItem(new Item.Properties().tab(ModCreativeModeTab.CURSES_TAB), 4));
	public static final RegistryObject<Item> LIGHTNING_STRIKE_CURSE5 =
			ITEMS.register("lightning_strike_curse5", () -> new LightningStrikeCurseItem(new Item.Properties().tab(ModCreativeModeTab.CURSES_TAB), 5));

	public static final RegistryObject<Item> BAGUETTE =
			ITEMS.register("baguette", () -> new Baguette(new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)
					.food(new FoodProperties.Builder().nutrition(5).saturationMod(.6f).build())));

	public static final RegistryObject<Item> BAGUETTE_BITE_1 =
			ITEMS.register("baguette_bite_1", () -> new BaguetteBite(new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)
					.food(new FoodProperties.Builder().nutrition(5).saturationMod(.6f).build())));

	public static final RegistryObject<Item> BAGUETTE_BITE_2 =
			ITEMS.register("baguette_bite_2", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)
					.food(new FoodProperties.Builder().nutrition(5).saturationMod(.6f).build())));

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
	
}
