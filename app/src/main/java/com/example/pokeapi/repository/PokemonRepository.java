package com.example.pokeapi.repository;

import android.os.Handler;

import com.example.pokeapi.data.AppDataBase;
import com.example.pokeapi.domain.Pokemon;

import java.util.concurrent.Executor;

public class PokemonRepository {

    private Executor executor;
    private Handler handler;
    private AppDataBase db;

    public PokemonRepository(Executor executor, Handler handler, AppDataBase db) {
        this.executor = executor;
        this.handler = handler;
        this.db = db;
    }

    public void addPokemonToDB (Pokemon pokemon){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.pokeDao().insertPokemon(pokemon);
            }
        });

    }

    public void deletePokemonToDB (Pokemon pokemon){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.pokeDao().deletePokemon(pokemon);
            }
        });

    }

    public void getPokemonList(PokeCallback pokeCallback){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                pokeCallback.callback(db.pokeDao().getAllPokemon());
            }
        }
        );
    }

}
