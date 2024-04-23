//package com.zee.benscreatures.datagen;
//
//import com.zee.benscreatures.BensCreatures;
//import net.minecraft.data.BuiltinRegistries;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.data.tags.TagsProvider;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.tags.TagManager;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraftforge.common.data.ExistingFileHelper;
//
//import java.nio.file.Path;
//
//public class ZBiomeTags extends TagsProvider<Biome> {
//    public ZBiomeTags(DataGenerator generator, ExistingFileHelper helper) {
//        super(generator, BuiltinRegistries.BIOME, BensCreatures.MODID, helper, TagManager.getTagDir(BuiltinRegistries.BIOME.key()).substring(5));
//    }
//
//    @Override
//    protected void addTags() {
//
//    }
//
//    @Override
//    protected Path getPath(ResourceLocation resourceLocation) {
//        return null;
//    }
//
//    @Override
//    public String getName() {
//        return "Ben's Creature's Tags";
//    }
//}
