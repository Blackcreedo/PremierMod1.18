package fr.black.pm.tileEntities.custom.oreGenerator;

import net.minecraftforge.common.ForgeConfigSpec;

public class OreGeneratorConfig {

    public static ForgeConfigSpec.IntValue ENERGY_GENERATE;
    public static ForgeConfigSpec.IntValue COLLECTING_DELAY;
    public static ForgeConfigSpec.IntValue INGOTS_PER_ORE;
    public static ForgeConfigSpec.IntValue ENERGY_CAPACITY;
    public static ForgeConfigSpec.IntValue ENERGY_RECEIVE;



    public static void registerServerConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
        ENERGY_GENERATE = SERVER_BUILDER
                .comment("How much energy is needed to process one ore block")
                .defineInRange("generate", 500, 1, Integer.MAX_VALUE);

        COLLECTING_DELAY = SERVER_BUILDER
                .comment("Delay (in ticks) before collecting items")
                .defineInRange("collectingDelay", 10, 1, Integer.MAX_VALUE);

        INGOTS_PER_ORE = SERVER_BUILDER
                .comment("How much ingots are required to process one ore block")
                .defineInRange("collectingDelay", 10, 1, Integer.MAX_VALUE);

        ENERGY_CAPACITY = SERVER_BUILDER
                .comment("The energy capacity of th OreGenerator")
                .defineInRange("collectingDelay", 100000, 1, Integer.MAX_VALUE);

        ENERGY_RECEIVE = SERVER_BUILDER
                .comment("How much energy the OreGenerator can receive")
                .defineInRange("collectingDelay", 1000, 1, Integer.MAX_VALUE);
    }


}
