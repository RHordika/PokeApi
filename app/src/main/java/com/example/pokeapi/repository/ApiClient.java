package com.example.pokeapi.repository;

import android.content.Context;

import com.example.pokeapi.domain.Pokemon;
import com.example.pokeapi.domain.PokemonListResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    Retrofit retrofit;
    Context context;

    public ApiClient(Context context) {
        this.context = context;

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
    }

    public Call<PokemonListResponse> getPokemonList() {
        Api service = retrofit.create(Api.class);
        return service.getPokemonList();
    }

    public Call<Pokemon> getPokemonDetail(String id) {
        Api service = retrofit.create(Api.class);
        return service.getPokemonDetail(id);
    }
}