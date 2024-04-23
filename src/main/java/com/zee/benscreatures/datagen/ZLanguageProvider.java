package com.zee.benscreatures.datagen;

import com.zee.benscreatures.BensCreatures;
import com.zee.benscreatures.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import static com.zee.benscreatures.setup.ModSetup.TAB_NAME;

public class ZLanguageProvider extends LanguageProvider {

    public ZLanguageProvider(DataGenerator gen, String locale) {
        super(gen, BensCreatures.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + TAB_NAME, "Ben's Creatures");

        add(Registration.ANCIENT_STEEL_ORE.get(), "Ancient Steel Ore");
        add(Registration.ANCIENT_STEEL_NUGGET.get(), "Ancient Steel Nugget");
        add(Registration.ANCIENT_STEEL_INGOT.get(), "Ancient Steel Ingot");
        add(Registration.ANCIENT_STEEL_CHUNK.get(), "Ancient Steel Chunk");
        add(Registration.ANCIENT_STEEL_BLOCK.get(), "Ancient Steel Block");
    }
}
