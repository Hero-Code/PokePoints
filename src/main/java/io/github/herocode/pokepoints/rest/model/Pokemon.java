/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.herocode.pokepoints.rest.model;

import io.github.herocode.pokepoints.entitiy.PokePoint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class Pokemon
{
    private int    id;
    private String name;
    private List<String> types;
    private String imageUrl;
    private List<PokePoint> pokePoints;
    
    public Pokemon(int id, String name, List<String> types, String imageUrl){
        this.id         = id;
        this.name       = name;
        this.types      = types;
        this.imageUrl   = imageUrl;
        this.pokePoints = new ArrayList<>();
    }
    
    public Pokemon(int id, String name, String imageUrl){
        this.id        = id;
        this.name      = name;
        this.types     = new ArrayList<>();
        this.imageUrl  = imageUrl;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public List<String> getTypes()
    {
        return Collections.unmodifiableList(types);
    }
    
    public void addPokePoint(PokePoint pokePoint){
        this.pokePoints.add(pokePoint);
    }
    
    public void addAll(List<PokePoint> pokePoints){
        this.pokePoints.addAll(pokePoints);
    }
    
    public void removePokePoint(PokePoint pokePoint){
        this.pokePoints.remove(pokePoint);
    }
    
    public List<PokePoint> getPokePoints(){
        return Collections.unmodifiableList(pokePoints);
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    @Override
    public String toString()
    {
        return "Pokemon{" + "id=" + id + ", name=" + name + ", types=" + types + ", imageUrl=" + imageUrl + '}';
    }        
}
