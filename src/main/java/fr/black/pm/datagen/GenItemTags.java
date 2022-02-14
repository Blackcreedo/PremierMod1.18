package fr.black.pm.datagen;

import fr.black.pm.PremierMod;
import fr.black.pm.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class GenItemTags extends ItemTagsProvider {
    public GenItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(generator, blockTags, PremierMod.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(Tags.Items.ORES)
                .add(ModBlocks.TITANIUM_ORE.get().asItem())
                .add(ModBlocks.RUBY_ORE.get().asItem());
    }

    @Override
    public String getName() {
        return "Tutorial Tags";
    }
}
