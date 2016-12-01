package com.android.lepretre.arrosage_plante;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

}
