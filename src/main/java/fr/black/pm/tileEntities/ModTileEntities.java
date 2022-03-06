package fr.black.pm.tileEntities;

import fr.black.pm.block.ModBlocks;
import fr.black.pm.PremierMod;
import fr.black.pm.item.ModCreativeModeTab;
import fr.black.pm.item.ModItems;
import fr.black.pm.tileEntities.custom.lightningChanneler.LightningChannelerBlock;
import fr.black.pm.tileEntities.custom.lightningChanneler.LightningChannelerBlockEntity;
import fr.black.pm.tileEntities.custom.lightningChanneler.LightningChannelerContainer;
import fr.black.pm.tileEntities.custom.oreGenerator.OreGeneratorBlock;
import fr.black.pm.tileEntities.custom.oreGenerator.OreGeneratorBlockEntity;
import fr.black.pm.tileEntities.custom.powergen.PowergenBlock;
import fr.black.pm.tileEntities.custom.powergen.PowergenBlockEntity;
import fr.black.pm.tileEntities.custom.powergen.PowergenContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class ModTileEntities {
    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, PremierMod.MOD_ID);

    public static DeferredRegister<MenuType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, PremierMod.MOD_ID);


    public static final RegistryObject<PowergenBlock> POWERGEN = registerBlock("powergen", PowergenBlock::new);
    public static final RegistryObject<BlockEntityType<PowergenBlockEntity>> POWERGEN_BLOCKENTITY =
            BLOCK_ENTITIES.register("powergen", () -> BlockEntityType.Builder.of(PowergenBlockEntity::new, POWERGEN.get()).build(null));
    public static final RegistryObject<MenuType<PowergenContainer>> POWERGEN_CONTAINER =
            CONTAINERS.register("powergen", () -> IForgeMenuType.create((windowId, inv, data) -> new PowergenContainer(windowId,data.readBlockPos(), inv, inv.player)));

    public static final RegistryObject<OreGeneratorBlock> ORE_GENERATOR = registerBlock("ore_generator", OreGeneratorBlock::new);
    public static final RegistryObject<BlockEntityType<OreGeneratorBlockEntity>> ORE_GENERATOR_BLOCKENTITY =
            BLOCK_ENTITIES.register("ore_generator", () -> BlockEntityType.Builder.of(OreGeneratorBlockEntity::new, ORE_GENERATOR.get()).build(null));


    public static final RegistryObject<Block> LIGHTNING_CHANNELER = registerBlock("lightning_channeler", LightningChannelerBlock::new);
    public static final RegistryObject<BlockEntityType<LightningChannelerBlockEntity>> LIGHTNING_CHANNELER_BLOCKENTITY =
            BLOCK_ENTITIES.register("lightning_channeler", () -> BlockEntityType.Builder.of(LightningChannelerBlockEntity::new, LIGHTNING_CHANNELER.get()).build(null));
    public static final RegistryObject<MenuType<LightningChannelerContainer>> LIGHTNING_CHANNELER_CONTAINER =
            CONTAINERS.register("lightning_channeler", () -> IForgeMenuType.create((windowId, inv, data) -> new LightningChannelerContainer(windowId,data.readBlockPos(), inv, inv.player)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = ModBlocks.BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModCreativeModeTab.PREMIER_MOD_TAB)));
    }


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
        CONTAINERS.register(eventBus);
    }
}