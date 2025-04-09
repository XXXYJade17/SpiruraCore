package com.xxxyjade17.spiruracore.Handler;

import com.xxxyjade17.spiruracore.Capability.Spirura;
import com.xxxyjade17.spiruracore.SpiruraCore;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.EntityCapability;

public class CapabilityHandler {
    public static final EntityCapability<Spirura, Void> SPIRURA_HANDLER =
            EntityCapability.createVoid(new ResourceLocation(SpiruraCore.MODID, "spirura_handler"),
                    Spirura.class);
}
