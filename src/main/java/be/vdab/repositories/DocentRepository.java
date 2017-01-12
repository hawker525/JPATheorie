package be.vdab.repositories;

import be.vdab.entities.Docent;
import be.vdab.filters.JPAFilter;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * Created by Maarten Westelinck on 22/12/2016.
 *
 */
public class DocentRepository {
    public Optional<Docent> read(long id, EntityManager entityManager) {
        return Optional.ofNullable(entityManager.find(Docent.class, id));
    }

    public void create(Docent docent, EntityManager entityManager) {
        entityManager.persist(docent);
    }
}
