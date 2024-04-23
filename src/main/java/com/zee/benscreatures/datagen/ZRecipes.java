package com.zee.benscreatures.datagen;

import com.zee.benscreatures.MultiSmeltingOutputRecipeBuilder;
import com.zee.benscreatures.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;

import java.util.function.Consumer;

public class ZRecipes extends RecipeProvider {
    public ZRecipes(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        MultiSmeltingOutputRecipeBuilder.smelting(Ingredient.of(Registration.ANCIENT_STEEL_ORE_ITEM.get()),
                Registration.ANCIENT_STEEL_NUGGET.get(), 1.0f, 100, 4)
                .unlockedBy("has_ore", has(Registration.ANCIENT_STEEL_ORE_ITEM.get()))
                .save(consumer, "ancient_steel_nugget1");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.ANCIENT_STEEL_CHUNK.get()),
                        Registration.ANCIENT_STEEL_NUGGET.get(), 1.0f, 100)
                .unlockedBy("has_ore", has(Registration.ANCIENT_STEEL_ORE_ITEM.get()))
                .save(consumer, "ancient_steel_nugget2");

        ShapelessRecipeBuilder.shapeless(Registration.ANCIENT_STEEL_INGOT.get())
                .requires(Registration.ANCIENT_STEEL_NUGGET.get(), 9)
                .group("benscreatures")
                .unlockedBy("ancient", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.ANCIENT_STEEL_NUGGET.get()))
                .save(consumer, "ancient_steel_ingot1");

        ShapelessRecipeBuilder.shapeless(Registration.ANCIENT_STEEL_INGOT.get(), 9)
                .requires(Registration.ANCIENT_STEEL_BLOCK.get(), 1)
                .group("benscreatures")
                .unlockedBy("ancient", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.ANCIENT_STEEL_BLOCK.get()))
                .save(consumer, "ancient_steel_ingot2");

        ShapelessRecipeBuilder.shapeless(Registration.ANCIENT_STEEL_NUGGET.get(), 9)
                .requires(Registration.ANCIENT_STEEL_INGOT.get(), 1)
                .group("benscreatures")
                .unlockedBy("ancient", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.ANCIENT_STEEL_INGOT.get()))
                .save(consumer, "ancient_steel_nugget3");

        ShapelessRecipeBuilder.shapeless(Registration.ANCIENT_STEEL_BLOCK_ITEM.get())
                .requires(Registration.ANCIENT_STEEL_INGOT.get(), 9)
                .group("benscreatures")
                .unlockedBy("ancient", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.ANCIENT_STEEL_INGOT.get()))
                .save(consumer);
    }
}
