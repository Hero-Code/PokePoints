/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.herocode.pokepoints.rest.client;

import io.github.herocode.pokepoints.entitiy.Pokemon;
import io.github.herocode.pokepoints.rest.util.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author kieckegard && Victor Hugo
 */
public class PokeApiClient {

    private final String baseUrl = "http://pokeapi.co/api/v2/";

    public PokeApiClient() {

    }

    public List<Pokemon> getAllPokemonsUntil(int num) throws MalformedURLException, FileNotFoundException, IOException {

        List<Pokemon> pokemons = new ArrayList<>();

        for (int i = 1; i <= num; i++) {
            pokemons.add(getById(i));
        }

        return pokemons;
    }

    public List<Pokemon> getAllPokemonsBetween(int start, int end) throws MalformedURLException, FileNotFoundException, IOException {

        List<Pokemon> pokemons = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            pokemons.add(getById(i));
        }

        return pokemons;
    }

    public Pokemon getById(int id) throws MalformedURLException, FileNotFoundException, IOException {

        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("pokemon/").
                append(id).
                append("/");

        Pokemon pokemon = createPokemonByUrl(urlBuilder.toString());

        return pokemon;
    }

    private Pokemon createPokemonByUrl(String url) throws MalformedURLException, IOException {

        JsonObject json = JsonUtils.getJsonFromUrl(url);

        return createPokemonByJson(json);
    }

    private Pokemon createPokemonByJson(JsonObject json) {

        List<String> pokemonTypes = new ArrayList<>();

        int id = json.get("id").getAsInt();
        String name = json.get("name").getAsString();
        String imageLocation = json.get("sprites").getAsJsonObject().get("front_default").getAsString();

        JsonArray jsonTypes = json.get("types").getAsJsonArray();

        for (JsonElement element : jsonTypes) {

            JsonObject obj = element.getAsJsonObject();
            JsonObject typeObj = obj.get("type").getAsJsonObject();

            pokemonTypes.add(typeObj.get("name").getAsString());
        }

        try {

            URL url = new URL(imageLocation);
            URLConnection openConnection = url.openConnection();

            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            InputStream inputStream = openConnection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);

            byte[] image = new byte[bis.available()];
            bis.read(image);
            bis.close();
            System.out.println(image.length);
            Pokemon pokemon = new Pokemon(id, name, pokemonTypes, image, imageLocation);

            return pokemon;

        } catch (IOException ex) {
            ex.printStackTrace();
            Pokemon pokemon = new Pokemon(id, name, pokemonTypes, imageLocation);

            return pokemon;

        }

    }

}
