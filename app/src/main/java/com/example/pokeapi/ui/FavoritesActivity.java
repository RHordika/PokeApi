package com.example.pokeapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.pokeapi.data.PokeDataBase;
import com.example.pokeapi.databinding.ActivityFavoritesBinding;
import com.example.pokeapi.domain.Pokemon;
import com.example.pokeapi.repository.PokeCallback;
import com.example.pokeapi.repository.PokemonRepository;
import com.example.pokeapi.ui.FavoritesAdapter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesActivity extends AppCompatActivity {

    ActivityFavoritesBinding binding;
    PokemonRepository repository;

    FavoritesAdapter adapter;

    ExecutorService executorService = Executors.newFixedThreadPool(4);
    Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoritesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = new PokemonRepository(executorService, mainThreadHandler, PokeDataBase.getDataBase(getApplicationContext()));

        setupToolbar();
        repository.getPokemonList(new PokeCallback(){
            @Override
            public void callback(List<Pokemon> pokemons){
                adapter.setItems(pokemons);
            }


        });


        adapter = new FavoritesAdapter(this);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerView.setAdapter(adapter);

        adapter.setPokemonClickListener(new FavoritesAdapter.PokemonClickListener() {
            @Override
            public void onPokemonClicked(Pokemon pokemon) {
                deletePokemon(pokemon);
            }
        });
    }

    private void deletePokemon(Pokemon pokemon){
        repository.deletePokemonToDB(pokemon);
        adapter.removePokemon(pokemon);
    }

    private void setupToolbar(){

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        binding.toolbar.setTitle("Favorites");

    }
}