package fr.black.pm.datagen;

import fr.black.pm.PremierMod;
import fr.black.pm.block.ModBlocks;
import fr.black.pm.item.ModItems;
import fr.black.pm.tileEntities.ModTileEntities;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {
    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, PremierMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(ModBlocks.TITANIUM_BLOCK.get().getRegistryName().getPath(), modLoc("block/titanium_block"));
        withExistingParent(ModBlocks.TITANIUM_STAIRS.get().getRegistryName().getPath(), modLoc("block/titanium_stairs"));
        withExistingParent(ModBlocks.TITANIUM_SLAB.get().getRegistryName().getPath(), modLoc("block/titanium_slab"));
        withExistingParent(ModBlocks.TITANIUM_WALL.get().getRegistryName().getPath(), modLoc("block/titanium_wall"));
        withExistingParent(ModBlocks.TITANIUM_ORE.get().getRegistryName().getPath(), modLoc("block/titanium_ore"));
        withExistingParent(ModBlocks.TITANIUM_TRAPDOOR.get().getRegistryName().getPath(), modLoc("block/titanium_trapdoor"));
        withExistingParent(ModBlocks.TITANIUM_PRESSURE_PLATE.get().getRegistryName().getPath(), modLoc("block/titanium_pressure_plate"));
        withExistingParent(ModBlocks.TITANIUM_BUTTON.get().getRegistryName().getPath(), modLoc("block/titanium_button"));
        withExistingParent(ModBlocks.SPEEDY_BLOCK.get().getRegistryName().getPath(), modLoc("block/speedy_block"));
        withExistingParent(ModBlocks.RUBY_ORE.get().getRegistryName().getPath(), modLoc("block/ruby_ore"));
        withExistingParent(ModBlocks.RUBY_BLOCK.get().getRegistryName().getPath(), modLoc("block/ruby_block"));
        withExistingParent(ModBlocks.RUBY_STAIRS.get().getRegistryName().getPath(), modLoc("block/ruby_stairs"));
        withExistingParent(ModBlocks.RUBY_SLAB.get().getRegistryName().getPath(), modLoc("block/ruby_slab"));
        withExistingParent(ModBlocks.RUBY_WALL.get().getRegistryName().getPath(), modLoc("block/ruby_wall"));
        withExistingParent(ModBlocks.FIRESTONE_BLOCK.get().getRegistryName().getPath(), modLoc("block/firestone_block"));
        withExistingParent(ModBlocks.TEST_BLOCK.get().getRegistryName().getPath(), modLoc("block/test_block"));
        withExistingParent(ModBlocks.TOMATO_PLANT.get().getRegistryName().getPath(), modLoc("block/tomato_plant"));
        withExistingParent(ModBlocks.PEPPER_PLANT.get().getRegistryName().getPath(), modLoc("block/pepper_plant"));
        withExistingParent(ModBlocks.ORCHID.get().getRegistryName().getPath(), modLoc("block/orchid"));
        withExistingParent(ModBlocks.REDWOOD_LOG.get().getRegistryName().getPath(), modLoc("block/redwood_log"));
        withExistingParent(ModBlocks.REDWOOD_WOOD.get().getRegistryName().getPath(), modLoc("block/redwood_wood"));
        withExistingParent(ModBlocks.STRIPPED_REDWOOD_LOG.get().getRegistryName().getPath(), modLoc("block/stripped_redwood_log"));
        withExistingParent(ModBlocks.STRIPPED_REDWOOD_WOOD.get().getRegistryName().getPath(), modLoc("block/stripped_redwood_wood"));
        withExistingParent(ModBlocks.REDWOOD_PLANKS.get().getRegistryName().getPath(), modLoc("block/redwood_planks"));
        withExistingParent(ModBlocks.REDWOOD_LEAVES.get().getRegistryName().getPath(), modLoc("block/redwood_leaves"));
        withExistingParent(ModBlocks.REDWOOD_SAPLING.get().getRegistryName().getPath(), modLoc("block/redwood_sapling"));
        withExistingParent(ModTileEntities.POWERGEN.get().getRegistryName().getPath(), modLoc("block/powergen/main"));


        singleTexture(ModItems.BAGUETTE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/baguette"));
        singleTexture(ModItems.BAGUETTE_BITE_1.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/baguette_bite_1"));
        singleTexture(ModItems.BAGUETTE_BITE_2.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/baguette_bite_2"));
        singleTexture(ModItems.COAL_COKE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/coal_coke"));
        singleTexture(ModItems.FIRESTONE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/firestone"));
        singleTexture(ModItems.PEPPER.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/pepper"));
        singleTexture(ModItems.PEPPER_SEEDS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/pepper_seeds"));
        singleTexture(ModItems.RAW_TITANIUM.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/raw_titanium"));
        singleTexture(ModItems.RUBY.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ruby"));
        singleTexture(ModItems.RUBY_AXE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ruby_axe"));
        singleTexture(ModItems.RUBY_BOOTS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ruby_boots"));
        singleTexture(ModItems.RUBY_CHESTPLATE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ruby_chestplate"));
        singleTexture(ModItems.RUBY_HELMET.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ruby_helmet"));
        singleTexture(ModItems.RUBY_HOE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ruby_hoe"));
        singleTexture(ModItems.RUBY_HORSE_ARMOR.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ruby_horse_armor"));
        singleTexture(ModItems.RUBY_LEGGINGS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ruby_leggings"));
        singleTexture(ModItems.RUBY_PICKAXE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ruby_pickaxe"));
        singleTexture(ModItems.RUBY_SHOVEL.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ruby_showel"));
        singleTexture(ModItems.RUBY_SWORD.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ruby_sword"));
        singleTexture(ModItems.SMART_BLOW_TORCH.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/smart_blow_torch"));
        singleTexture(ModItems.TITANIUM_AXE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/titanium_axe"));
        singleTexture(ModItems.TITANIUM_BOOTS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/titanium_boots"));
        singleTexture(ModItems.TITANIUM_CHESTPLATE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/titanium_chestplate"));
        singleTexture(ModBlocks.TITANIUM_DOOR.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/titanium_door"));
        singleTexture(ModItems.TITANIUM_HELMET.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/titanium_helmet"));
        singleTexture(ModItems.TITANIUM_HOE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/titanium_hoe"));
        singleTexture(ModItems.TITANIUM_HORSE_ARMOR.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/titanium_horse_armor"));
        singleTexture(ModItems.TITANIUM_INGOT.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/titanium"));
        singleTexture(ModItems.TITANIUM_LEGGINGS.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/titanium_leggings"));
        singleTexture(ModItems.TITANIUM_NUGGET.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/titanium_nugget"));
        singleTexture(ModItems.TITANIUM_PICKAXE.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/titanium_pickaxe"));
        singleTexture(ModItems.TITANIUM_SHOVEL.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/titanium_showel"));
        singleTexture(ModItems.TITANIUM_SWORD.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/titanium_sword"));
        singleTexture(ModItems.LIGHTNING_STRIKE_CURSE1.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/red_paper"));
        singleTexture(ModItems.LIGHTNING_STRIKE_CURSE2.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/red_paper"));
        singleTexture(ModItems.LIGHTNING_STRIKE_CURSE3.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/red_paper"));
        singleTexture(ModItems.LIGHTNING_STRIKE_CURSE4.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/red_paper"));
        singleTexture(ModItems.LIGHTNING_STRIKE_CURSE5.get().getRegistryName().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/red_paper"));

    }
}
