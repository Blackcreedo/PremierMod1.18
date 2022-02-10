package fr.black.pm.world;

import fr.black.pm.PremierMod;
import fr.black.pm.world.gen.ModFlowerGeneration;
import fr.black.pm.world.gen.ModOreGeneration;
import fr.black.pm.world.gen.ModTreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PremierMod.MOD_ID)
public class WorldGenerationEvents {

    @SubscribeEvent
    public static void ModWorldGeneration(final BiomeLoadingEvent event){
        ModOreGeneration.generateOres(event);
        ModTreeGeneration.generateTrees(event);
        ModFlowerGeneration.generateFlowers(event);
    }

}
