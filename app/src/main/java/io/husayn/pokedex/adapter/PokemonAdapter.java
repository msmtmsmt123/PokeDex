package io.husayn.pokedex.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.husayn.pokedex.R;
import io.husayn.pokedex.model.Pokemon;

/**
 * Created by husaynhakeem on 3/12/17.
 */

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private Context mContext;
    private ArrayList<Pokemon> mPokemons;

    public PokemonAdapter(Context context, ArrayList<Pokemon> pokemons) {
        mContext = context;
        mPokemons = pokemons;
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate item view
        View pokemonItemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_view_pokemon, parent, false);

        // Return new view holder
        return new PokemonViewHolder(mContext, pokemonItemView);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {

        // Get the current pokemon
        Pokemon currentPokemon = mPokemons.get(position);

        // Set the current pokemon's sprite
        holder.setPokemonSprite(currentPokemon.getSpritePath());

        // Set the current pokemon's name
        holder.setPokemonName(currentPokemon.getName());
    }

    /**
     * Method that adds pokemon objects to the list of the adapter's pokemons
     *
     * @param pokemons
     */
    public void addItems(ArrayList<Pokemon> pokemons) {
        if (mPokemons == null)
            mPokemons = new ArrayList<>();
        mPokemons.addAll(pokemons);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mPokemons != null)
            return mPokemons.size();
        return 0;
    }
}
