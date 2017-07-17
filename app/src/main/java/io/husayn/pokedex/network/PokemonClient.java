package io.husayn.pokedex.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by husaynhakeem on 7/14/17.
 */
public class PokemonClient {

    private static final String POKEMON_REQUEST = "http://pokeapi.co/api/v2/";
    private static Retrofit instance;


    public static Retrofit withClient() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(POKEMON_REQUEST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
}
