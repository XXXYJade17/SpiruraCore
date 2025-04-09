package com.xxxyjade17.spiruracore.Capability;

import net.minecraft.nbt.CompoundTag;

public interface ISpirura {
    int getRank();
    void setRank(int rank);
    int getLevel();
    void setLevel(int level);
    void addExperience(int experience);
    int getExperience();
    void setExperience(int experience);

    void breakShackle();
    void setShackle(boolean shackle);
    boolean hasShackle();
    int getBreakRate();
    void setBreakRate(int breakRate);
    int getRateIncrease();
    void setRateIncrease(int rateIncrease);

    void saveData(CompoundTag nbt);
    void loadData(CompoundTag nbt);
}
