package fr.black.pm.datagen;

import fr.black.pm.block.ModBlocks;
import fr.black.pm.item.ModItems;
import fr.black.pm.tileEntities.ModTileEntities;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;

public class LootTables extends BlockLoot {


    @Override
    protected void addTables() {

        this.add(ModBlocks.TITANIUM_SLAB.get(), (block) -> {
            return createSlabItemTable(ModBlocks.TITANIUM_SLAB.get());
        });
        this.add(ModBlocks.RUBY_SLAB.get(), (block) -> {
            return createSlabItemTable(ModBlocks.RUBY_SLAB.get());
        });

        this.add(ModBlocks.TITANIUM_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.TITANIUM_ORE.get(), ModItems.RAW_TITANIUM.get());
        });
        this.add(ModBlocks.RUBY_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.RUBY_ORE.get(), ModItems.RUBY.get());
        });

        this.dropSelf(ModBlocks.TITANIUM_BLOCK.get());
        this.dropSelf(ModBlocks.TITANIUM_DOOR.get());
        this.dropSelf(ModBlocks.TITANIUM_TRAPDOOR.get());
        this.dropSelf(ModBlocks.TITANIUM_BUTTON.get());
        this.dropSelf(ModBlocks.TITANIUM_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.TITANIUM_WALL.get());
        this.dropSelf(ModBlocks.SPEEDY_BLOCK.get());
        this.dropSelf(ModBlocks.TITANIUM_STAIRS.get());
        this.dropSelf(ModBlocks.RUBY_BLOCK.get());
        this.dropSelf(ModBlocks.RUBY_WALL.get());
        this.dropSelf(ModBlocks.RUBY_STAIRS.get());
        this.dropSelf(ModBlocks.FIRESTONE_BLOCK.get());
        this.dropSelf(ModBlocks.ORCHID.get());
        this.dropSelf(ModBlocks.REDWOOD_LOG.get());
        this.dropSelf(ModBlocks.REDWOOD_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_REDWOOD_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_REDWOOD_WOOD.get());
        this.dropSelf(ModBlocks.REDWOOD_PLANKS.get());
        this.dropSelf(ModBlocks.REDWOOD_LEAVES.get());
        this.dropSelf(ModBlocks.REDWOOD_SAPLING.get());
        this.dropSelf(ModTileEntities.POWERGEN.get());
        this.dropSelf(ModTileEntities.LIGHTNING_CHANNELER.get());
        this.dropSelf(ModTileEntities.ORE_GENERATOR.get());
        this.dropSelf(ModTileEntities.CABLE.get());
        this.dropSelf(ModTileEntities.BATTERY.get());


        this.add(ModBlocks.TEST_BLOCK.get(), noDrop());
        this.add(ModBlocks.TOMATO_PLANT.get(), noDrop());
        this.add(ModBlocks.PEPPER_PLANT.get(), noDrop());
/*
        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.TOMATO_PLANT.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
        this.createCropDrops(ModBlocks.TOMATO_PLANT.get(), ModItems.TOMATO.get(), ModItems.TOMATO_SEEDS.get(), lootitemcondition$builder);
        LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.PEPPER_PLANT.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
        this.createCropDrops(ModBlocks.PEPPER_PLANT.get(), ModItems.PEPPER.get(), ModItems.PEPPER_SEEDS.get(), lootitemcondition$builder1);
    */
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}