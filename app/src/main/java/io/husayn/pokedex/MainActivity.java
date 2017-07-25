package io.husayn.pokedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static io.husayn.pokedex.poke_discovery.PokemonFetcher.getPokemons;

/**
 * Created by husaynhakeem on 3/12/17.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.just(getPokemons())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pokemons -> Timber.d("Size of the list %d", pokemons.size()));
    }
}
