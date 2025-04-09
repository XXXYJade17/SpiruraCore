package com.xxxyjade17.spiruracore.Capability;

import net.minecraft.nbt.CompoundTag;

public class Spirura implements ISpirura{
    private int rank;
    private int level;
    private int experience;
    private boolean shackle;
    private float breakRate;
    private float rateIncrease;

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
    public float getBreakRate() {
        return breakRate;
    }

    @Override
    public void setBreakRate(float breakRate) {
        this.breakRate=breakRate;
    }

    @Override
    public float getRateIncrease() {
        return rateIncrease;
    }

    @Override
    public void setRateIncrease(float rateIncrease) {
        this.rateIncrease=rateIncrease;
    }

    @Override
    public void saveData(CompoundTag nbt) {
        nbt.putInt("rank", rank);
        nbt.putInt("level", level);
        nbt.putInt("experience", experience);
        nbt.putBoolean("shackle", shackle);
        nbt.putFloat("breakRate", breakRate);
        nbt.putFloat("rateIncrease", rateIncrease);
    }

    @Override
    public void loadData(CompoundTag nbt) {
        rank = nbt.getInt("rank");
        level = nbt.getInt("level");
        experience = nbt.getInt("experience");
        shackle = nbt.getBoolean("shackle");
        breakRate = nbt.getFloat("breakRate");
        rateIncrease = nbt.getFloat("rateIncrease");
    }
}
