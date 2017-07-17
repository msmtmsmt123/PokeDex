package io.husayn.pokedex.features.listing;

import android.support.annotation.NonNull;

import io.husayn.pokedex.model.PokemonList;
import io.husayn.pokedex.network.PokemonClient;
import io.husayn.pokedex.network.PokemonService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by husaynhakeem on 7/14/17.
 */

public class DiscoveryAgent {


    private static DiscoveryPresenter presenter;
    private static DiscoveryAgent agent;


    public static DiscoveryAgent withPresenter(DiscoveryPresenter discoveryPresenter) {
        if (presenter == null)
            presenter = discoveryPresenter;
        if (agent == null)
            agent = new DiscoveryAgent();
        return agent;
    }


    public boolean getPokemons(int offset) {
        PokemonService pokemonService = PokemonClient.withClient().create(PokemonService.class);

        Call<PokemonList> call = pokemonService.getPokemons(offset);
        call.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(@NonNull Call<PokemonList> call, @NonNull Response<PokemonList> response) {
                try {
                    presenter.onPokemonsLoadedSuccess(response.body().getPokemons());
                } catch(NullPointerException e) {
                    presenter.onPokemonsLoadedFailure();
                }
            }
            @Override
            public void onFailure(@NonNull Call<PokemonList> call, Throwable t) {
                presenter.onPokemonsLoadedFailure();
            }
        });
        return true;
    }
}
