package com.android.lepretre.arrosage_plante;

/**
 * Created by LEPRETRE Rémy
 * Class to instantiate the object Plant
 */

public class Plant {
    //ATTRIBUTES
    private long id;
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


    public Plant(long id, String n, int f, int sprinkle){
        this(n,f,sprinkle);
        this.id=id;
    }

    //Getters
    public long getId(){
        return this.id;
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

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setLastSprinkle(int lastSprinkle) {
        this.lastSprinkle = lastSprinkle;
    }
}
