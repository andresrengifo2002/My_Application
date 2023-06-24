package com.ivan.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText name, number;

    private ImageView url;

    private RecyclerView recyclerView ;

    private ListapokemonAdapter listaPokemonAdapter;

    Retrofit retrofit;

    private  int offset;

    private final String TAG="pokeapi";



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById((R.id.reclyview));
        name = findViewById(R.id.textView);

        listaPokemonAdapter = new ListapokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);


        retrofit=new Retrofit.Builder().
                //base de la url
                baseUrl("https://pokeapi.co/api/v2/").
                //convertidor
                addConverterFactory(GsonConverterFactory.create()).
                //comenzar
                build();
        obtenerDatos();
        offset=0;

    }

    private void obtenerDatos() {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerListaPokemon();
        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                //bien
                if (response.isSuccessful()){
                    PokemonRespuesta pokemonRespuesta = response.body();
                    List<Pokemon> listaPokemon = pokemonRespuesta.getResults();
                    for (int i=0;i<listaPokemon.size();i++ ){
                        Pokemon p=listaPokemon.get(i);
                        Log.e(TAG,"pokemon: " +p.getName());

                    }
                    listaPokemonAdapter.add((ArrayList<Pokemon>) listaPokemon);
                }
                else {
                    Log.e(TAG,"onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                //error
            }
        });
    }
}