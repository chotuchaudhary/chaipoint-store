package com.chaipoint.config;

import java.util.Properties;

public class IngredientsRefillThresholdConfig {
    public static final int DEFAULT_WATER_THRESHOLD_LIMIT = 10;//ml
    public static final int DEFAULT_MILK_THRESHOLD_LIMIT = 10;//ml
    public static final int DEFAULT_TEA_LEAVES_SYRUP_THRESHOLD_LIMIT = 10;//ml
    public static final int DEFAULT_ELAICHI_SYRUP_THRESHOLD_LIMIT = 10;//ml
    public static final int DEFAULT_GINGER_SYRUP_THRESHOLD_LIMIT = 10;//ml
    public static final int DEFAULT_SUGAR_SYRUP_THRESHOLD_LIMIT = 10;//ml
    public static final int DEFAULT_COFFEE_SYRUP_THRESHOLD_LIMIT = 10;//ml
    public static final int DEFAULT_GREEN_MIXTURE_THRESHOLD_LIMIT = 10;//ml

    public IngredientsRefillThresholdConfig(){

    }

    public int getWaterThresholdLimit(){
        return waterThresholdLimit;
    }

    public void setWaterThresholdLimit(int waterThresholdLimit){
        this.waterThresholdLimit = waterThresholdLimit;
    }

    public int getMilkThresholdLimit(){
        return milkThresholdLimit;
    }

    public void setMilkThresholdLimit(int milkThresholdLimit){
        this.milkThresholdLimit = milkThresholdLimit;
    }

    public int getTeaLeavesSyrupThresholdLimit(){
        return teaLeavesSyrupThresholdLimit;
    }

    public void setTeaLeavesSyrupThresholdLimit(int teaLeavesSyrupThresholdLimit){
        this.teaLeavesSyrupThresholdLimit = teaLeavesSyrupThresholdLimit;
    }

    public int getElaichiThresholdLimit(){
        return elaichiThresholdLimit;
    }

    public void setElaichiThresholdLimit(int elaichiThresholdLimit){
        this.elaichiThresholdLimit = elaichiThresholdLimit;
    }

    public int getGingerThresholdLimit(){
        return gingerThresholdLimit;
    }

    public void setGingerThresholdLimit(int gingerThresholdLimit){
        this.gingerThresholdLimit = gingerThresholdLimit;
    }

    public int getSugarThresholdLimit(){
        return sugarThresholdLimit;
    }

    public void setSugarThresholdLimit(int sugarThresholdLimit){
        this.sugarThresholdLimit = sugarThresholdLimit;
    }

    public int getCoffeeSyrupThresholdLimit(){
        return coffeeSyrupThresholdLimit;
    }

    public void setCoffeeSyrupThresholdLimit(int coffeeSyrupThresholdLimit){
        this.coffeeSyrupThresholdLimit = coffeeSyrupThresholdLimit;
    }

    public int getGreenMixtureThresholdLimit(){
        return greenMixtureThresholdLimit;
    }

    public void setGreenMixtureThresholdLimit(int greenMixtureThresholdLimit){
        this.greenMixtureThresholdLimit = greenMixtureThresholdLimit;
    }

    public int waterThresholdLimit = DEFAULT_WATER_THRESHOLD_LIMIT;
    public int milkThresholdLimit = DEFAULT_MILK_THRESHOLD_LIMIT;

    public int teaLeavesSyrupThresholdLimit = DEFAULT_TEA_LEAVES_SYRUP_THRESHOLD_LIMIT;
    public int elaichiThresholdLimit = DEFAULT_ELAICHI_SYRUP_THRESHOLD_LIMIT;
    public int gingerThresholdLimit = DEFAULT_GINGER_SYRUP_THRESHOLD_LIMIT;
    public int sugarThresholdLimit = DEFAULT_SUGAR_SYRUP_THRESHOLD_LIMIT;
    public int coffeeSyrupThresholdLimit = DEFAULT_COFFEE_SYRUP_THRESHOLD_LIMIT;
    public int greenMixtureThresholdLimit = DEFAULT_GREEN_MIXTURE_THRESHOLD_LIMIT;

    public IngredientsRefillThresholdConfig(Properties properties){
        this.waterThresholdLimit = (int) properties.getOrDefault("waterThresholdLimit", DEFAULT_WATER_THRESHOLD_LIMIT);
        this.milkThresholdLimit = (int) properties.getOrDefault("milkThresholdLimit", DEFAULT_MILK_THRESHOLD_LIMIT);
        this.teaLeavesSyrupThresholdLimit = (int) properties.getOrDefault("teaLeavesSyrupThresholdLimit", DEFAULT_TEA_LEAVES_SYRUP_THRESHOLD_LIMIT);
        this.elaichiThresholdLimit = (int) properties.getOrDefault("elaichiThresholdLimit", DEFAULT_ELAICHI_SYRUP_THRESHOLD_LIMIT);
        this.gingerThresholdLimit = (int) properties.getOrDefault("gingerThresholdLimit", DEFAULT_GINGER_SYRUP_THRESHOLD_LIMIT);
        this.sugarThresholdLimit = (int) properties.getOrDefault("sugarThresholdLimit", DEFAULT_SUGAR_SYRUP_THRESHOLD_LIMIT);
        this.coffeeSyrupThresholdLimit = (int) properties.getOrDefault("coffeeSyrupThresholdLimit", DEFAULT_COFFEE_SYRUP_THRESHOLD_LIMIT);
        this.greenMixtureThresholdLimit = (int) properties.getOrDefault("greenMixtureThresholdLimit", DEFAULT_GREEN_MIXTURE_THRESHOLD_LIMIT);
    }
}
