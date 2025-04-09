package com.xxxyjade17.spiruracore.Event;

import com.xxxyjade17.spiruracore.Capability.Spirura;
import com.xxxyjade17.spiruracore.Data.Client.SpiruraData;
import com.xxxyjade17.spiruracore.Data.Server.SpiruraSavedData;
import com.xxxyjade17.spiruracore.Handler.CapabilityHandler;
import com.xxxyjade17.spiruracore.SpiruraCore;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = SpiruraCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.DEDICATED_SERVER)
public class PlayerJoinEvent {
    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer player) {
                SpiruraSavedData.addUUID(player);
                Optional<Spirura> optionalCE =
                        Optional.ofNullable(player.getCapability(CapabilityHandler.SPIRURA_HANDLER));
                optionalCE.ifPresent(spirura -> {
                    SpiruraSavedData savedData = SpiruraSavedData.get((ServerLevel) event.getLevel());
                    CompoundTag data=new CompoundTag();
                    savedData.getPlayerData(player.getUUID()).saveData(data);
                    spirura.loadData(data);
                    PacketDistributor.PLAYER.with(player)
                            .send(new SpiruraData(
                                    spirura.getRank(),
                                    spirura.getLevel(),
                                    spirura.getExperience(),
                                    spirura.hasShackle(),
                                    spirura.getBreakRate(),
                                    spirura.getRateIncrease()
                            ));
                });
            }
        }
    }
}
