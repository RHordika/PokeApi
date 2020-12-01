package com.example.pokeapi.repository;

import com.example.pokeapi.domain.Pokemon;

import java.util.List;

public interface PokeCallback {

    void callback(List<Pokemon> pokemons);

}
