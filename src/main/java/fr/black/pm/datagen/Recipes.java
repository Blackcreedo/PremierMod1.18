package fr.black.pm.datagen;

import fr.black.pm.block.ModBlocks;
import fr.black.pm.item.ModItems;
import fr.black.pm.tileEntities.ModTileEntities;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider implements IConditionBuilder {

    public Recipes(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer) {

        blocks(finishedRecipeConsumer, ModBlocks.TITANIUM_BLOCK.get(), ModItems.TITANIUM_INGOT.get());
        stairs(finishedRecipeConsumer, ModBlocks.TITANIUM_STAIRS.get(), ModItems.TITANIUM_INGOT.get());
        slab(finishedRecipeConsumer, ModBlocks.TITANIUM_SLAB.get(), ModItems.TITANIUM_INGOT.get());
        wall(finishedRecipeConsumer, ModBlocks.TITANIUM_WALL.get(), ModItems.TITANIUM_INGOT.get());

        ShapedRecipeBuilder.shaped(ModBlocks.TITANIUM_DOOR.get())
                .define('T', ModItems.TITANIUM_INGOT.get())
                .pattern("TT")
                .pattern("TT")
                .pattern("TT").unlockedBy("has_material", has(ModItems.TITANIUM_INGOT.get()))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModBlocks.TITANIUM_TRAPDOOR.get())
                .define('T', ModItems.TITANIUM_INGOT.get())
                .pattern("TT")
                .pattern("TT").unlockedBy("has_material", has(ModItems.TITANIUM_INGOT.get()))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModBlocks.TITANIUM_PRESSURE_PLATE.get())
                .define('T', ModItems.TITANIUM_INGOT.get())
                .pattern("TT").unlockedBy("has_material", has(ModItems.TITANIUM_INGOT.get()))
                .save(finishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(ModBlocks.TITANIUM_BUTTON.get())
                .requires(ModItems.TITANIUM_INGOT.get())
                .unlockedBy("has_material", has(ModItems.TITANIUM_INGOT.get()))
                .save(finishedRecipeConsumer);;

        blocks(finishedRecipeConsumer, ModBlocks.RUBY_BLOCK.get(), ModItems.RUBY.get());
        stairs(finishedRecipeConsumer, ModBlocks.RUBY_STAIRS.get(), ModItems.RUBY.get());
        slab(finishedRecipeConsumer, ModBlocks.RUBY_SLAB.get(), ModItems.RUBY.get());
        wall(finishedRecipeConsumer, ModBlocks.RUBY_WALL.get(), ModItems.RUBY.get());
        blocks(finishedRecipeConsumer, ModBlocks.FIRESTONE_BLOCK.get(), ModItems.FIRESTONE.get());
        planksFromLog(finishedRecipeConsumer, ModBlocks.REDWOOD_PLANKS.get(), ItemTags.ACACIA_LOGS);
        woodFromLogs(finishedRecipeConsumer, ModBlocks.REDWOOD_WOOD.get(), ModBlocks.REDWOOD_LOG.get());
        woodFromLogs(finishedRecipeConsumer, ModBlocks.STRIPPED_REDWOOD_WOOD.get(), ModBlocks.STRIPPED_REDWOOD_LOG.get());

        ShapedRecipeBuilder.shaped(ModTileEntities.POWERGEN.get())
                .define('m', ModItems.TITANIUM_INGOT.get())
                .define('#', Tags.Items.DUSTS_REDSTONE)
                .define('x', Tags.Items.INGOTS_IRON)
                .pattern("mmm")
                .pattern("x#x")
                .pattern("#x#")
                .unlockedBy("has_material", has(ModItems.TITANIUM_INGOT.get())).save(finishedRecipeConsumer);
    }


    private static void blocks(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike craft, ItemLike material) {
        ShapedRecipeBuilder.shaped(craft)
                .define('#', material)
                .pattern("###")
                .pattern("###")
                .pattern("###").unlockedBy("has_material", has(material))
                .save(finishedRecipeConsumer);
    }

    private static void stairs(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike craft, ItemLike material) {
        ShapedRecipeBuilder.shaped(craft)
                .define('#', material)
                .pattern("#")
                .pattern("##")
                .pattern("###").unlockedBy("has_material", has(material))
                .save(finishedRecipeConsumer);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), craft)
                .unlockedBy("has_material", has(material)).save(finishedRecipeConsumer);
    }

    private static void slab(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike craft, ItemLike material) {
        ShapedRecipeBuilder.shaped(craft)
                .define('#', material)
                .pattern("###").unlockedBy("has_material", has(material))
                .save(finishedRecipeConsumer);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), craft)
                .unlockedBy("has_material", has(material)).save(finishedRecipeConsumer);
    }

    private static void Walls(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike craft, ItemLike material) {
        ShapedRecipeBuilder.shaped(craft)
                .define('#', material)
                .pattern("###")
                .pattern("###").unlockedBy("has_material", has(material))
                .save(finishedRecipeConsumer);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), craft)
                .unlockedBy("has_material", has(material)).save(finishedRecipeConsumer);
    }

    private static void planksFromLog(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike itemLike, Tag<Item> itemTag) {
        ShapelessRecipeBuilder.shapeless(itemLike, 4).requires(itemTag).group("planks").unlockedBy("has_log", has(itemTag)).save(finishedRecipeConsumer);
    }

    private static void woodFromLogs(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike itemLike, ItemLike itemLike1) {
        ShapedRecipeBuilder.shaped(itemLike, 3).define('#', itemLike1).pattern("##").pattern("##").group("bark").unlockedBy("has_log", has(itemLike1)).save(finishedRecipeConsumer);
    }
}
