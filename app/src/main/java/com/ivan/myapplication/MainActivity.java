package com.ivan.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;

    private final String TAG="pokeapi";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit=new Retrofit.Builder().
                //base de la url
                baseUrl("https://pokeapi.co/api/v2/").
                //convertidor
                addConverterFactory(GsonConverterFactory.create()).
                //comenzar
                build();
        obtenerDatos();

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
                    List<Pokemon> listaPokemon = pokemonRespuesta.getResult();
                    for (int i=0;i<listaPokemon.size();i++ ){
                        Pokemon p=listaPokemon.get(i);
                        Log.e(TAG,"pokemon: " +p.getName());

                    }
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                //error
            }
        });
    }
}