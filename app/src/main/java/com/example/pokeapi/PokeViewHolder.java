package com.example.pokeapi;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokeapi.databinding.ItemPokemonBinding;

public class PokeViewHolder extends RecyclerView.ViewHolder {

    ItemPokemonBinding itemPokemonBinding;

    public PokeViewHolder(ItemPokemonBinding binding) {
        super(binding.getRoot());//get root obtiene la vista del binding
        itemPokemonBinding = binding;
    }

    public void decorateWith(ShortPokemon model) {
        Glide.with(itemView.getContext()).load(model.getSprite()).into(itemPokemonBinding.ivPicture);
        itemView.getContext().getString(R.string.app_name);
        itemPokemonBinding.name.setText(model.getName());
    }


}
