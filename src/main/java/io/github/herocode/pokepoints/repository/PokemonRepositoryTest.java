/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.herocode.pokepoints.repository;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import io.github.herocode.pokepoints.entitiy.PokePoint;
import io.github.herocode.pokepoints.entitiy.Pokemon;

/**
 *
 * @author kieckegard
 */
public class PokemonRepositoryTest
{
    public static void main(String[] args) throws ParseException
    {
        PokemonRepository rep = new PokemonRepository();
        
        Pokemon pokemon = rep.get(6);
        WKTReader reader = new WKTReader();
        
        Point p = (Point) reader.read("POINT(-2.3332431 43.456456)");
        
        pokemon.addPokePoint(new PokePoint(p,85,4));
        
        rep.update(pokemon);
    }
}
