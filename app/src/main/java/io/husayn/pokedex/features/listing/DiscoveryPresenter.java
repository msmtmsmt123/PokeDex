package io.husayn.pokedex.features.listing;

import java.util.List;

import io.husayn.pokedex.model.Pokemon;

/**
 * Created by husaynhakeem on 7/14/17.
 */

public class DiscoveryPresenter implements DiscoveryContract.Presenter {


    public DiscoveryPresenter(DiscoveryContract.View view, DiscoveryAgent agent) {
    }

    public void onPokemonsLoadedSuccess(List<Pokemon> pokemons) {
    }


    public void onPokemonsLoadedFailure() {
    }
}
