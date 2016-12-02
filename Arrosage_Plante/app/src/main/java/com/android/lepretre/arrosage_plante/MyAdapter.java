package com.android.lepretre.arrosage_plante;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by LEPRETRE Rémy
 * Adapter of my RecyclerView
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    //List of plant to display
    List<Plant> list;

    /**
     * Constuctor
     * @param list list of Plant
     */
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
        holder.bind(plant);
        int frequency = plant.getFrequency();
        int sprinkle = plant.getLastSprinkle();
        if(sprinkle == frequency ||frequency==sprinkle-1)
            holder.itemView.setBackgroundColor(Color.rgb(255,165,0));
        else if(sprinkle > frequency)
            holder.itemView.setBackgroundColor(Color.RED);
        else
            holder.itemView.setBackgroundColor(Color.GREEN);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Plant> plants){
        this.list = plants;
    }
}
