package com.xxxyjade17.spiruracore.Event;

import com.xxxyjade17.spiruracore.Capability.Spirura;
import com.xxxyjade17.spiruracore.Config;
import com.xxxyjade17.spiruracore.Handler.CapabilityHandler;
import com.xxxyjade17.spiruracore.SpiruraCore;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.LogicalSide;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = SpiruraCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.DEDICATED_SERVER)
public class PlayerTick {
    private static final int TICKS_PER_INCREASE = (int) Config.getINSTANCE().getConfigInfo("ticks_per_increase");
    private static final Map<ServerPlayer, Integer> counters = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            if (event.player instanceof ServerPlayer player) {
                Optional<Spirura> optionalSpirura=
                        Optional.ofNullable(player.getCapability(CapabilityHandler.SPIRURA_HANDLER));
                optionalSpirura.ifPresent(spirura -> {
                   if(!spirura.hasShackle()){

                   }
                });
//                Optional<Shackle> optionalShackle=
//                        Optional.ofNullable(player.getCapability(CapabilityHandler.SHACKLE_HANDLER));
//                optionalShackle.ifPresent(shackle ->{
//                    if(!shackle.hasShackle()){
//                        Optional<CelestialEssence> optionalCE =
//                                Optional.ofNullable(player.getCapability(CapabilityHandler.CELESTIAL_ESSENCE_HANDLER));
//                        optionalCE.ifPresent(CE -> {
//                            int tickCounter = tickCounters.getOrDefault(player, 0)+1;
//                            if (tickCounter >= TICKS_PER_MINUTE) {
//                                tickCounters.put(player, 0);
//                                CE.addEtherealEssence(1);
//                                player.sendSystemMessage(config.getMessage("online.reward",1));
//                                if(CE.isReachShackle()){
//                                    shackle.setShackle(true);
//                                    shackle.setBreakRate(config.getInitialBreakRate(CE.getCultivationRealm(), CE.getStageRank()));
//                                    shackle.setRatePerAdd(config.getBreakRatePerAdd(CE.getCultivationRealm(), CE.getStageRank()));
//                                    CE.breakShackle();
//                                }
//                                PacketDistributor.PLAYER.with(player)
//                                        .send(new CelestialEssenceData(CE.getCultivationRealm(), CE.getStageRank(), CE.getEtherealEssence()));
//                            } else {
//                                tickCounters.put(player, tickCounter);
//                            }
//                        });
//                    }
//                });
            }
        }
    }
}
