package com.example.pokeapi.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokeapi.databinding.ItemFavoritePokemonBinding;
import com.example.pokeapi.domain.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {

    private List<Pokemon> items = new ArrayList<>();

    private Context context;

    public FavoritesAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<Pokemon> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoritesViewHolder(ItemFavoritePokemonBinding.inflate(LayoutInflater.from(context)));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        holder.decorateWith(items.get(position));
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPokemonClicked(items.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private PokemonClickListener listener;

    public void setPokemonClickListener(PokemonClickListener listener) {
        this.listener = listener;
    }

    public interface PokemonClickListener {
        void onPokemonClicked(Pokemon pokemon);
    }

    public void removePokemon(Pokemon pokemon) {
        items.remove(pokemon);
        notifyDataSetChanged();
    }

}
