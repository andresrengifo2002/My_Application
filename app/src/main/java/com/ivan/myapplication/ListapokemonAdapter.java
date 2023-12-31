package com.ivan.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListapokemonAdapter extends RecyclerView.Adapter<ListapokemonAdapter.ViewHolder> {

    private ListapokemonAdapter listaPokemonAdapter;

    private RecyclerView recyclerView;
    private ArrayList<Pokemon> dataset;

    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        private TextView name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public ListapokemonAdapter(Context context) {
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

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6"+p.getNumber()+".png")
                .into(holder.imageView);
    }

    public void add(ArrayList<Pokemon> listaPokemon){
        dataset.addAll(listaPokemon);
        notifyDataSetChanged(); // reseteo de la vista
    }
}

