package com.example.juandavid.pokemon_home.Factory;

import com.example.juandavid.pokemon_home.Pokemon.Pokemon;

/**
 * Created by Juan David on 22/09/2017.
 */

public class PokeFactory {

    //Generador de pokemones
    public static Pokemon getPokemon(String tipo){

        if(tipo.equals("pokemon")){
            return new Pokemon();
        }
        return null;
    }
}
