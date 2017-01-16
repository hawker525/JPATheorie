package be.vdab.repositories;

import be.vdab.filters.JPAFilter;

import javax.persistence.EntityManager;

/**
 * Created by Maarten Westelinck on 16/01/2017 for fietsacademy.
 *
 */
public abstract class AbstractRepository {
    EntityManager getEntityManager(){
        return JPAFilter.getEntityManager();
    }
}
