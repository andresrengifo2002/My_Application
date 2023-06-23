package com.ivan.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Listapokemonaadapter extends RecyclerView.Adapter<Listapokemonaadapter.ViewHolder> {
    private Listapokemonaadapter listaPokemonAdapter;

    private RecyclerView recyclerView;
    private ArrayList<Pokemon> dataset;
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        private TextView name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.imageView);
        }
    }

    public Listapokemonaadapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewTipe) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Pokemon p=dataset.get(position);
        holder.name.setText(p.getName()); //envio del nombre al texto
    }

    public void add(ArrayList<Pokemon> listaPokemon){
        dataset.addAll(listaPokemon);
        notifyDataSetChanged(); // reseteo de la vista
    }
}

