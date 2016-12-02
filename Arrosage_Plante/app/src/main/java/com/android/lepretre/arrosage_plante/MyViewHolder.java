package com.android.lepretre.arrosage_plante;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LEPRETRE Rémy
 * ViewHolder of my RecyclerView
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener, RecyclerView.OnLongClickListener {

    //ATTRIBUTES
    //Textbox to write the pieces of information about the plant
    public TextView name;
    public TextView frequency;
    public TextView lastSprinkle;
    private Plant plant;

    //METHODS
    /**
     * Constructor
     * @param v the view
     */
    public MyViewHolder(View v){
        super(v);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        name = (TextView) v.findViewById(R.id.textView);
        frequency = (TextView) v.findViewById(R.id.textView2);
        lastSprinkle = (TextView) v.findViewById(R.id.textView3);
    }

    /**
     * Bind the pieces of information of a plant with the textbox of the viewholder
     * @param plant
     */
    public void bind(Plant plant){
        this.plant = plant;
        name.setText(String.valueOf(plant.getName()));
        frequency.setText("Fréquence d'arrosage : " + String.valueOf(plant.getFrequency()));
        lastSprinkle.setText("Arrosé il y a : " + String.valueOf(plant.getLastSprinkle()) + " jours");

    }

    @Override
    public void onClick(View view) {
        Intent crudPlant = new Intent(view.getContext(), CrudPlantActivity.class);
        crudPlant.putExtra("status", "update");
        crudPlant.putExtra("id", plant.getId());
        ((MainActivity) view.getContext()).startActivityForResult(crudPlant, MainActivity.UPDATECODE);
    }


    @Override
    public boolean onLongClick(View view) {
        plant.setLastSprinkle(0);
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(view.getContext());
        dbHelper.update(plant);
        return true;
    }
}