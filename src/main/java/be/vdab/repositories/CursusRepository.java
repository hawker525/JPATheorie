package be.vdab.repositories;

import be.vdab.entities.Cursus;

import java.util.List;

/**
 * Created by Maarten Westelinck on 17/01/2017 for fietsacademy.
 */
public class CursusRepository extends AbstractRepository {

    public List<Cursus> findByNaamContains(String woord) {
        return getEntityManager().createNamedQuery("Cursus.findByNaamContains", Cursus.class)
                .setParameter("zoals", '%' + woord + '%')
                .getResultList();
    }

}
