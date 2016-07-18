/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.herocode.pokepoints.entitiy;

import io.github.herocode.pokepoints.rest.client.PokeApiClient;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.persistence.AssociationOverride;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Persistence;

/**
 *
 * @author kieckegard && Victor Hugo
 */
@Entity
public class Pokemon implements Serializable{

    @Id
    private int             id;
    private String          name;
    private String          imageUrl;
    
    @ElementCollection
    @CollectionTable(name = "pokemon_types")
    private List<String>    types;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
    
    @ManyToMany
    @JoinTable(
      name="pokemon_pokepoints",
      joinColumns=@JoinColumn(name="pokemon_id", referencedColumnName="id"),
      inverseJoinColumns=@JoinColumn(name="pokepoint_id", referencedColumnName="id"))
    private List<PokePoint> pokePoints;

    public Pokemon() {
        
    }
    
    public Pokemon(int id, String name, List<String> types, byte[] image, String imageUrl) {
        
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

    public byte[] getImage() {

        if (image == null) {
            
            try {
                BufferedImage read = ImageIO.read(new URL(imageUrl));
                
                image = ((DataBufferByte) read.getData().getDataBuffer()).getData();
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

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setPokePoints(List<PokePoint> pokePoints) {
        this.pokePoints = pokePoints;
    }
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
       
        PokeApiClient pac = new PokeApiClient();
        
        Pokemon p = pac.getById(6);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PokepointsPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
                
    }
}
