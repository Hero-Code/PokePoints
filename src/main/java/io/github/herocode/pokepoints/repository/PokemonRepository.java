/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.herocode.pokepoints.repository;

import io.github.herocode.pokepoints.entitiy.Pokemon;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author kieckegard
 */
public class PokemonRepository implements Repository<Pokemon, Integer> {
     
    private final EntityManagerFactory  entityManagerFactory;
    private final EntityManager         entityManager;
    
    public PokemonRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("PokepointsPU");
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    /*
        I'm not closing the entityManager because there'll be moments that we'll call two methods from the same Repository,
        one after the other, So... once that each method closes the entityManager we'll have a really beautiful NullPointException
        when we try to call another method. I'm studying some possibilities to make this shit works wonderfully. <3
    */
    
    @Override
    public void save(Pokemon pokemon) {
        entityManager.getTransaction().begin();
        entityManager.persist(pokemon);
        entityManager.getTransaction().commit();
        //entityManager.close();
    }
    
    @Override
    public void update(Pokemon pokemon) {
        entityManager.getTransaction().begin();
        entityManager.merge(pokemon);
        entityManager.getTransaction().commit();
        //entityManager.close();
    }
    
    @Override
    public List<Pokemon> list() {
        entityManager.getTransaction().begin();   
        Query query = entityManager.createQuery("SELECT p FROM Pokemon p");   
        
        List<Pokemon> pokemons = query.getResultList();
        
        entityManager.getTransaction().commit();       
        //entityManager.close();      
        return pokemons;
    }
    
    @Override
    public Pokemon get(Integer id) {
        entityManager.getTransaction().begin();         
        Pokemon pokemon =  entityManager.find(Pokemon.class, id);
        
        entityManager.getTransaction().commit();    
        //entityManager.close();       
        return pokemon;
    }
}
