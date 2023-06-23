package com.ivan.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeapiService {
    @GET("pokemon")
    Call<PokemonRespuesta>obtenerListaPokemon();
}
