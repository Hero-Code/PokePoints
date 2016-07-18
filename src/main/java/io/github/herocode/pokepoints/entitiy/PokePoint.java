/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.herocode.pokepoints.entitiy;

import com.vividsolutions.jts.geom.Point;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author kieckegard
 */
@Entity
public class PokePoint implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int     id;
    private Point   point;
    private int     pokemonCp;
    private int     userLevel;

    public PokePoint() {
    }
    
    public PokePoint(Point point, int pokemonCp, int userLevel)
    {
        this.point = point;
        this.pokemonCp = pokemonCp;
        this.userLevel = userLevel;
    }
    
    public PokePoint(int id, Point point, int pokemonCp, int userLevel)
    {
        this.id        = id;
        this.point     = point;
        this.pokemonCp = pokemonCp;
        this.userLevel = userLevel;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }

    public Point getPoint()
    {
        return point;
    }

    public int getPokemonCp()
    {
        return pokemonCp;
    }

    public int getUserLevel()
    {
        return userLevel;
    }

    @Override
    public String toString()
    {
        return "PokePoint{" + "point=" + point + ", pokemonCp=" + pokemonCp + ", userLevel=" + userLevel + '}';
    }
}
