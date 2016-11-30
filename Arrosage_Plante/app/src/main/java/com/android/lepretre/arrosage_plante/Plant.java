package com.android.lepretre.arrosage_plante;

/**
 * Created by LEPRETRE Rémy
 */

public class Plant {
    private String name;
    private int frequency;
    private int lastSprinkle;

    public Plant(String n, int f, int sprinkle){
        name = n;
        frequency = f;
        lastSprinkle = sprinkle;
    }

    public String getName(){
        return this.name;
    }

    public int getFrequency(){
        return this.frequency;
    }

    public int getLastSprinkle(){
        return this.lastSprinkle;
    }
}
