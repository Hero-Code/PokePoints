/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.herocode.pokepoints.repository;

import java.util.List;

/**
 *
 * @author kieckegard
 * @param <T>
 * @param <I>
 */
public interface Repository<T,I>
{
    void save(T obj);
    void update(T obj);
    T get(I obj);
    List<T> list();
}
