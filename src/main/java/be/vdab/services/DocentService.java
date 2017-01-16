package be.vdab.services;

import be.vdab.entities.Docent;
import be.vdab.filters.JPAFilter;
import be.vdab.repositories.DocentRepository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by Maarten Westelinck on 12/01/2017 for fietsacademy.
 *
 */
public class DocentService extends AbstractService{
    private final DocentRepository docentRepository = new DocentRepository();

    public Optional<Docent> read(long id) {
        return docentRepository.read(id);
    }

    public void opslag(long id, BigDecimal percentage) {
        beginTransaction();
        try {
            docentRepository.read(id).ifPresent(d -> d.opslag(percentage));
            commit();
        } catch (RuntimeException ex) {
            rollback();
            throw ex;
        }
    }

    public void create(Docent docent) {
        beginTransaction();
        try {
            docentRepository.create(docent);
            commit();
        } catch (RuntimeException ex) {
            rollback();
            throw ex;
        }
    }

    public void delete(long id) {
        beginTransaction();
        try {
            docentRepository.delete(id);
            commit();
        } catch (RuntimeException ex) {
            rollback();
            throw ex;
        }
    }

}
