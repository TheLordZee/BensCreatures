package com.zee.benscreatures.datagen;

import com.zee.benscreatures.BensCreatures;
import com.zee.benscreatures.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ZBlockTags extends BlockTagsProvider {
    public ZBlockTags(DataGenerator gen, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen, BensCreatures.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(){
        this.m_126548_(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.ANCIENT_STEEL_ORE.get())
                .add(Registration.ANCIENT_STEEL_BLOCK.get());
        this.m_126548_(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(Registration.ANCIENT_STEEL_ORE.get())
                .add(Registration.ANCIENT_STEEL_BLOCK.get());
        this.m_126548_(Tags.Blocks.ORES)
                .add(Registration.ANCIENT_STEEL_ORE.get());
    }
}
