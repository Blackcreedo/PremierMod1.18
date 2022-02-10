package fr.black.pm.block;

import fr.black.pm.PremierMod;
import fr.black.pm.block.custom.*;
import fr.black.pm.item.ModCreativeModeTab;
import fr.black.pm.item.ModItems;
import fr.black.pm.world.features.tree.RedwoodTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import java.util.function.Supplier;

public class ModBlocks{

	public static final DeferredRegister<Block> BLOCKS =
			DeferredRegister.create(ForgeRegistries.BLOCKS, PremierMod.MOD_ID);
	
	public static final RegistryObject<Block> TITANIUM_BLOCK =
			registerBlock("titanium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> TITANIUM_STAIRS =
			registerBlock("titanium_stairs", () -> new StairBlock(() -> TITANIUM_BLOCK.get().defaultBlockState(), 
					BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> TITANIUM_SLAB = 
			registerBlock("titanium_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> TITANIUM_WALL = 
			registerBlock("titanium_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()));
	
	
	public static final RegistryObject<Block> TITANIUM_ORE = 
			registerBlock("titanium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(8f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> TITANIUM_DOOR = 
			registerBlock("titanium_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> TITANIUM_TRAPDOOR = 
			registerBlock("titanium_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> TITANIUM_PRESSURE_PLATE = 
			registerBlock("titanium_pressure_plate", () -> new PressurePlateBlock(
					PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> TITANIUM_BUTTON = 
			registerBlock("titanium_button", () -> new TitaniumButtonBlock(
					BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> SPEEDY_BLOCK = 
			registerBlock("speedy_block", () -> new SpeedyBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> RUBY_ORE = 
			registerBlock("ruby_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> RUBY_BLOCK = 
			registerBlock("ruby_block", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).strength(5f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> RUBY_STAIRS =
			registerBlock("ruby_stairs", () -> new StairBlock(() -> RUBY_BLOCK.get().defaultBlockState(),
					BlockBehaviour.Properties.of(Material.AMETHYST).strength(5f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> RUBY_SLAB = 
			registerBlock("ruby_slab", () -> new SlabBlock( BlockBehaviour.Properties.of(Material.AMETHYST).strength(5f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> RUBY_WALL = 
			registerBlock("ruby_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.AMETHYST).strength(5f).requiresCorrectToolForDrops()));
	
	
	public static final RegistryObject<Block> FIRESTONE_BLOCK = 
			registerBlock("firestone_block", () -> new FireStoneBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> TEST_BLOCK =
			registerBlock("test_block", () -> new TestBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> TOMATO_PLANT =
			BLOCKS.register("tomato_plant", () -> new TomatoPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));


	public static final RegistryObject<Block> PEPPER_PLANT =
			BLOCKS.register("pepper_plant", () -> new PepperPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

	public static final RegistryObject<Block> ORCHID =
			registerBlock("orchid", () -> new FlowerBlock(MobEffects.BLINDNESS, 2, BlockBehaviour.Properties.copy(Blocks.DANDELION)));

	/* RED WOOD */
	public static final RegistryObject<Block> REDWOOD_LOG =
			registerBlock("redwood_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<Block> REDWOOD_WOOD =
			registerBlock("redwood_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
	public static final RegistryObject<Block> STRIPPED_REDWOOD_LOG =
			registerBlock("stripped_redwood_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
	public static final RegistryObject<Block> STRIPPED_REDWOOD_WOOD =
			registerBlock("stripped_redwood_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
	public static final RegistryObject<Block> REDWOOD_PLANKS =
			registerBlock("redwood_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}
				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});
	public static final RegistryObject<Block> REDWOOD_LEAVES =
			registerBlock("redwood_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}
				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			});
	public static final RegistryObject<Block> REDWOOD_SAPLING =
			registerBlock("redwood_sapling", () -> new SaplingBlock(new RedwoodTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));




	
	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
	{
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}
	
	
	private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
	{
		ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
	}
	
	public static void register(IEventBus eventBus)
	{
		BLOCKS.register(eventBus);
	}
	
	
	
}
