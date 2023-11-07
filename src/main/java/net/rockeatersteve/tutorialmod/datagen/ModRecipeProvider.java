package net.rockeatersteve.tutorialmod.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.rockeatersteve.tutorialmod.TutorialMod;
import net.rockeatersteve.tutorialmod.block.ModBlocks;
import net.rockeatersteve.tutorialmod.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> RUBY_SMELTABLES = List.of(ModItems.RAW_RUBY.get(), ModBlocks.RUBY_ORE.get(), ModBlocks.DEEPSLATE_RUBY_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RUBY_BLOCK.get())
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', ModItems.RUBY.get())
                .unlockedBy("has_ruby", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModItems.RUBY.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RUBY.get(), 9)
                .requires(ModBlocks.RUBY_BLOCK.get())
                .unlockedBy("has_ruby_block", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.RUBY_BLOCK.get()).build()))
                .save(pWriter);

        // nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.RUBY.get(), RecipeCategory.MISC, ModBlocks.RUBY_BLOCK.get()); macht das gleiche wie oben
        oreBlasting(pWriter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY.get(), 20f, 1000, "ruby");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DETECTOR.get())
                .pattern(" RN")
                .pattern(" DR")
                .pattern("R  ")
                .define('R', ModItems.REINFORCED_STICK.get())
                .define('N', Items.NETHERITE_SCRAP)
                .define('D', Items.DIAMOND)
                .unlockedBy("has_reinforced_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.REINFORCED_STICK.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SPEAR.get())
                .pattern("  X")
                .pattern(" R ")
                .pattern("R  ")
                .define('R', ModItems.REINFORCED_STICK.get())
                .define('X', ModItems.RUBY.get())
                .unlockedBy("has_reinforced_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.REINFORCED_STICK.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SCYTHE.get())
                .pattern(" XX")
                .pattern("X R")
                .pattern(" R ")
                .define('X', ModItems.RUBY.get())
                .define('R', ModItems.REINFORCED_STICK.get())
                .unlockedBy("has_reinforced_stick", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.REINFORCED_STICK.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REINFORCED_STICK.get())
                .pattern("X")
                .pattern("X")
                .define('X', Items.NETHERITE_INGOT)
                .unlockedBy("has_netherite_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(Items.NETHERITE_INGOT).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CHOCOLATE_CAKE.get())
                .pattern("XCV")
                .define('X', Items.COCOA_BEANS)
                .define('C', Items.SUGAR)
                .define('V', Items.EGG)
                .unlockedBy("has_EGG", inventoryTrigger(ItemPredicate.Builder.item().of(Items.EGG).build()))
                .save(pWriter);

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, TutorialMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
