package io.husayn.pokedex.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by husaynhakeem on 3/12/17.
 */

public class Pokemon {

    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String name;

    private String image;

    public Pokemon() {
    }

    public Pokemon(String url, String name, String image) {
        this.url = url;
        this.name = name;
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        this.name = mName;
    }
}
