package be.vdab.repositories;

import be.vdab.entities.Campus;

import java.util.List;
import java.util.Optional;

/**
 * Created by Maarten Westelinck on 19/01/2017 for fietsacademy.
 */
public class CampusRepository extends AbstractRepository {

    public List<Campus> findByGemeente(String gemeente) {
        return getEntityManager().createNamedQuery("Campus.findByGemeente", Campus.class)
                .setParameter("gemeente", gemeente)
                .getResultList();
    }

    public List<Campus> findAll() {
        return getEntityManager().createNamedQuery("Campus.findAll", Campus.class)
                .getResultList();
    }

    public Optional<Campus> read(long id) {
        return Optional.ofNullable(getEntityManager().find(Campus.class, id));
    }

}
