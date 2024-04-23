package com.zee.benscreatures.entities.sandElemental;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.BusBuilder;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.EnumSet;

public class SandElemental extends Monster implements IAnimatable {

    protected static final AnimationBuilder IDLE_ANIM = new AnimationBuilder().loop("i");
    protected static final AnimationBuilder MOVE_ANIM = new AnimationBuilder().loop("slow moving");

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    private float allowedHeightOffset = 0.5F;
    private int nextHeightOffsetChangeTick;
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID;
    public SandElemental(EntityType<? extends SandElemental> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.LAVA, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
        this.xpReward = 10;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(4, new SandElemental.SandElementalAttackGoal(this));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[0]));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void registerControllers(final AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "Idle", 5, this::idleAnimController));
        data.addAnimationController(new AnimationController<>(this, "Moving", 5, this::moveAnimController));
    }

    protected <E extends SandElemental> PlayState idleAnimController(final AnimationEvent<E> event) {
        if (!event.isMoving()) {
            event.getController().setAnimation(IDLE_ANIM);

            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }
    protected <E extends SandElemental> PlayState moveAnimController(final AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(MOVE_ANIM);

            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 6.0).add(Attributes.MOVEMENT_SPEED, 0.23000000417232513).add(Attributes.FOLLOW_RANGE, 48.0);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.BLAZE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.BLAZE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.BLAZE_DEATH;
    }

    public float getBrightness() {
        return 1.0F;
    }

    public void aiStep() {
        if (!this.onGround && this.getDeltaMovement().y < 0.0) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6, 1.0));
        }

        if (this.level.isClientSide) {
            if (this.random.nextInt(24) == 0 && !this.isSilent()) {
                this.level.playLocalSound(this.getX() + 0.5, this.getY() + 0.5, this.getZ() + 0.5, SoundEvents.BLAZE_BURN, this.getSoundSource(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
            }

            for(int $$0 = 0; $$0 < 2; ++$$0) {
                this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5), 0.0, 0.0, 0.0);
            }
        }

        super.aiStep();
    }

    public boolean isSensitiveToWater() {
        return true;
    }

    protected void customServerAiStep() {
        --this.nextHeightOffsetChangeTick;
        if (this.nextHeightOffsetChangeTick <= 0) {
            this.nextHeightOffsetChangeTick = 100;
            this.allowedHeightOffset = 0.5F + (float)this.random.nextGaussian() * 3.0F;
        }

        LivingEntity $$0 = this.getTarget();
        if ($$0 != null && $$0.getEyeY() > this.getEyeY() + (double)this.allowedHeightOffset && this.canAttack($$0)) {
            Vec3 $$1 = this.getDeltaMovement();
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, (0.30000001192092896 - $$1.y) * 0.30000001192092896, 0.0));
            this.hasImpulse = true;
        }

        super.customServerAiStep();
    }

    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    public boolean isOnFire() {
        return this.isCharged();
    }

    private boolean isCharged() {
        return ((Byte)this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    void setCharged(boolean pCharged) {
        byte $$1 = (Byte)this.entityData.get(DATA_FLAGS_ID);
        if (pCharged) {
            $$1 = (byte)($$1 | 1);
        } else {
            $$1 &= -2;
        }

        this.entityData.set(DATA_FLAGS_ID, $$1);
    }

    static {
        DATA_FLAGS_ID = SynchedEntityData.defineId(SandElemental.class, EntityDataSerializers.BYTE);
    }



    static class SandElementalAttackGoal extends Goal {
        private final SandElemental sandElemental;
        private int attackStep;
        private int attackTime;
        private int lastSeen;

        public SandElementalAttackGoal(SandElemental pSandElemental) {
            this.sandElemental = pSandElemental;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity $$0 = this.sandElemental.getTarget();
            return $$0 != null && $$0.isAlive() && this.sandElemental.canAttack($$0);
        }

        public void start() {
            this.attackStep = 0;
        }

        public void stop() {
            this.sandElemental.setCharged(false);
            this.lastSeen = 0;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            --this.attackTime;
            LivingEntity $$0 = this.sandElemental.getTarget();
            if ($$0 != null) {
                boolean $$1 = this.sandElemental.getSensing().hasLineOfSight($$0);
                if ($$1) {
                    this.lastSeen = 0;
                } else {
                    ++this.lastSeen;
                }

                double $$2 = this.sandElemental.distanceToSqr($$0);
                if ($$2 < 4.0) {
                    if (!$$1) {
                        return;
                    }

                    if (this.attackTime <= 0) {
                        this.attackTime = 20;
                        this.sandElemental.doHurtTarget($$0);
                    }

                    this.sandElemental.getMoveControl().setWantedPosition($$0.getX(), $$0.getY(), $$0.getZ(), 1.0);
                } else if ($$2 < this.getFollowDistance() * this.getFollowDistance() && $$1) {
                    double $$3 = $$0.getX() - this.sandElemental.getX();
                    double $$4 = $$0.getY(0.5) - this.sandElemental.getY(0.5);
                    double $$5 = $$0.getZ() - this.sandElemental.getZ();
                    if (this.attackTime <= 0) {
                        ++this.attackStep;
                        if (this.attackStep == 1) {
                            this.attackTime = 60;
                            this.sandElemental.setCharged(true);
                        } else if (this.attackStep <= 4) {
                            this.attackTime = 6;
                        } else {
                            this.attackTime = 100;
                            this.attackStep = 0;
                            this.sandElemental.setCharged(false);
                        }

                        if (this.attackStep > 1) {
                            double $$6 = Math.sqrt(Math.sqrt($$2)) * 0.5;
                            if (!this.sandElemental.isSilent()) {
                                this.sandElemental.level.levelEvent((Player)null, 1018, this.sandElemental.blockPosition(), 0);
                            }

                            for(int $$7 = 0; $$7 < 1; ++$$7) {
                                SmallFireball $$8 = new SmallFireball(this.sandElemental.level, this.sandElemental, $$3 + this.sandElemental.getRandom().nextGaussian() * $$6, $$4, $$5 + this.sandElemental.getRandom().nextGaussian() * $$6);
                                $$8.setPos($$8.getX(), this.sandElemental.getY(0.5) + 0.5, $$8.getZ());
                                this.sandElemental.level.addFreshEntity($$8);
                            }
                        }
                    }

                    this.sandElemental.getLookControl().setLookAt($$0, 10.0F, 10.0F);
                } else if (this.lastSeen < 5) {
                    this.sandElemental.getMoveControl().setWantedPosition($$0.getX(), $$0.getY(), $$0.getZ(), 1.0);
                }

                super.tick();
            }
        }

        private double getFollowDistance() {
            return this.sandElemental.getAttributeValue(Attributes.FOLLOW_RANGE);
        }
    }
}
