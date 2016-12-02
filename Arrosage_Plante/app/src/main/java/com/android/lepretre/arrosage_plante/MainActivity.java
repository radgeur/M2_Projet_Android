package com.android.lepretre.arrosage_plante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Plant> plants = new ArrayList<Plant>();
    public static final int ADDCODE = 8;
    public static final int UPDATECODE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyDatabaseHelper dbHelper = new MyDatabaseHelper(getApplicationContext());

        //Retrieve the elements of the activity
        Button addPlantButton = (Button) this.findViewById(R.id.addButton);
        Button fixtures = (Button) this.findViewById(R.id.fixtures);
        final Button oneMoreDay = (Button) this.findViewById(R.id.oneMoreDay);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Display the recyclerView and all the plants actually in the database
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        plants = (dbHelper.selectAll());
        recyclerView.setAdapter(new MyAdapter(plants));

        //Display the fixtures in the recyclerView
        fixtures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixtures();
                plants = (dbHelper.selectAll());
                recyclerView.setAdapter(new MyAdapter(plants));
            }
        });

        //call the crudPlantActivity to can add a plant
        addPlantButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent crudPlant = new Intent(view.getContext(), CrudPlantActivity.class);
                crudPlant.putExtra("status", "add");
                startActivityForResult(crudPlant, ADDCODE);
            }
        });

        //add 1 day to the lastSprinkle of every plants
        oneMoreDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oneMoreDay();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(getApplicationContext());
        switch (requestCode) {
            //when add a plant
            case ADDCODE:
                switch (resultCode) {
                    case RESULT_OK:
                        dbHelper.insert(new Plant(data.getExtras().getString("name"),
                                Integer.parseInt(data.getExtras().getString("frequency")),
                                0));
                        plants = (dbHelper.selectAll());
                        recyclerView.setAdapter(new MyAdapter(plants));
                        break;

                    case RESULT_CANCELED:
                        Toast.makeText(getApplicationContext(), "Plante non ajouté", Toast.LENGTH_LONG).show();
                        break;

                    default:
                        break;
                }

             //when update a plant
            case UPDATECODE:
                switch (resultCode) {
                    case RESULT_OK:
                        plants = (dbHelper.selectAll());
                        recyclerView.setAdapter(new MyAdapter(plants));
                        break;

                    case RESULT_CANCELED:
                        Toast.makeText(getApplicationContext(), "Modifications non enregistré", Toast.LENGTH_LONG).show();
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
        dbHelper.insert(new Plant("Rose", 5, 2));
        dbHelper.insert(new Plant("Roseau", 2, 0));
        dbHelper.insert(new Plant("Cuisine", 1, 0));
        dbHelper.insert(new Plant("Salon", 6, 4));
        dbHelper.insert(new Plant("Muguet", 3, 2));
        dbHelper.insert(new Plant("Tulipes", 2, 2));
        dbHelper.insert(new Plant("Jonquilles", 5, 2));
        dbHelper.insert(new Plant("Fleurs", 1, 1));
        dbHelper.insert(new Plant("Tournesol", 10, 20));
        dbHelper.insert(new Plant("Television", 2, 1));
    }

    private void oneMoreDay(){
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(getApplicationContext());
        plants = (dbHelper.selectAll());
        for(Plant p : plants){
            p.setLastSprinkle(p.getLastSprinkle() + 1);
            dbHelper.update(p);
        }
        plants = (dbHelper.selectAll());
        recyclerView.setAdapter(new MyAdapter(plants));
    }
}
