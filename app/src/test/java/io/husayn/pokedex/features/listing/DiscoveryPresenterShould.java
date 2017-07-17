package io.husayn.pokedex.features.listing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by husaynhakeem on 7/15/17.
 */
public class DiscoveryPresenterShould {


    private DiscoveryContract.View view;
    private DiscoveryAgent agent;
    private DiscoveryContract.Presenter presenter;

    @Before
    public void setUp() throws Exception {

        view = Mockito.mock(DiscoveryContract.View.class);
        agent = Mockito.mock(DiscoveryAgent.class);
        presenter = new DiscoveryPresenter(view, agent);
    }


    @Test
    public void bindPokemonsToRecyclerViewOnLoadingSuccess() throws Exception {
        Mockito.when(agent.getPokemons(Mockito.anyInt())).thenReturn(true);
    }
}