package com.android.lepretre.arrosage_plante;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEPRETRE Rémy
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    //Fields of my Table
    public static final String FIELD_KEY = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_FREQUENCY = "frequency";
    public static final String FIELD_LASTSPRINKLE = "lastSprinkle";

    //Name of my Table
    public static final String TABLE_NAME = "Plant";

    /**
     * Constructor override SQLiteOpenHelper
     */
    public MyDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
            + " (" + FIELD_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FIELD_NAME + " TEXT, "
            + FIELD_FREQUENCY + " INTEGER, "
            + FIELD_LASTSPRINKLE + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    /**
     * Select all the plants in the database
     * @return list of plant
     */
    public List<Plant> selectAll(){
        List<Plant> plants = new ArrayList<Plant>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            plants.add(new Plant(Long.parseLong(cursor.getString(0)),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3))));
            cursor.moveToNext();
        }
        return plants;
    }

    /**
     * Insert a plant in the database
     * @param plant to insert
     */
    public void insert(Plant plant){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(FIELD_NAME, plant.getName());
        value.put(FIELD_FREQUENCY, plant.getFrequency());
        value.put(FIELD_LASTSPRINKLE, plant.getLastSprinkle());
        db.insert(TABLE_NAME, null, value);
    }

    /**
     * Select the plant with the ID
     * @param id of the plant
     * @return plant
     */
    public Plant selectById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] fields = {FIELD_KEY};
        String where = FIELD_KEY + " =?";
        String[] whereArgs = {Long.toString(id)};
        Cursor cursor = db.query(TABLE_NAME, null, where, whereArgs, null, null, null, null);
        cursor.moveToFirst();
        Plant plant = null;
        while (!cursor.isAfterLast()) {
            plant = new Plant(cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3)));
            cursor.moveToNext();
        }
        return plant;
    }

    /**
     * Update a plant in the database
     * @param plant to update
     */
    public void update(Plant plant) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues value = new ContentValues();
        value.put(FIELD_NAME, plant.getName());
        value.put(FIELD_FREQUENCY, plant.getFrequency());
        value.put(FIELD_LASTSPRINKLE, 0);

        String[] fields = {FIELD_KEY};
        String where = FIELD_KEY + " =?";
        String[] whereArgs = {Long.toString(plant.getId())};
        db.update(TABLE_NAME, value, where, whereArgs);
    }

    /**
     * Delete a plant by it ID
     * @param id of the plant to delete
     */
    public void delById(long id){
        SQLiteDatabase db = this.getWritableDatabase();

        String where = FIELD_KEY + "=?";
        String[] whereArgs = {Long.toString(id)};

        db.delete(TABLE_NAME, where, whereArgs);
    }
}
