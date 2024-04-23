package com.zee.benscreatures.datagen;

import com.zee.benscreatures.BensCreatures;
import com.zee.benscreatures.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ZBlockStates extends BlockStateProvider {
    public ZBlockStates(DataGenerator gen, ExistingFileHelper helper) { super(gen, BensCreatures.MODID, helper); }

    @Override
    protected void registerStatesAndModels(){
        simpleBlock(Registration.ANCIENT_STEEL_ORE.get());
        simpleBlock(Registration.ANCIENT_STEEL_BLOCK.get());
    }
}
