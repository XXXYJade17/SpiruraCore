package com.xxxyjade17.spiruracore.Data.Client;


import com.xxxyjade17.spiruracore.SpiruraCore;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SpiruraData(int rank, int level, int experience, boolean shackle, float breakRate, float rateIncrease) implements CustomPacketPayload {
    public static final ResourceLocation ID = new ResourceLocation(SpiruraCore.MODID, "spirura_data");

    public SpiruraData(FriendlyByteBuf buf){
        this(buf.readInt(), buf.readInt(),buf.readInt(),buf.readBoolean(),buf.readFloat(),buf.readFloat());
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeInt(rank);
        buf.writeInt(level);
        buf.writeInt(experience);
        buf.writeBoolean(shackle);
        buf.writeFloat(breakRate);
        buf.writeFloat(rateIncrease);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
