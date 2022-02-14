package fr.black.pm.datagen;


import fr.black.pm.PremierMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = PremierMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        if(event.includeServer()){
            //generator.addProvider(new Recipes(generator));
            //generator.addProvider(new LootTables(generator));
            GenBlockTags blockTags = new GenBlockTags(generator, event.getExistingFileHelper());
            GenItemTags itemTags = new GenItemTags(generator,blockTags, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(itemTags);
        }
        if(event.includeClient()){
            generator.addProvider(new BlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new ItemModels(generator, event.getExistingFileHelper()));
        }
    }


}
