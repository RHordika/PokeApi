package com.example.pokeapi.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import com.bumptech.glide.Glide;
import com.example.pokeapi.R;
import com.example.pokeapi.data.PokeDataBase;
import com.example.pokeapi.databinding.ActivityPokemonDetailBinding;
import com.example.pokeapi.domain.Pokemon;
import com.example.pokeapi.repository.ApiClient;
import com.example.pokeapi.repository.PokemonRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokeDetailActivity extends AppCompatActivity {


    private ActivityPokemonDetailBinding binding;

    ExecutorService executorService = Executors.newFixedThreadPool(4);
    Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

    PokemonRepository repository;
    private Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPokemonDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String id = getIntent().getStringExtra("EXTRA");


        repository = new PokemonRepository(executorService, mainThreadHandler, PokeDataBase.getDataBase(getApplicationContext()));
        binding.addFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository.addPokemonToDB(pokemon);
            }
        });


        ApiClient apiClient = new ApiClient(this);
        apiClient.getPokemonDetail(id).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if(response.isSuccessful()){
                    //binding.progressBar.setVisibility(View.GONE);
                    setupToolbar(response.body());
                    setData(response.body());
                }
            }
            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problema con el server",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setupToolbar(Pokemon pokemon) {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        binding.toolbar.setTitle(pokemon.getName());
    }
    private void setData(Pokemon pokemon) {
        this.pokemon = pokemon;
        Glide.with(this).load(pokemon.getSprite()).into(binding.ivPicture);
        binding.tvName.setText(getString(R.string.poke_name,pokemon.getName()));
        binding.tvOrder.setText(getString(R.string.poke_order,pokemon.getOrder()));

        String types = "";
        for (int i = 0; i < pokemon.getTypes().size(); i++){
                    //.size() indica tamaño
            types = types + pokemon.getTypes().get(i).getType().getName() + " "; //representa una posicion en una lista,

            // despues the cada punto es una capa

        }

        binding.tvTypes.setText(getString(R.string.poke_types, types));
        binding.tvWeight.setText(getString(R.string.poke_weight, pokemon.getWeight()));
        binding.tvExp.setText(getString(R.string.poke_exp, pokemon.getExp()));
    }

}
