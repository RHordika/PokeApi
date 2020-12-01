package com.example.pokeapi.repository;

import com.example.pokeapi.domain.Pokemon;
import com.example.pokeapi.domain.PokemonListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("pokemon?limit=150&offset=0")
    Call<PokemonListResponse> getPokemonList();

    @GET("pokemon/{id}")
    Call<Pokemon>getPokemonDetail(@Path("id") String pokemonld);
}
