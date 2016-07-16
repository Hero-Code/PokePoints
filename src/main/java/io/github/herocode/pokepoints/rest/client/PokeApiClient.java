/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.herocode.pokepoints.rest.client;

import io.github.herocode.pokepoints.rest.model.Pokemon;
import io.github.herocode.pokepoints.rest.util.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class PokeApiClient
{
    private final String baseUrl = "http://pokeapi.co/api/v2/";
    
    public PokeApiClient(){
        
    }
    
    public List<Pokemon> getAllPokemonsUntil(int num) throws MalformedURLException, FileNotFoundException, IOException{
        List<Pokemon> pokemons = new ArrayList<>();
        for(int i=1;i<=num;i++){
            pokemons.add(getById(i));
        }
        return pokemons;
    }
    
    public List<Pokemon> getAllPokemonsBetween(int start, int end) throws MalformedURLException, FileNotFoundException, IOException{
        List<Pokemon> pokemons = new ArrayList<>();
        for(int i=start;i<=end;i++){
            pokemons.add(getById(i));
        }
        return pokemons;
    }
    
    public Pokemon getById(int id) throws MalformedURLException, FileNotFoundException, IOException{
        StringBuilder builder = new StringBuilder(baseUrl);
        builder.append("pokemon/");
        builder.append(id);
        builder.append("/");
        
        Pokemon pokemon = createPokemonByUrl(builder.toString());
        return pokemon;
    }
    
    private Pokemon createPokemonByUrl(String url) throws MalformedURLException, IOException{
        JsonObject json = JsonUtils.getJsonFromUrl(url);
        return createPokemonByJson(json);
    }
    
    private Pokemon createPokemonByJson(JsonObject json){
        List<String> pokemonTypes = new ArrayList<>();
        
        int id = json.get("id").getAsInt();
        String name = json.get("name").getAsString();
        String imageUrl = json.get("sprites").getAsJsonObject().get("front_default").getAsString();
        
        JsonArray jsonTypes = json.get("types").getAsJsonArray();
        for(JsonElement element : jsonTypes){
            JsonObject obj = element.getAsJsonObject();
            pokemonTypes.add(obj.get("type").getAsJsonObject().get("name").getAsString());
        }
        
        Pokemon pokemon = new Pokemon(id,name,pokemonTypes,imageUrl);
        
        return pokemon;
    }
    
}
