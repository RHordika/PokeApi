package com.example.pokeapi.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pokeapi.domain.Pokemon;

@Database(entities = {Pokemon.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PokeDao pokeDao();
}

