package com.example.pokeapi.domain;

import com.example.pokeapi.ui.ShortPokemon;

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
