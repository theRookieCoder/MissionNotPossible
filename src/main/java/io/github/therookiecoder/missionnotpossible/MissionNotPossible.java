package io.github.therookiecoder.missionnotpossible;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

public class MissionNotPossible implements ModInitializer {
    @Override
    public void onInitialize() {
        PlayerBlockBreakEvents.AFTER.register(
            (world, player, pos, state, entity) -> player.setFireTicks(5 * 20)
        );
    }
}
