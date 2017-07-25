package io.husayn.pokedex.poke_discovery;

import java.util.ArrayList;
import java.util.List;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;

/**
 * Created by husaynhakeem on 7/25/17.
 */

public class PokemonFetcher {


    public static List<Pokemon> getPokemons() {

        List<Pokemon> pokemons = new ArrayList<>();
        PokeApi pokeApi = new PokeApiClient();

        for (int i = 1; i <= 25; i++)
            pokemons.add(pokeApi.getPokemon(i));

        return pokemons;
    }
}
