package com.android.lepretre.arrosage_plante;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by LEPRETRE Rémy
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {

    //ATTRIBUTES
    //name of the plant
    public TextView name;
    //frequency between two sprinkle
    public TextView frequency;
    //Number of days since the last sprinkle
    public TextView lastSprinkle;

    //METHODS
    /**
     * A view for one item
     * @param v the view
     */
    public MyViewHolder(View v){
        super(v);
        name = (TextView) v.findViewById(R.id.textView);
        frequency = (TextView) v.findViewById(R.id.textView2);
        lastSprinkle = (TextView) v.findViewById(R.id.textView3);
    }

    public void bind(Plant plant){
        name.setText(String.valueOf(plant.getName()));
        frequency.setText("Fréquence d'arrosage : " + String.valueOf(plant.getFrequency()));
        lastSprinkle.setText("Arrosé il y a : " + String.valueOf(plant.getLastSprinkle()) + " jours");

    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(c, "It works", Toast.LENGTH_SHORT).show();
    }



}