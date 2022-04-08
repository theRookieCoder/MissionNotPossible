package io.github.therookiecoder.missionnotpossible.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static io.github.therookiecoder.missionnotpossible.MissionNotPossible.*;

@Mixin(ServerWorld.class)
public class EntitySpawnMixin {
    @Inject(method = "spawnEntity", at = @At("HEAD"))
    public void injectMethod(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof ZombieEntity || entity instanceof SkeletonEntity) {
            // Equip the armour
            entity.equipStack(EquipmentSlot.HEAD, helmet);
            entity.equipStack(EquipmentSlot.CHEST, chestplate);
            entity.equipStack(EquipmentSlot.LEGS, leggings);
            entity.equipStack(EquipmentSlot.FEET, boots);

            if (entity instanceof SkeletonEntity) {
                entity.equipStack(EquipmentSlot.MAINHAND, bow);
            } else {
                entity.equipStack(EquipmentSlot.MAINHAND, sword);
            }
        } else if (entity instanceof CreeperEntity creeper) {
            creeper.onStruckByLightning(
                ((ServerWorld)(Object)this),
                new LightningEntity(
                    EntityType.LIGHTNING_BOLT,
                    ((ServerWorld)(Object)this)
                )
            );
        }
    }
}
