package io.github.therookiecoder.missionnotpossible;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class MissionNotPossible implements ModInitializer {
    public static final ItemStack helmet = Items.NETHERITE_HELMET.getDefaultStack();
    public static final ItemStack chestplate = Items.NETHERITE_CHESTPLATE.getDefaultStack();
    public static final ItemStack leggings = Items.NETHERITE_LEGGINGS.getDefaultStack();
    public static final ItemStack boots = Items.NETHERITE_BOOTS.getDefaultStack();
    public static final ItemStack sword = Items.NETHERITE_SWORD.getDefaultStack();
    public static final ItemStack bow = Items.BOW.getDefaultStack();

    @Override
    public void onInitialize() {
        PlayerBlockBreakEvents.AFTER.register(
            (world, player, pos, state, entity) -> player.setFireTicks(5 * 20)
        );

        // Enchant the armour and weapons
        helmet.addEnchantment(Enchantments.PROTECTION, 4);
        chestplate.addEnchantment(Enchantments.PROTECTION, 4);
        leggings.addEnchantment(Enchantments.PROTECTION, 4);
        boots.addEnchantment(Enchantments.PROTECTION, 4);
        bow.addEnchantment(Enchantments.POWER, 5);
        bow.addEnchantment(Enchantments.FLAME, 1);
        sword.addEnchantment(Enchantments.SHARPNESS, 5);
        sword.addEnchantment(Enchantments.FIRE_ASPECT, 1);
    }
}
