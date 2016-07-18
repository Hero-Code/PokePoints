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

    private EntityManagerFactory entityManagerFactory;

    public PokemonRepository() {
        
        entityManagerFactory = Persistence.createEntityManagerFactory("PokepointsPU");
    }
    
    
    
    @Override
    public void save(Pokemon pokemon) {
        
        EntityManager entityManager = getEntityManager();
        
        entityManager.getTransaction().begin();
        entityManager.persist(pokemon);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(Pokemon pokemon) {
        
        EntityManager entityManager = getEntityManager();
        
        entityManager.getTransaction().begin();
        entityManager.merge(pokemon);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Pokemon> list() {
        
        EntityManager entityManager = getEntityManager();
        
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT p FROM Pokemon p");

        List<Pokemon> pokemons = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();  
        
        return pokemons;
    }

    @Override
    public Pokemon get(Integer id) {
        
        EntityManager entityManager = getEntityManager();
        
        entityManager.getTransaction().begin();
        Pokemon pokemon = entityManager.find(Pokemon.class, id);

        entityManager.getTransaction().commit();
        entityManager.close();       
        
        return pokemon;
    }

    @Override
    public EntityManager getEntityManager() {

        EntityManager entityManager;
        entityManager = entityManagerFactory.createEntityManager();
        
        return entityManager;
    }
    
}
