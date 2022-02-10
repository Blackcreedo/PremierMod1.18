package fr.black.pm.block.custom;

import fr.black.pm.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;


public class PepperPlantBlock extends CropBlock {
    public PepperPlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.PEPPER_SEEDS.get();
    }
}