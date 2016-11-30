package com.android.lepretre.arrosage_plante;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by LEPRETRE Rémy
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<Plant> list;

    public MyAdapter(List<Plant> list){
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Plant plant = list.get(position);
        /*holder.name.setText(String.valueOf(plant.getName()));
        holder.frequency.setText("Fréquence d'arrosage : " + String.valueOf(plant.getFrequency()));
        holder.lastSprinkle.setText("Arrosé il y a : " + String.valueOf(plant.getLastSprinkle()) + " jours");*/
        holder.bind(plant);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
