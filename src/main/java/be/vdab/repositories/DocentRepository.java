package be.vdab.repositories;

import be.vdab.entities.Docent;
import be.vdab.filters.JPAFilter;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * Created by Maarten Westelinck on 22/12/2016.
 *
 */
public class DocentRepository extends AbstractRepository{
    public Optional<Docent> read(long id) {
        return Optional.ofNullable(getEntityManager().find(Docent.class, id));
    }

    public void create(Docent docent) {
        getEntityManager().persist(docent);
    }

    public void delete(long id) {
        read(id).ifPresent(getEntityManager()::remove);
    }
}
