package com.example.pokeapi;

import java.util.List;

public class PokemonListResponse {

    private List<ShortPokemon> results;

    public List<ShortPokemon> getResults() {
        return results;
    }

    public void setResults(List<ShortPokemon> results) {
        this.results = results;
    }


}
