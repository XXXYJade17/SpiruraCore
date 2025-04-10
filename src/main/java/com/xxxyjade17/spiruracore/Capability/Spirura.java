package com.xxxyjade17.spiruracore.Capability;

import com.xxxyjade17.spiruracore.Config;
import net.minecraft.nbt.CompoundTag;

import java.security.SecureRandom;

public class Spirura implements ISpirura{
    private int rank=1;
    private int level=1;
    private int experience=0;
    private boolean shackle=false;
    private float breakRate=0;
    private float rateIncrease=0;

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
        int requiredExperience = Config.getINSTANCE().getRequiredExperience(rank, level);
        if(this.experience>=requiredExperience){
            if(Config.getINSTANCE().getShackle(rank,level)){
                this.shackle=true;
                this.breakRate = Config.getINSTANCE().getBreakRate(rank, level);
                this.rateIncrease = Config.getINSTANCE().getRateIncrease(rank, level);
            }else{
                if(this.level!=10){
                    this.level++;
                }else{
                    this.rank++;
                    this.level=1;
                }
            }
        }
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
        SecureRandom secureRandom = new SecureRandom();
        float randomValue = secureRandom.nextFloat();
        if(randomValue<=this.breakRate){
            this.shackle=false;
            this.breakRate = 0;
            this.rateIncrease = 0;

            if(level==10){
                rank++;
                level=1;
            }else{
                level++;
            }
        }else{
            this.breakRate+=this.rateIncrease;
        }
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
