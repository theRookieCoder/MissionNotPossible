package io.github.therookiecoder.missionnotpossible.mixin;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Objects;

@Mixin(ServerWorld.class)
public class EntitySpawnMixin {
    private static final ItemStack helmet = new ItemStack(Items.NETHERITE_HELMET);
    private static final ItemStack chestplate = new ItemStack(Items.NETHERITE_CHESTPLATE);
    private static final ItemStack leggings = new ItemStack(Items.NETHERITE_LEGGINGS);
    private static final ItemStack boots = new ItemStack(Items.NETHERITE_BOOTS);
    private static final ItemStack sword = new ItemStack(Items.NETHERITE_SWORD);
    private static final ItemStack bow = new ItemStack(Items.BOW);

    @Inject(method = "spawnEntity", at = @At("HEAD"))
    public void injectMethod(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof ZombieEntity || entity instanceof SkeletonEntity) {
            // Enchant the armour
            helmet.addEnchantment(Enchantments.PROTECTION, 4);
            chestplate.addEnchantment(Enchantments.PROTECTION, 4);
            leggings.addEnchantment(Enchantments.PROTECTION, 4);
            boots.addEnchantment(Enchantments.PROTECTION, 4);

            // Equip the armour
            entity.equipStack(EquipmentSlot.HEAD, helmet);
            entity.equipStack(EquipmentSlot.CHEST, chestplate);
            entity.equipStack(EquipmentSlot.LEGS, leggings);
            entity.equipStack(EquipmentSlot.FEET, boots);

            if (entity instanceof SkeletonEntity) {
                bow.addEnchantment(Enchantments.POWER, 5);
                bow.addEnchantment(Enchantments.FLAME, 1);
                entity.equipStack(EquipmentSlot.MAINHAND, bow);
            } else {
                sword.addEnchantment(Enchantments.SHARPNESS, 5);
                sword.addEnchantment(Enchantments.FIRE_ASPECT, 1);
                entity.equipStack(EquipmentSlot.MAINHAND, sword);
            }
        } else if (entity instanceof CreeperEntity creeper) {
            ServerWorld world = Objects.requireNonNull(entity.getServer()).getOverworld();
            creeper.onStruckByLightning(world, new LightningEntity(EntityType.LIGHTNING_BOLT, world));
        }
    }
}
