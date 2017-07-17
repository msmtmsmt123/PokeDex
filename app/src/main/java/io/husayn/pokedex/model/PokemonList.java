package io.husayn.pokedex.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by husaynhakeem on 7/14/17.
 */
public class PokemonList {

    @SerializedName("count")
    private int count;

    @SerializedName("previous")
    private String previous;

    @SerializedName("results")
    private List<Pokemon> pokemons;

    @SerializedName("next")
    private String next;

    public PokemonList() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
