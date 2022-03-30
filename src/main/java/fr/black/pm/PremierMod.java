package fr.black.pm;

import com.google.common.collect.ImmutableMap;
import fr.black.pm.block.ModBlocks;
import fr.black.pm.effect.ModEffects;
import fr.black.pm.enchantment.ModEnchantment;
import fr.black.pm.item.ModItems;
import fr.black.pm.tileEntities.ModTileEntities;
import fr.black.pm.tileEntities.custom.battery.BatteryScreen;
import fr.black.pm.tileEntities.custom.cable.CableScreen;
import fr.black.pm.tileEntities.custom.lightningChanneler.LightningChannelerScreen;
import fr.black.pm.tileEntities.custom.oreGenerator.OreGeneratorModelLoader;
import fr.black.pm.tileEntities.custom.powergen.PowergenScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(PremierMod.MOD_ID)
@Mod.EventBusSubscriber(modid = PremierMod.MOD_ID, value= Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PremierMod 
{
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "pm";
	
	
	public PremierMod()
	{
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		ModItems.register(eventBus);
		ModBlocks.register(eventBus);
		ModEnchantment.register(eventBus);
		ModEffects.register(eventBus);
		ModTileEntities.register(eventBus);
		eventBus.addListener(this::setup);
		eventBus.addListener(this::setupClient);
		
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	
	private void setupClient(final FMLCommonSetupEvent event)
	{
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.TITANIUM_DOOR.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.TITANIUM_TRAPDOOR.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.TOMATO_PLANT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.PEPPER_PLANT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.ORCHID.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.REDWOOD_LEAVES.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.REDWOOD_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModTileEntities.POWERGEN.get(), RenderType.translucent());

		event.enqueueWork(() -> {
			MenuScreens.register(ModTileEntities.POWERGEN_CONTAINER.get(), PowergenScreen::new);
			MenuScreens.register(ModTileEntities.CABLE_CONTAINER.get(), CableScreen::new);
			MenuScreens.register(ModTileEntities.BATTERY_CONTAINER.get(), BatteryScreen::new);
			MenuScreens.register(ModTileEntities.LIGHTNING_CHANNELER_CONTAINER.get(), LightningChannelerScreen::new);
		});


	}

	@SubscribeEvent
	public static void onModelRegistryEvent(ModelRegistryEvent event){
		ModelLoaderRegistry.registerLoader(OreGeneratorModelLoader.ORE_GENERATOR_LOADER, new OreGeneratorModelLoader());
	}


	private void setup(final FMLCommonSetupEvent event){
		event.enqueueWork(() -> {
			AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
					.put(ModBlocks.REDWOOD_LOG.get(), ModBlocks.STRIPPED_REDWOOD_LOG.get())
					.put(ModBlocks.REDWOOD_WOOD.get(), ModBlocks.STRIPPED_REDWOOD_WOOD.get())
					.build();
		});
	}
	
}
