package io.husayn.pokedex.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.husayn.pokedex.MainActivity;
import io.husayn.pokedex.R;

/**
 * Created by husaynhakeem on 3/12/17.
 */

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    @BindView(R.id.pokemon_sprite) private ImageView mPokemonSprite;
    @BindView(R.id.pokemon_name)private TextView mPokemonName;

    public PokemonViewHolder(Context context, View itemView) {
        super(itemView);

        // Set the context
        mContext = context;

        ButterKnife.bind((MainActivity) mContext);
    }

    /**
     * Method that sets the pokemon's sprite
     *
     * @param spritePath
     */
    public void setPokemonSprite(String spritePath) {
        Picasso.with(mContext)
                .load(spritePath)
                .into(mPokemonSprite);
    }

    /**
     * Method that sets the pokemon's name
     *
     * @param pokemonName
     */
    public void setPokemonName(String pokemonName) {
        mPokemonName.setText(pokemonName);
    }
}
