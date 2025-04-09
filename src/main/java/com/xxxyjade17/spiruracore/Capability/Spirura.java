package com.xxxyjade17.spiruracore.Capability;

import net.minecraft.nbt.CompoundTag;

public class Spirura implements ISpirura{
    private int rank;
    private int level;
    private int experience;
    private boolean shackle;
    private int breakRate;
    private int rateIncrease;

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public void setRank(int rank) {
        this.rank=rank;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level=level;
    }

    @Override
    public void addExperience(int experience) {
        this.experience+=experience;
    }

    @Override
    public int getExperience() {
        return experience;
    }

    @Override
    public void setExperience(int experience) {
        this.experience=experience;
    }

    @Override
    public void breakShackle() {

    }

    @Override
    public void setShackle(boolean shackle) {
        this.shackle=shackle;
    }

    @Override
    public boolean hasShackle() {
        return shackle;
    }

    @Override
    public int getBreakRate() {
        return breakRate;
    }

    @Override
    public void setBreakRate(int breakRate) {
        this.breakRate=breakRate;
    }

    @Override
    public int getRateIncrease() {
        return rateIncrease;
    }

    @Override
    public void setRateIncrease(int rateIncrease) {
        this.rateIncrease=rateIncrease;
    }

    @Override
    public void saveData(CompoundTag nbt) {
        nbt.putInt("rank", rank);
        nbt.putInt("level", level);
        nbt.putInt("experience", experience);
        nbt.putBoolean("shackle", shackle);
        nbt.putInt("breakRate", breakRate);
        nbt.putInt("rateIncrease", rateIncrease);
    }

    @Override
    public void loadData(CompoundTag nbt) {
        rank = nbt.getInt("rank");
        level = nbt.getInt("level");
        experience = nbt.getInt("experience");
        shackle = nbt.getBoolean("shackle");
        breakRate = nbt.getInt("breakRate");
        rateIncrease = nbt.getInt("rateIncrease");
    }
}
