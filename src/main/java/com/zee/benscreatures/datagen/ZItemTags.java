package com.zee.benscreatures.datagen;

import com.zee.benscreatures.BensCreatures;
import com.zee.benscreatures.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ZItemTags extends ItemTagsProvider {
    public ZItemTags(DataGenerator gen, BlockTagsProvider blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen, blockTags, BensCreatures.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(){
        this.m_126548_(Tags.Items.ORES)
                .add(Registration.ANCIENT_STEEL_ORE_ITEM.get());
        this.m_126548_(Tags.Items.INGOTS)
                .add(Registration.ANCIENT_STEEL_INGOT.get());
        this.m_126548_(Tags.Items.NUGGETS)
                .add(Registration.ANCIENT_STEEL_NUGGET.get());
    }
}
