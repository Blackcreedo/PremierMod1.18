package fr.black.pm.datagen;

import fr.black.pm.PremierMod;
import fr.black.pm.block.ModBlocks;
import fr.black.pm.block.custom.Cable;
import fr.black.pm.block.custom.TestBlock;
import fr.black.pm.tileEntities.ModTileEntities;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import static fr.black.pm.tileEntities.custom.oreGenerator.OreGeneratorModelLoader.ORE_GENERATOR_LOADER;
import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;


public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper helper){
        super(gen, PremierMod.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.TITANIUM_BLOCK.get());
        ResourceLocation titanium_block = new ResourceLocation("pm:block/titanium_block");
        stairsBlock((StairBlock) ModBlocks.TITANIUM_STAIRS.get(), titanium_block);
        slabBlock((SlabBlock) ModBlocks.TITANIUM_SLAB.get(), titanium_block, titanium_block);
        registerWall((WallBlock) ModBlocks.TITANIUM_WALL.get(), titanium_block, "titanium_wall");
        simpleBlock(ModBlocks.TITANIUM_ORE.get());
        ResourceLocation titanium_door_bottom = new ResourceLocation("pm:block/titanium_door_bottom");
        ResourceLocation titanium_door_top = new ResourceLocation("pm:block/titanium_door_top");
        doorBlock((DoorBlock) ModBlocks.TITANIUM_DOOR.get(), titanium_door_bottom, titanium_door_top);
        ResourceLocation titanium_trapdoor = new ResourceLocation("pm:block/titanium_trapdoor");
        trapdoorBlock((TrapDoorBlock) ModBlocks.TITANIUM_TRAPDOOR.get(), titanium_trapdoor, true);
        pressurePlateBlock((PressurePlateBlock) ModBlocks.TITANIUM_PRESSURE_PLATE.get(), titanium_block);
        registerButton((ButtonBlock) ModBlocks.TITANIUM_BUTTON.get(), titanium_block, "titanium_button");
        simpleBlock(ModBlocks.SPEEDY_BLOCK.get());
        simpleBlock(ModBlocks.RUBY_ORE.get());
        simpleBlock(ModBlocks.RUBY_BLOCK.get());
        ResourceLocation ruby_block = new ResourceLocation("pm:block/ruby_block");
        stairsBlock((StairBlock) ModBlocks.RUBY_STAIRS.get(), ruby_block);
        slabBlock((SlabBlock) ModBlocks.RUBY_SLAB.get(), ruby_block, ruby_block);
        registerWall((WallBlock) ModBlocks.RUBY_WALL.get(), ruby_block, "ruby_wall");
        simpleBlock(ModBlocks.FIRESTONE_BLOCK.get());

        registerTestBlock();

        registerPlantBlock(ModBlocks.TOMATO_PLANT.get(), "tomato");
        registerPlantBlock(ModBlocks.PEPPER_PLANT.get(), "pepper");

        simpleBlock(ModBlocks.ORCHID.get(), models().cross("block/orchid", new ResourceLocation("pm:block/orchid")));
        logBlock((RotatedPillarBlock) ModBlocks.REDWOOD_LOG.get());
        ResourceLocation redwood_log = new ResourceLocation("pm:block/redwood_log");
        axisBlock((RotatedPillarBlock) ModBlocks.REDWOOD_WOOD.get(), redwood_log, redwood_log);
        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_REDWOOD_LOG.get());
        ResourceLocation stripped_redwood_log = new ResourceLocation("pm:block/stripped_redwood_log");
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_REDWOOD_WOOD.get(), stripped_redwood_log, stripped_redwood_log);
        simpleBlock(ModBlocks.REDWOOD_PLANKS.get());
        simpleBlock(ModBlocks.REDWOOD_LEAVES.get());
        simpleBlock(ModBlocks.REDWOOD_SAPLING.get(), models().cross("block/redwood_sapling", new ResourceLocation("pm:block/redwood_sapling")));
        simpleBlock(ModTileEntities.LIGHTNING_CHANNELER.get());

        registerCable((Cable) ModBlocks.CABLE.get(), "cable", new ResourceLocation("block/texture_cable_test_1"), new ResourceLocation("block/texture_cable_test_2"));
        registerPowergen();
        registerOreGenerator();
    }

    private void registerButton(ButtonBlock block, ResourceLocation texture, String name){
        //register block
        buttonBlock(block, texture);
        //creating wall_inventory file
        models().buttonInventory(name + "_inventory", texture);
    }


    private void registerWall(WallBlock block, ResourceLocation texture, String name){
        //register block
        wallBlock(block, texture);
        //creating wall_inventory file
        models().wallInventory(name + "_inventory", texture);
    }

    private void registerTestBlock(){
        VariantBlockStateBuilder variant = getVariantBuilder(ModBlocks.TEST_BLOCK.get());
        variant.partialState().with(TestBlock.CLICKED, false).modelForState().modelFile(models().cubeAll("test_block_normal", new ResourceLocation("pm:block/test_block_normal"))).addModel()
                .partialState().with(TestBlock.CLICKED, true).modelForState().modelFile(models().cubeAll("test_block_clicked", new ResourceLocation("pm:block/test_block_clicked"))).addModel();
    }

    public ResourceLocation cropModel(Block block, String name, int age) {
        return new ResourceLocation(PremierMod.MOD_ID, "block/" + name + "_stage" + age);
    }

    private void registerPlantBlock(Block block, String name){
        VariantBlockStateBuilder variant = getVariantBuilder(block);
        variant.partialState().with(CropBlock.AGE, 0).modelForState().modelFile(models().cross("block/crop/"+name +"/"+ name + "_stage0", cropModel(block, name,0))).addModel()
                .partialState().with(CropBlock.AGE, 1).modelForState().modelFile(models().cross("block/crop/"+name +"/"+ name + "_stage1", cropModel(block, name,1))).addModel()
                .partialState().with(CropBlock.AGE, 2).modelForState().modelFile(models().cross("block/crop/"+name +"/"+ name + "_stage2", cropModel(block, name,2))).addModel()
                .partialState().with(CropBlock.AGE, 3).modelForState().modelFile(models().cross("block/crop/"+name +"/"+ name + "_stage3", cropModel(block, name,3))).addModel()
                .partialState().with(CropBlock.AGE, 4).modelForState().modelFile(models().cross("block/crop/"+name +"/"+ name + "_stage4", cropModel(block, name,4))).addModel()
                .partialState().with(CropBlock.AGE, 5).modelForState().modelFile(models().cross("block/crop/"+name +"/"+ name + "_stage5", cropModel(block, name,5))).addModel()
                .partialState().with(CropBlock.AGE, 6).modelForState().modelFile(models().cross("block/crop/"+name +"/"+ name + "_stage6", cropModel(block, name,6))).addModel()
                .partialState().with(CropBlock.AGE, 7).modelForState().modelFile(models().cross("block/crop/"+name +"/"+ name + "_stage7", cropModel(block, name,7))).addModel();
    }

    private void registerCable(Cable cable, String baseName, ResourceLocation side, ResourceLocation top){

        cableBlock(cable);
        //cableBlock(cable, models().singleTexture(baseName + "_top",modLoc(BLOCK_FOLDER + "/template_cable_top"), "cable", top),
        //models().singleTexture(baseName + "_side",modLoc(BLOCK_FOLDER + "/template_cable_side"), "cable", side));
        //register Inventory texture
        //models().singleTexture(baseName, modLoc(BLOCK_FOLDER + "/cable_inventory"), "cable", side);
    }

    private void cableBlock(Cable cable){

        BlockModelBuilder side = models().getBuilder("block/cable/side")
                .element().from(0,5,5).to(16,11,11).face(Direction.DOWN).texture("#single").end().end()
                .texture("single", modLoc("block/texture_cable_test_1"));

        BlockModelBuilder top = models().getBuilder("block/cable/top")
                .element().from(5,5,0).to(11,11,0).face(Direction.NORTH).texture("#single").end().end()
                .texture("single", modLoc("block/texture_cable_test_2"));

        MultiPartBlockStateBuilder builder = getMultipartBuilder(cable);

        builder.part().modelFile(side).addModel();
        builder.part().modelFile(side).rotationX(180).addModel();
        builder.part().modelFile(side).rotationX(90).addModel();
        builder.part().modelFile(side).rotationX(270).addModel();
        builder.part().modelFile(top).rotationY(90).addModel();
        builder.part().modelFile(top).rotationX(180).rotationY(90).addModel();
    }

    private void registerPowergen(){
        BlockModelBuilder dimCellFrame = models().getBuilder("block/powergen/main");
        dimCellFrame.parent(models().getExistingFile(mcLoc("cube")));

        floatingCube(dimCellFrame, 0f, 0f, 0f, 1f, 16f, 1f);
        floatingCube(dimCellFrame, 15f, 0f, 0f, 16f, 16f, 1f);
        floatingCube(dimCellFrame, 0f, 0f, 15f, 1f, 16f, 16f);
        floatingCube(dimCellFrame, 15f, 0f, 15f, 16f, 16f, 16f);

        floatingCube(dimCellFrame, 1f, 0f, 0f, 15f, 1f, 1f);
        floatingCube(dimCellFrame, 1f, 15f, 0f, 15f, 16f, 1f);
        floatingCube(dimCellFrame, 1f, 0f, 15f, 15f, 1f, 16f);
        floatingCube(dimCellFrame, 1f, 15f, 15f, 15f, 16f, 16f);

        floatingCube(dimCellFrame, 0f, 0f, 1f, 1f, 1f, 15f);
        floatingCube(dimCellFrame, 15f, 0f, 1f, 16f, 1f, 15f);
        floatingCube(dimCellFrame, 0f, 15f, 1f, 1f, 16f, 15f);
        floatingCube(dimCellFrame, 15f, 15f, 1f, 16f, 16f, 15f);

        floatingCube(dimCellFrame, 1f, 1f, 1f, 15f, 15f, 15f);

        dimCellFrame.texture("window", modLoc("block/powergen_window"));
        dimCellFrame.texture("particle", modLoc("block/powergen_off"));

        createPowergenModel(ModTileEntities.POWERGEN.get(), dimCellFrame);
    }

    private void floatingCube(BlockModelBuilder builder, float fx, float fy, float fz, float tx, float ty, float tz) {
        builder.element().from(fx, fy, fz).to(tx, ty, tz).allFaces((direction, faceBuilder) -> faceBuilder.texture("#window")).end();
    }

    private void createPowergenModel(Block block, BlockModelBuilder frame){
        BlockModelBuilder singleOff = models().getBuilder("block/powergen/singleoff")
                .element().from(3,3,3).to(13,13,13).face(Direction.DOWN).texture("#single").end().end()
                .texture("single", modLoc("block/powergen_off"));
        BlockModelBuilder singleOn = models().getBuilder("block/powergen/singleon")
                .element().from(3,3,3).to(13,13,13).face(Direction.DOWN).texture("#single").end().end()
                .texture("single", modLoc("block/powergen_on"));

        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        builder.part().modelFile(frame).addModel();

        BlockModelBuilder[] models = new BlockModelBuilder[] {singleOff, singleOn};
        for(int i=0; i<2; i++){
            builder.part().modelFile(models[i]).addModel().condition(BlockStateProperties.POWERED, i==1);
            builder.part().modelFile(models[i]).rotationX(180).addModel().condition(BlockStateProperties.POWERED, i==1);
            builder.part().modelFile(models[i]).rotationX(90).addModel().condition(BlockStateProperties.POWERED, i==1);
            builder.part().modelFile(models[i]).rotationX(270).addModel().condition(BlockStateProperties.POWERED, i==1);
            builder.part().modelFile(models[i]).rotationY(90).rotationX(90).addModel().condition(BlockStateProperties.POWERED, i==1);
            builder.part().modelFile(models[i]).rotationY(270).rotationX(90).addModel().condition(BlockStateProperties.POWERED, i==1);
        }
    }

    private void registerOreGenerator(){
        // Using CustomLoaderBuilder we can define a json file for our model that will use our baked model
        BlockModelBuilder generatorModel = models().getBuilder(ModTileEntities.ORE_GENERATOR.get().getRegistryName().getPath())
                .parent(models().getExistingFile(mcLoc("cube")))
                .customLoader((blockModelBuilder, helper) -> new CustomLoaderBuilder<BlockModelBuilder>(ORE_GENERATOR_LOADER, blockModelBuilder, helper){}).end();
        directionalBlock(ModTileEntities.ORE_GENERATOR.get(), generatorModel);
    }

}
