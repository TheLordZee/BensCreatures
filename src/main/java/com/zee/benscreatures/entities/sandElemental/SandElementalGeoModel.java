package com.zee.benscreatures.entities.sandElemental;

import com.zee.benscreatures.BensCreatures;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SandElementalGeoModel extends AnimatedGeoModel<SandElemental> {
    private static final ResourceLocation modelResource = new ResourceLocation(BensCreatures.MODID, "models/geo/Desert_Elemental_g.geo.json");
    private static final ResourceLocation textureResource = new ResourceLocation(BensCreatures.MODID, "textures/entity/sand_elemental.png");
    private static final ResourceLocation animationResource = new ResourceLocation(BensCreatures.MODID, "animations/sandelemental.animation.json");

    @Override
    public ResourceLocation getModelLocation(SandElemental object) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureLocation(SandElemental object) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SandElemental object) {
        return animationResource;
    }
}
