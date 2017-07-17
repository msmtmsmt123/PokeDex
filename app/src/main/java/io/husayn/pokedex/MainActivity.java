package io.husayn.pokedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    // Url for the request that retrieves the names of the pokemon


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
