package com.xxxyjade17.spiruracore.Data.Client;


import com.xxxyjade17.spiruracore.SpiruraCore;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SpiruraData(int rank, int level, int experience, boolean shackle, int breakRate, int rateIncrease) implements CustomPacketPayload {
    public static final ResourceLocation ID = new ResourceLocation(SpiruraCore.MODID, "spirura_data");

    public SpiruraData(FriendlyByteBuf buf){
        this(buf.readInt(), buf.readInt(),buf.readInt(),buf.readBoolean(),buf.readInt(),buf.readInt());
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeInt(rank);
        buf.writeInt(level);
        buf.writeInt(experience);
        buf.writeBoolean(shackle);
        buf.writeInt(breakRate);
        buf.writeInt(rateIncrease);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
