package com.ivan.myapplication;

import java.util.ArrayList;
import java.util.List;

public class PokemonRespuesta {
    private List<Pokemon> Results = new ArrayList<Pokemon>();
    public List<Pokemon>

    getResult() {
        return Results;
    }

    public void setResult(List<Pokemon> result)
    {
        Results = result;
    }
}
