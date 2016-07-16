/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.herocode.pokepoints.rest.client;

import io.github.herocode.pokepoints.entitiy.Pokemon;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieckegard
 */
public class PokeApiClientTest
{
    public static void main(String[] args)
    {
        PokeApiClient client = new PokeApiClient();
        
        try
        {
            for(Pokemon pokemon : client.getAllPokemonsBetween(1, 3)){
                System.out.println(pokemon);
            }
        }
        catch (FileNotFoundException | MalformedURLException ex){
            
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
