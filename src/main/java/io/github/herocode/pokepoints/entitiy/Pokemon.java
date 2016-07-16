/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.herocode.pokepoints.entitiy;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author kieckegard && Victor Hugo
 */
public class Pokemon {

    private int             id;
    private String          name;
    private List<String>    types;
    private BufferedImage   image;
    private String          imageUrl;
    private List<PokePoint> pokePoints;

    public Pokemon(int id, String name, List<String> types, BufferedImage image, String imageUrl) {
        
        this.id = id;
        this.name = name;
        this.types = types;
        this.image = image;
        this.imageUrl = imageUrl;
    }

    public Pokemon(int id, String name, List<String> types, String imageUrl) {
        
        this.id = id;
        this.name = name;
        this.types = types;
        this.imageUrl = imageUrl;
        this.pokePoints = new ArrayList<>();
    }

    public BufferedImage getImage() {

        if (image == null) {
            
            try {
                image = ImageIO.read(new URL(imageUrl));
            } catch (IOException ex) {
                Logger.getLogger(Pokemon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getTypes() {
        return Collections.unmodifiableList(types);
    }

    public void addPokePoint(PokePoint pokePoint) {
        this.pokePoints.add(pokePoint);
    }

    public void addAll(List<PokePoint> pokePoints) {
        this.pokePoints.addAll(pokePoints);
    }

    public void removePokePoint(PokePoint pokePoint) {
        this.pokePoints.remove(pokePoint);
    }

    public List<PokePoint> getPokePoints() {
        return Collections.unmodifiableList(pokePoints);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Pokemon{" + "id=" + id + ", name=" + name + ", types=" + types + ", imageUrl=" + imageUrl + '}';
    }
}
