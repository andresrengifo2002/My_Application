package com.ivan.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    ImageView imageView;

    RecyclerView recyclerView;

    ListapokemonAdapter listaPokemonAdapter;

    Retrofit retrofit;


    private final String TAG = "pokeapi";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById((R.id.reclyview));


        listaPokemonAdapter = new ListapokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);


        retrofit = new Retrofit.Builder().
                //base de la url
                        baseUrl("https://pokeapi.co/api/v2/").
                //convertidor
                        addConverterFactory(GsonConverterFactory.create()).
                //comenzar
                        build();


        imageView = findViewById(R.id.imagenGlide);
        setImageView();
        obtenerDatos();


    }


    private void obtenerDatos() {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerListaPokemon();

        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                //bien
                if (response.isSuccessful()) {
                    PokemonRespuesta pokemonRespuesta = response.body();
                    List<Pokemon> listaPokemon = pokemonRespuesta.getResults();
                    for (int i = 0; i < listaPokemon.size(); i++) {
                        Pokemon p = listaPokemon.get(i);
                        Log.e(TAG, "pokemon: " + p.getName());

                    }
                    listaPokemonAdapter.add((ArrayList<Pokemon>) listaPokemon);
                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                //error
            }
        });
    }

    private void setImageView() {
        String url = "https://i1.sndcdn.com/avatars-000487158516-03ypka-t500x500.jpg";
        Glide.with(this)
                .load(url)
                .error(R.drawable.descarga)
                .placeholder(R.drawable.error1)
                .into(imageView);
    }

}