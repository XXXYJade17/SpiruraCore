package com.xxxyjade17.spiruracore.Event;

import com.xxxyjade17.spiruracore.Capability.Spirura;
import com.xxxyjade17.spiruracore.Config;
import com.xxxyjade17.spiruracore.Data.Client.SpiruraData;
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
                    int rank = spirura.getRank();
                    int level = spirura.getLevel();
                   if(!spirura.hasShackle()){
                        int ticks=counters.getOrDefault(player,0);
                        ticks++;
                        if(ticks>=TICKS_PER_INCREASE){
                            if(!spirura.hasShackle()){
                                int increasedExperience= SpiruraCore.getConfig().getIncreasedExperience(rank,level);
                                spirura.addExperience(increasedExperience);
                                PacketDistributor.PLAYER.with(player)
                                        .send(new SpiruraData(
                                                spirura.getRank(),
                                                spirura.getLevel(),
                                                spirura.getExperience(),
                                                spirura.hasShackle(),
                                                spirura.getBreakRate(),
                                                spirura.getRateIncrease()));
                                player.sendSystemMessage(Config.getMessage("spirura.online.increase",increasedExperience));
                            }
                            counters.put(player,0);
                        }else{
                            counters.put(player,ticks);
                        }
                   }
                });
            }
        }
    }
}
