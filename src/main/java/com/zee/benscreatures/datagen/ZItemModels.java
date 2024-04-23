package com.zee.benscreatures.datagen;

import com.zee.benscreatures.BensCreatures;
import com.zee.benscreatures.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ZItemModels extends ItemModelProvider {

    public ZItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, BensCreatures.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(Registration.ANCIENT_STEEL_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/ancient_steel_ore"));
        withExistingParent(Registration.ANCIENT_STEEL_BLOCK_ITEM.get().getRegistryName().getPath(), modLoc("block/ancient_steel_block"));

        withExistingParent(Registration.SAND_ELEMENTAL_EGG.get().getRegistryName().getPath(), mcLoc("item/template_spawn_egg"));

        singleTexture(Registration.ANCIENT_STEEL_NUGGET.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/ancient_steel_nugget"));
        singleTexture(Registration.ANCIENT_STEEL_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/ancient_steel_ingot"));
        singleTexture(Registration.ANCIENT_STEEL_CHUNK.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/ancient_steel_chunk"));
    }
}
