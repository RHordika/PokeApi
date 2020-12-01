package com.example.pokeapi.data;

import android.content.Context;

import androidx.room.Room;

public class PokeDataBase {

    public static AppDataBase getDataBase(Context context){

        return Room.databaseBuilder(context, AppDataBase.class, "pokemon-database").build();
    }

}
