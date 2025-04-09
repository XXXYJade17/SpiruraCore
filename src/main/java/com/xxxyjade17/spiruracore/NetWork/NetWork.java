package com.xxxyjade17.spiruracore.NetWork;


import com.xxxyjade17.spiruracore.Data.Client.SpiruraData;
import com.xxxyjade17.spiruracore.NetWork.Handler.ClientPayloadHandler;
import com.xxxyjade17.spiruracore.NetWork.Handler.ServerPayloadHandler;
import com.xxxyjade17.spiruracore.SpiruraCore;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

@Mod.EventBusSubscriber(modid = SpiruraCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetWork {
    @SubscribeEvent
    public static void registerPackets(RegisterPayloadHandlerEvent event) {
        IPayloadRegistrar registrar = event.registrar(SpiruraCore.MODID);

        registrar.play(SpiruraData.ID, SpiruraData::new, handler ->
                handler.client(ClientPayloadHandler.getINSTANCE()::handleSpiruraData)
                        .server(ServerPayloadHandler.getINSTANCE()::handleSpiruraData));
    }
}