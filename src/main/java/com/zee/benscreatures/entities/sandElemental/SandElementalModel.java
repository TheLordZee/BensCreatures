package com.zee.benscreatures.entities.sandElemental;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zee.benscreatures.BensCreatures;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class SandElementalModel extends EntityModel<SandElemental> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BensCreatures.MODID, "sand_elemental"), "body");
	private final ModelPart body;

	public SandElementalModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -35.0F, 0.0F));

		PartDefinition bone2 = head.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 82).addBox(-13.0F, -9.0F, -10.0F, 25.0F, 18.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 0.0F));

		PartDefinition bone = head.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(158, 53).addBox(-10.0F, -7.0F, -6.0F, 20.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -10.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-17.0F, -34.0F, -13.0F, 33.0F, 16.0F, 26.0F, new CubeDeformation(0.0F))
		.texOffs(0, 43).addBox(-15.0F, -18.0F, -11.0F, 29.0F, 16.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(83, 62).addBox(-13.0F, -2.0F, -10.0F, 25.0F, 14.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(49, 156).addBox(-11.0F, 12.0F, -9.0F, 21.0F, 10.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(160, 97).addBox(-9.0F, 22.0F, -8.0F, 17.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone6 = arms.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offset(-17.0F, -28.0F, 0.0F));

		PartDefinition bone8 = bone6.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(119, 0).addBox(-16.0F, -7.0F, -9.0F, 16.0F, 34.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition bone7 = bone6.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(91, 97).addBox(-8.0F, 0.0F, -9.0F, 16.0F, 34.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 28.0F, 0.0F));

		PartDefinition bone4 = arms.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(17.0F, -28.0F, 0.0F));

		PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(142, 132).addBox(-1.0F, -6.0F, -9.0F, 15.0F, 34.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone9 = bone4.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(0, 121).addBox(-9.0F, 0.0F, -9.0F, 15.0F, 34.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 28.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}


	@Override
	public void setupAnim(SandElemental sandElemental, float v, float v1, float v2, float v3, float v4) {

	}
}