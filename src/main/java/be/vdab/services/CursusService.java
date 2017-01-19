package be.vdab.services;

import be.vdab.entities.Cursus;
import be.vdab.repositories.CursusRepository;

import java.util.List;

/**
 * Created by Maarten Westelinck on 17/01/2017 for fietsacademy.
 *
 */
public class CursusService extends AbstractService {
    private final CursusRepository cursusRepository = new CursusRepository();

    public List<Cursus> findByNaamContains(String woord) {
        return cursusRepository.findByNaamContains(woord);
    }

}
