package io.github.therookiecoder.missionnotpossible.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerAttackMixin {
    @Inject(method = "attack", at = @At("HEAD"))
    public void injectMethod(Entity target, CallbackInfo ci) {
        if (target instanceof CreeperEntity creeper) {
            creeper.ignite();
        } else if (target instanceof SpiderEntity) {
            ((PlayerEntity) (Object) this).damage(DamageSource.mob((LivingEntity) target), 3.4028235E38F);
        }
    }
}
