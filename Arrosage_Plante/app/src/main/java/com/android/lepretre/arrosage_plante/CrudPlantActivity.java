package com.android.lepretre.arrosage_plante;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CrudPlantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_plant);

        final MyDatabaseHelper dbHelper = new MyDatabaseHelper(getApplicationContext());

        //Retrieve the element of the activity
        final EditText nameText = (EditText) findViewById(R.id.name);
        final EditText frequencyNumber = (EditText) findViewById(R.id.frequency);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button delButton = (Button) findViewById(R.id.deleteButton);

        //hide the button delete when activity call by the add button
        if(getIntent().getExtras() != null) {
            String status = getIntent().getExtras().getString("status");
            //when call by clicking on the add button
            if(status.equals("add")) {
                delButton.setVisibility(View.INVISIBLE);
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = getIntent();
                        intent.putExtra("name", nameText.getText().toString());
                        intent.putExtra("frequency", frequencyNumber.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
            //when call by clicking on a recycler Item
            else{
                //bind the pieces of information of plant with the textfield of the activity
                final Plant plant = dbHelper.selectById(getIntent().getExtras().getLong("id"));
                nameText.setText(plant.getName());
                frequencyNumber.setText(Integer.toString(plant.getFrequency()));

                //click on the saveButton
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbHelper.update(new Plant(getIntent().getExtras().getLong("id"),
                                nameText.getText().toString(),
                                Integer.parseInt(frequencyNumber.getText().toString()),
                                plant.getLastSprinkle()));
                        Intent intent = getIntent();
                        /*intent.putExtra("id", getIntent().getExtras().getLong("id"));
                        intent.putExtra("name", nameText.getText().toString());
                        intent.putExtra("frequency", frequencyNumber.getText().toString());*/
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });

                //click on the deleteButton
                delButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbHelper.delById(getIntent().getExtras().getLong("id"));
                        Intent intent = getIntent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
        }

    }
}
