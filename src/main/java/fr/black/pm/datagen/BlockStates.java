package fr.black.pm.datagen;

import fr.black.pm.PremierMod;
import fr.black.pm.block.ModBlocks;
import fr.black.pm.tileEntities.ModTileEntities;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;


public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper helper){
        super(gen, PremierMod.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.TITANIUM_BLOCK.get());
        simpleBlock(ModBlocks.TITANIUM_STAIRS.get());
        simpleBlock(ModBlocks.TITANIUM_SLAB.get());
        simpleBlock(ModBlocks.TITANIUM_WALL.get());
        simpleBlock(ModBlocks.TITANIUM_ORE.get());
        simpleBlock(ModBlocks.TITANIUM_DOOR.get());
        simpleBlock(ModBlocks.TITANIUM_TRAPDOOR.get());
        simpleBlock(ModBlocks.TITANIUM_PRESSURE_PLATE.get());
        simpleBlock(ModBlocks.TITANIUM_BUTTON.get());
        simpleBlock(ModBlocks.SPEEDY_BLOCK.get());
        simpleBlock(ModBlocks.RUBY_ORE.get());
        simpleBlock(ModBlocks.RUBY_BLOCK.get());
        simpleBlock(ModBlocks.RUBY_STAIRS.get());
        simpleBlock(ModBlocks.RUBY_SLAB.get());
        simpleBlock(ModBlocks.RUBY_WALL.get());
        simpleBlock(ModBlocks.FIRESTONE_BLOCK.get());
        simpleBlock(ModBlocks.TEST_BLOCK.get());
        simpleBlock(ModBlocks.TOMATO_PLANT.get());
        simpleBlock(ModBlocks.PEPPER_PLANT.get());
        simpleBlock(ModBlocks.ORCHID.get());
        simpleBlock(ModBlocks.REDWOOD_LOG.get());
        simpleBlock(ModBlocks.REDWOOD_WOOD.get());
        simpleBlock(ModBlocks.STRIPPED_REDWOOD_LOG.get());
        simpleBlock(ModBlocks.STRIPPED_REDWOOD_WOOD.get());
        simpleBlock(ModBlocks.REDWOOD_PLANKS.get());
        simpleBlock(ModBlocks.REDWOOD_LEAVES.get());
        simpleBlock(ModBlocks.REDWOOD_SAPLING.get());
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
                .texture("single", modLoc("block/powergen/single_off"));
        BlockModelBuilder singleOn = models().getBuilder("block/powergen/singleon")
                .element().from(3,3,3).to(13,13,13).face(Direction.DOWN).texture("#single").end().end()
                .texture("single", modLoc("block/powergen/single_on"));

        MultiPartBlockStateBuilder bld = getMultipartBuilder(block);
        bld.part().modelFile(frame).addModel();

        BlockModelBuilder[] models = new BlockModelBuilder[] {singleOff, singleOn};
        for(int i=0; i<2; i++){
            bld.part().modelFile(models[i]).addModel().condition(BlockStateProperties.POWERED, i==1);
            bld.part().modelFile(models[i]).rotationX(180).addModel().condition(BlockStateProperties.POWERED, i==1);
            bld.part().modelFile(models[i]).rotationX(90).addModel().condition(BlockStateProperties.POWERED, i==1);
            bld.part().modelFile(models[i]).rotationX(270).addModel().condition(BlockStateProperties.POWERED, i==1);
            bld.part().modelFile(models[i]).rotationY(90).rotationX(90).addModel().condition(BlockStateProperties.POWERED, i==1);
            bld.part().modelFile(models[i]).rotationY(270).rotationX(90).addModel().condition(BlockStateProperties.POWERED, i==1);
        }
    }
}
