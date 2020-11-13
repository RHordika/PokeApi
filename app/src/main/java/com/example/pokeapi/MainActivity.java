package com.example.pokeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pokeapi.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PokemonAdapter.PokemonClickListener{

    ActivityMainBinding binding;
    PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new PokemonAdapter(this);
        adapter.setPokemonClickListener(this);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerView.setAdapter(adapter);


        ApiClient apiClient = new ApiClient(this);
        apiClient.getPokemonList().enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                if(response.isSuccessful()){
                    binding.progressBar.setVisibility(View.GONE);
                    adapter.setItems(response.body().getResults());
                }
            }
            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problema con el server",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onPokemonClicked(ShortPokemon pokemon) {
        Intent intent = new Intent(this, PokeDetailActivity.class);
        intent.putExtra("EXTRA", pokemon.getID());
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Name: " + pokemon.getName(), Toast.LENGTH_LONG).show();
    }
}