package fr.black.pm.world.features.tree;

import fr.black.pm.world.features.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

public class RedwoodTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random p_60014_, boolean p_60015_) {
        return (ConfiguredFeature<TreeConfiguration, ?>)ModConfiguredFeatures.REDWOOD;
    }
}
