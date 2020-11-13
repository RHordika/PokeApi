package com.example.pokeapi;

public class ShortPokemon {

    private String name;
    private String url;

    public String getSprite(){
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + getID() + ".png";
    }

    public String getID(){
        String id = url.replace("https://pokeapi.co/api/v2/pokemon/", "");
        id = id.replace("/", "");
        return id;
    }

    public String getName() {
        return name;
    }

    public void setNameS(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
