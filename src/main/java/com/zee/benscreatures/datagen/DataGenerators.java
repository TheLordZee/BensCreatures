package com.zee.benscreatures.datagen;

import com.zee.benscreatures.BensCreatures;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = BensCreatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()){
            generator.addProvider(new ZRecipes(generator));
            generator.addProvider(new ZLootTables(generator));
            ZBlockTags blockTags = new ZBlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new ZItemTags(generator, blockTags, event.getExistingFileHelper()));
        }
        if(event.includeClient()){
            generator.addProvider(new ZBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new ZItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new ZLanguageProvider(generator, "en_us"));
        }
    }
}
