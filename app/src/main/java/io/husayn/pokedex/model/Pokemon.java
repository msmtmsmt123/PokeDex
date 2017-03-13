package io.husayn.pokedex.model;

/**
 * Created by husaynhakeem on 3/12/17.
 */

public class Pokemon {

    private String mName;
    private String mSpritePath;

    public Pokemon() {
    }

    public Pokemon(String mSpritePath, String mName) {
        this.mSpritePath = mSpritePath;
        this.mName = mName;
    }

    public String getSpritePath() {
        return mSpritePath;
    }

    public void setSpritePath(String mSpritePath) {
        this.mSpritePath = mSpritePath;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}
