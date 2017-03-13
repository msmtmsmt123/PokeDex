package io.husayn.pokedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.husayn.pokedex.adapter.PokemonAdapter;
import io.husayn.pokedex.model.Pokemon;

/**
 * Created by husaynhakeem on 3/12/17.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    // Url for the request that retrieves the names of the pokemon
    private final String POKEMON_NAME_REQUEST = "http://pokeapi.co/api/v2/pokemon/?offset=";

    // Elements of the url for the request that retrieves the sprites of the pokemon
    private final String POKEMON_SPRITE_REQUEST = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    private final String POKEMON_SPRITE_FORMAT = ".png";

    // Url of a certain pokemon (minus its Id)
    private final String POKEMON_URL_STATIC = "http://pokeapi.co/api/v2/pokemon/";

    // Json attribute keys
    private final String POKEMON_COUNT = "count";
    private final String POKEMON_RESULTS = "results";
    private final String POKEMON_URL = "url";
    private final String POKEMON_NAME = "name";

    private RecyclerView mPokemonRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
    private PokemonAdapter mPokemonAdapter;

    private TextView mLoadingTextView;

    private Cache mCache;
    private Network mNetwork;
    private RequestQueue mRequestQueue;
    private JsonObjectRequest mPokemonNamesRequest;

    // Total number of available pokemons
    private int mPokemonsCount;

    // Number of pokemons retrieved after each API call
    private final int mPokemonCountPerRequest = 20;

    // Offset used in the API call
    private static int sCurrentOffset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadingTextView = (TextView) findViewById(R.id.loading);
        mPokemonRecyclerView = (RecyclerView) findViewById(R.id.pokemon_recycler_view);

        // Setting the layout manager and animator for the recycler view
        mLayoutManager = new StaggeredGridLayoutManager(3, GridLayoutManager.VERTICAL);
        mPokemonRecyclerView.setLayoutManager(mLayoutManager);
        mPokemonRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // Setting the adapter for the recycler view
        mPokemonAdapter = new PokemonAdapter(MainActivity.this, null);
        mPokemonRecyclerView.setAdapter(mPokemonAdapter);

        // Setting a scroll listener for the recycler view
        mPokemonRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int[] lastCompletelyVisibleItems = new int[3];
                    mLayoutManager.findLastCompletelyVisibleItemPositions(lastCompletelyVisibleItems);

                    if (max(lastCompletelyVisibleItems) > (mPokemonAdapter.getItemCount() - mPokemonCountPerRequest / 2)
                            && mPokemonAdapter.getItemCount() < mPokemonsCount)
                        newPokemonRequest();
                }
            }
        });

        // Initialize network-related parameters
        prepareNetworkParameters();

        // Send first request
        newPokemonRequest();
    }

    /**
     * @param integerArray: Array of integers
     * @return: Maximum value in the array
     */
    private int max(int[] integerArray) {
        int max = integerArray[0];
        for (int anInteger : integerArray) {
            if (anInteger > max)
                max = anInteger;
        }
        return max;
    }

    /**
     * Method that prepares network related variables such
     * as the cache, network and request queue objects
     */
    private void prepareNetworkParameters() {

        // Instantiate the cache (1MB capacity)
        mCache = new DiskBasedCache(getCacheDir(), 1024 * 1024);

        // Set up the network to use HttpURLConnection as the HTTP client
        mNetwork = new BasicNetwork(new HurlStack());

        // Instantiate the request queue
        mRequestQueue = new RequestQueue(mCache, mNetwork);
        mRequestQueue.start();
    }

    /**
     * Method that handles the API call and its response
     */
    private void newPokemonRequest() {

        // Display loading message
        displayLoadingMessage(true);

        // Create new request
        mPokemonNamesRequest = new JsonObjectRequest(Request.Method.GET,
                POKEMON_NAME_REQUEST + sCurrentOffset,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, response.toString());
                        convertToPokemons(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage());
                        displayLoadingMessage(false);
                        if (sCurrentOffset > 0)
                            sCurrentOffset -= mPokemonCountPerRequest;
                    }
                });

        // Add newly created request to request queue
        mRequestQueue.add(mPokemonNamesRequest);
        sCurrentOffset += mPokemonCountPerRequest;
    }

    /**
     * Method that converts a json string to a list of pokemon objects
     *
     * @param pokemonsJson
     */
    private void convertToPokemons(String pokemonsJson) {
        try {
            // Json object of the request call
            JSONObject pokemonResultJsonObject = new JSONObject(pokemonsJson);

            // Set pokemons count
            mPokemonsCount = pokemonResultJsonObject.getInt(POKEMON_COUNT);

            // Get list of returned pokemons
            JSONArray pokemonJsonArray = pokemonResultJsonObject.getJSONArray(POKEMON_RESULTS);
            ArrayList<Pokemon> pokemons = new ArrayList<>();
            JSONObject tempJsonPokemon;

            for (int i = 0; i < pokemonJsonArray.length(); i++) {
                tempJsonPokemon = pokemonJsonArray.getJSONObject(i);
                pokemons.add(
                        new Pokemon(
                                POKEMON_SPRITE_REQUEST + getPokemonIndex(tempJsonPokemon.getString(POKEMON_URL)) + POKEMON_SPRITE_FORMAT,
                                tempJsonPokemon.getString(POKEMON_NAME)
                        )
                );
            }

            // Update recycler view
            mPokemonAdapter.addItems(pokemons);

            // Hide loading message
            displayLoadingMessage(false);

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * @param pokemonUrl: Url to a single pokemon, contains its Id
     * @return: the id of a pokemon, 1 by default
     */
    private String getPokemonIndex(String pokemonUrl) {
        try {
            String lastPartOfUrl = pokemonUrl.replace(POKEMON_URL_STATIC, "");
            return lastPartOfUrl.substring(0, lastPartOfUrl.length() - 1);
        } catch (StringIndexOutOfBoundsException e) {
            Log.e(TAG, e.getMessage());
            return "1";
        }
    }

    /**
     * Method that displays of hides the loading message text view
     *
     * @param toDisplay: Boolean indicating whether to show or hide the
     *                   loading message text view
     */
    private void displayLoadingMessage(boolean toDisplay) {
        mLoadingTextView.setVisibility(toDisplay ? View.VISIBLE : View.GONE);
    }
}
