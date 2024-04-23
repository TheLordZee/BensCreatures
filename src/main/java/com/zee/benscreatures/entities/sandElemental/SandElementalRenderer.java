package com.zee.benscreatures.entities.sandElemental;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SandElementalRenderer extends GeoEntityRenderer<SandElemental> {
    public SandElementalRenderer(EntityRendererProvider.Context context) {
        super(context, new SandElementalGeoModel());
    }

}
