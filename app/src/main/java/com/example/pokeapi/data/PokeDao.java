package com.example.pokeapi.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pokeapi.domain.Pokemon;

import java.util.List;

@Dao
public interface PokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPokemon(Pokemon pokemon);

    @Delete
    void deletePokemon(Pokemon pokemon);

    @Query("SELECT * FROM pokemon")
    List<Pokemon> getAllPokemon();
    
    
}
