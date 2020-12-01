package com.example.pokeapi.ui;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokeapi.R;
import com.example.pokeapi.databinding.ItemFavoritePokemonBinding;
import com.example.pokeapi.domain.Pokemon;

public class FavoritesViewHolder extends RecyclerView.ViewHolder{

    ItemFavoritePokemonBinding itemFavoritePokemonBinding;

    public FavoritesViewHolder(ItemFavoritePokemonBinding binding) {
        super(binding.getRoot());//get root obtiene la vista del binding
        itemFavoritePokemonBinding = binding;
    }

    public FavoritesViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void decorateWith(Pokemon model) {
        Glide.with(itemView.getContext()).load(model.getSprite()).into(itemFavoritePokemonBinding.ivPicture);
        itemView.getContext().getString(R.string.app_name);
        itemFavoritePokemonBinding.name.setText(model.getName());
    }

}
