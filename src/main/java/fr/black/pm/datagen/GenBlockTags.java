package fr.black.pm.datagen;

import fr.black.pm.PremierMod;
import fr.black.pm.block.ModBlocks;
import fr.black.pm.tileEntities.ModTileEntities;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;

public class GenBlockTags extends BlockTagsProvider {

    public GenBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, PremierMod.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.TITANIUM_STAIRS.get())
                .add(ModBlocks.TITANIUM_SLAB.get())
                .add(ModBlocks.TITANIUM_WALL.get())
                .add(ModBlocks.TITANIUM_DOOR.get())
                .add(ModBlocks.TITANIUM_TRAPDOOR.get())
                .add(ModBlocks.TITANIUM_PRESSURE_PLATE.get())
                .add(ModBlocks.TITANIUM_BUTTON.get())
                .add(ModBlocks.SPEEDY_BLOCK.get())
                .add(ModBlocks.RUBY_ORE.get())
                .add(ModBlocks.RUBY_BLOCK.get())
                .add(ModBlocks.RUBY_STAIRS.get())
                .add(ModBlocks.RUBY_SLAB.get())
                .add(ModBlocks.RUBY_WALL.get())
                .add(ModBlocks.FIRESTONE_BLOCK.get())
                .add(ModTileEntities.POWERGEN.get())
                .add(ModTileEntities.ORE_GENERATOR.get())
                .add(ModTileEntities.CABLE.get())
                .add(ModTileEntities.BATTERY.get())
                .add(ModBlocks.BATTERY_STORAGE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.TITANIUM_STAIRS.get())
                .add(ModBlocks.TITANIUM_SLAB.get())
                .add(ModBlocks.TITANIUM_WALL.get())
                .add(ModBlocks.TITANIUM_DOOR.get())
                .add(ModBlocks.TITANIUM_TRAPDOOR.get())
                .add(ModBlocks.TITANIUM_PRESSURE_PLATE.get())
                .add(ModBlocks.TITANIUM_BUTTON.get())
                .add(ModBlocks.RUBY_ORE.get())
                .add(ModBlocks.RUBY_BLOCK.get())
                .add(ModBlocks.RUBY_STAIRS.get())
                .add(ModBlocks.RUBY_SLAB.get())
                .add(ModBlocks.RUBY_WALL.get())
                .add(ModBlocks.FIRESTONE_BLOCK.get())
                .add(ModTileEntities.POWERGEN.get())
                .add(ModTileEntities.ORE_GENERATOR.get())
                .add(ModTileEntities.CABLE.get())
                .add(ModTileEntities.BATTERY.get())
                .add(ModBlocks.BATTERY_STORAGE.get());

        tag(Tags.Blocks.ORES)
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.RUBY_ORE.get());

        tag(BlockTags.LOGS)
                .add(ModBlocks.REDWOOD_LOG.get())
                .add(ModBlocks.REDWOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_REDWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_REDWOOD_WOOD.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.REDWOOD_LOG.get())
                .add(ModBlocks.REDWOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_REDWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_REDWOOD_WOOD.get())
                .add(ModBlocks.REDWOOD_PLANKS.get());
    }

    @Override
    public String getName() {
        return "Tutorial Tags";
    }
}
