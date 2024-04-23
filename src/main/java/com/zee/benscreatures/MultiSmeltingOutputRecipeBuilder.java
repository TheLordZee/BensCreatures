package com.zee.benscreatures;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class MultiSmeltingOutputRecipeBuilder  implements RecipeBuilder {
    private final Item result;
    private final Ingredient ingredient;

    private final int count;
    private final float experience;
    private final int cookingTime;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    @Nullable
    private String group;
    private final SimpleCookingSerializer<?> serializer;

    private MultiSmeltingOutputRecipeBuilder(ItemLike p_126243_, Ingredient p_126244_, float p_126245_, int p_126246_, int count, SimpleCookingSerializer<?> p_126247_) {
        this.result = p_126243_.asItem();
        this.ingredient = p_126244_;
        this.experience = p_126245_;
        this.cookingTime = p_126246_;
        this.count = count;
        this.serializer = p_126247_;
    }

    public static MultiSmeltingOutputRecipeBuilder cooking(Ingredient p_126249_, ItemLike p_126250_, float p_126251_, int p_126252_, int count, SimpleCookingSerializer<?> p_126253_) {
        return new MultiSmeltingOutputRecipeBuilder(p_126250_, p_126249_, p_126251_, p_126252_, count, p_126253_);
    }

    public static MultiSmeltingOutputRecipeBuilder campfireCooking(Ingredient p_176785_, ItemLike p_176786_, float p_176787_, int p_176788_, int count) {
        return cooking(p_176785_, p_176786_, p_176787_, p_176788_, count, RecipeSerializer.CAMPFIRE_COOKING_RECIPE);
    }

    public static MultiSmeltingOutputRecipeBuilder blasting(Ingredient p_126268_, ItemLike p_126269_, float p_126270_, int p_126271_, int count) {
        return cooking(p_126268_, p_126269_, p_126270_, p_126271_, count, RecipeSerializer.BLASTING_RECIPE);
    }

    public static MultiSmeltingOutputRecipeBuilder smelting(Ingredient p_126273_, ItemLike p_126274_, float p_126275_, int p_126276_, int count) {
        return cooking(p_126273_, p_126274_, p_126275_, p_126276_, count, RecipeSerializer.SMELTING_RECIPE);
    }

    public static MultiSmeltingOutputRecipeBuilder smoking(Ingredient p_176797_, ItemLike p_176798_, float p_176799_, int p_176800_, int count) {
        return cooking(p_176797_, p_176798_, p_176799_, p_176800_, count, RecipeSerializer.SMOKING_RECIPE);
    }

    public MultiSmeltingOutputRecipeBuilder unlockedBy(String p_126255_, CriterionTriggerInstance p_126256_) {
        this.advancement.addCriterion(p_126255_, p_126256_);
        return this;
    }

    public MultiSmeltingOutputRecipeBuilder group(@Nullable String p_176795_) {
        this.group = p_176795_;
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    public void save(Consumer<FinishedRecipe> p_126263_, ResourceLocation p_126264_) {
        this.ensureValid(p_126264_);
        this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_126264_)).rewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(p_126264_)).requirements(RequirementsStrategy.OR);
        String var10004 = this.group == null ? "" : this.group;
        Ingredient var10005 = this.ingredient;
        Item var10006 = this.result;
        float var10007 = this.experience;
        int var10010 = this.count;
        int var10008 = this.cookingTime;
        Advancement.Builder var10009 = this.advancement;
        String var10012 = p_126264_.getNamespace();
        String var10013 = this.result.getItemCategory().getRecipeFolderName();
        p_126263_.accept(new MultiSmeltingOutputRecipeBuilder.Result(p_126264_, var10004, var10005, var10006, var10007, var10008, var10009, new ResourceLocation(var10012, "recipes/" + var10013 + "/" + p_126264_.getPath()), this.serializer, var10010));
    }

    private void ensureValid(ResourceLocation p_126266_) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + p_126266_);
        }
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final String group;
        private final Ingredient ingredient;
        private final Item result;

        private final int count;
        private final float experience;
        private final int cookingTime;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final RecipeSerializer<? extends AbstractCookingRecipe> serializer;

        public Result(ResourceLocation p_126287_, String p_126288_, Ingredient p_126289_, Item p_126290_, float p_126291_, int p_126292_, Advancement.Builder p_126293_, ResourceLocation p_126294_, RecipeSerializer<? extends AbstractCookingRecipe> p_126295_, int count) {
            this.id = p_126287_;
            this.group = p_126288_;
            this.ingredient = p_126289_;
            this.result = p_126290_;
            this.experience = p_126291_;
            this.cookingTime = p_126292_;
            this.advancement = p_126293_;
            this.advancementId = p_126294_;
            this.serializer = p_126295_;
            this.count = count;
        }

        public void serializeRecipeData(JsonObject p_126297_) {
            if (!this.group.isEmpty()) {
                p_126297_.addProperty("group", this.group);
            }

            p_126297_.add("ingredient", this.ingredient.toJson());
            p_126297_.addProperty("result", Registry.ITEM.getKey(this.result).toString());
            p_126297_.addProperty("experience", this.experience);
            p_126297_.addProperty("cookingtime", this.cookingTime);
            JsonObject $$3 = new JsonObject();
            $$3.addProperty("item", Registry.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                $$3.addProperty("count", this.count);
            }

            p_126297_.add("result", $$3);
        }

        public RecipeSerializer<?> getType() {
            return this.serializer;
        }

        public ResourceLocation getId() {
            return this.id;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
