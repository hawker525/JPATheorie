package be.vdab.services;

import be.vdab.entities.Campus;
import be.vdab.repositories.CampusRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Maarten Westelinck on 19/01/2017 for fietsacademy.
 */
public class CampusService extends AbstractService{
    private final CampusRepository campusRepository = new CampusRepository();

    public List<Campus> findByGemeente(String gemeente) {
        return campusRepository.findByGemeente(gemeente);
    }

    public List<Campus> findAll() {
        return campusRepository.findAll();
    }

    public Optional<Campus> read(long id) {
        return campusRepository.read(id);
    }
}
