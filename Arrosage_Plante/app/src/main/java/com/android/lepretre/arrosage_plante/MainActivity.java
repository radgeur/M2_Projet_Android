package com.android.lepretre.arrosage_plante;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Plant> plants = new ArrayList<Plant>();
    private static final int MONCODE = 8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrieve the buttons
        Button addPlantButton = (Button) this.findViewById(R.id.addButton);
        Button fixtures = (Button) this.findViewById(R.id.fixtures);

        //Display the recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(plants));
        

        //Display the fixtures in the recyclerView
        fixtures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixtures();
                MyDatabaseHelper dbHelper = new MyDatabaseHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor plantsFixtures = db.query(MyDatabaseHelper.TABLE_NAME, null, null, null, null, null, null, null);
                plantsFixtures.moveToFirst();
                while(!plantsFixtures.isAfterLast()){
                    plants.add(new Plant(plantsFixtures.getString(1), Integer.parseInt(plantsFixtures.getString(2)),
                            Integer.parseInt(plantsFixtures.getString(3))));
                    plantsFixtures.moveToNext();
                }
                recyclerView.setAdapter(new MyAdapter(plants));
            }
        });

        //call the crudPlantActivity
        addPlantButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent crudPlant = new Intent(view.getContext(), CrudPlantActivity.class);
                String status = "add";
                crudPlant.putExtra("status", status);
                startActivityForResult(crudPlant, MONCODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case MONCODE:
                switch (resultCode) {
                    case RESULT_OK:
                        break;

                    case RESULT_CANCELED:
                        break;

                    default:
                        break;
                }
            default:
                break;
        }
    }

    /**
     * insert fixtures into the plant table of the database
     */
    private void fixtures() {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues plant1 = new ContentValues();
        plant1.put(MyDatabaseHelper.FIELD_NAME, "Rose");
        plant1.put(MyDatabaseHelper.FIELD_FREQUENCY, 5);
        plant1.put(MyDatabaseHelper.FIELD_LASTSPRINKLE, 2);
        db.insert(MyDatabaseHelper.TABLE_NAME, null, plant1);
    }
}
