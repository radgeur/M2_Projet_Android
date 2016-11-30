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
