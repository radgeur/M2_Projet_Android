package com.android.lepretre.arrosage_plante;

/**
 * Created by LEPRETRE Rémy
 */

public class Plant {
    //ATTRIBUTES
    private String name;
    private int frequency;
    private int lastSprinkle;


    //METHODS
    /**
     * Constructor
     * @param n name of the plant
     * @param f sprinkle frequency
     * @param sprinkle number of days since the last sprinkle
     */
    public Plant(String n, int f, int sprinkle){
        name = n;
        frequency = f;
        lastSprinkle = sprinkle;
    }

    //Getters
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
