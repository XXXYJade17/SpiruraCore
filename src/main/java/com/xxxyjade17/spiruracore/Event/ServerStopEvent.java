package com.xxxyjade17.spiruracore.Event;

import com.xxxyjade17.spiruracore.Data.Server.SpiruraSavedData;
import com.xxxyjade17.spiruracore.SpiruraCore;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

@Mod.EventBusSubscriber(modid = SpiruraCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.DEDICATED_SERVER)
public class ServerStopEvent {
    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
        SpiruraSavedData.saveAllPlayersData();
    }
}
