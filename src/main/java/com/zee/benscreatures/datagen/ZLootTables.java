package com.zee.benscreatures.datagen;

import com.zee.benscreatures.setup.Registration;
import net.minecraft.data.DataGenerator;

public class ZLootTables extends BaseLootTableProvider {
    public ZLootTables(DataGenerator gen) {
        super(gen);
    }

   @Override
    protected void addTables(){
//       createSilkTouchTable("mysterious_ore_overworld", Registration.MYSTERIOUS_ORE_OVERWORLD.get(), Registration.RAW_MYSTERIOUS_CHUNK.get(), 1, 3)
        lootTables.put(Registration.ANCIENT_STEEL_ORE.get(), createSilkTouchTable("ancient_steel_ore", Registration.ANCIENT_STEEL_ORE.get(), Registration.ANCIENT_STEEL_CHUNK.get(), 1,4));
        lootTables.put(Registration.ANCIENT_STEEL_BLOCK.get(), createSimpleTable("ancient_steel_block", Registration.ANCIENT_STEEL_BLOCK.get()));
    }
}
