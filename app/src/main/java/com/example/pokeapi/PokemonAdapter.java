package com.example.pokeapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokeapi.databinding.ItemPokemonBinding;

import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokeViewHolder> {

    private List<ShortPokemon> items = new ArrayList<>();

    private Context context;

    public PokemonAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<ShortPokemon> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokeViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(context)));
    }

    @Override
    public void onBindViewHolder(@NonNull PokeViewHolder holder, int position) {
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

    interface PokemonClickListener {
        void onPokemonClicked(ShortPokemon pokemon);
    }
}
