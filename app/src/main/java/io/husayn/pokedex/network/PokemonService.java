package io.husayn.pokedex.network;

import io.husayn.pokedex.model.PokemonList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by husaynhakeem on 7/14/17.
 */

public interface PokemonService {

    String POKEMON_REQUEST_PATH = "pokemon";
    String OFFSET_PARAM_KEY = "offset";

    @GET(POKEMON_REQUEST_PATH)
    Call<PokemonList> getPokemons(@Query(OFFSET_PARAM_KEY) int offset);
}
