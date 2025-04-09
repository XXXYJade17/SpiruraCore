package com.xxxyjade17.spiruracore.Handler;

import com.xxxyjade17.spiruracore.Capability.SpiruraProvider;
import com.xxxyjade17.spiruracore.SpiruraCore;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@Mod.EventBusSubscriber(modid = SpiruraCore.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegistry {
    @SubscribeEvent
    private static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerEntity(CapabilityHandler.SPIRURA_HANDLER,
                EntityType.PLAYER,
                new SpiruraProvider());
    }
}
