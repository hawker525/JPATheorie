package be.vdab.services;

import be.vdab.entities.Docent;
import be.vdab.exceptions.DocentBestaatAlException;
import be.vdab.filters.JPAFilter;
import be.vdab.repositories.DocentRepository;
import be.vdab.valueobjects.AantalDocentenPerWedde;
import be.vdab.valueobjects.VoornaamEnId;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
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

    public List<Docent> findByWeddeBetween(BigDecimal van, BigDecimal tot, int vanafRij, int aantalRijen) {
        return docentRepository.findWeddeBetween(van, tot, vanafRij, aantalRijen);
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

    public void bijnaamToevoegen(long id, String bijnaam) {
        beginTransaction();
        try {
            docentRepository.read(id).ifPresent(d -> d.addBijnaam(bijnaam));
            commit();
        } catch (RuntimeException ex) {
            rollback();
            throw ex;
        }
    }

    public void bijnamenVerwijderen(long id, String[] bijnamen) {
        beginTransaction();
        try {
            docentRepository.read(id).ifPresent(docent -> Arrays.stream(bijnamen).forEach(docent::removeBijnaam));
            commit();
        } catch (RuntimeException ex) {
            rollback();
            throw ex;
        }
    }

    public void algemeneOpslag(BigDecimal percentage) {
        BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
        try {
            beginTransaction();
            docentRepository.algemeneOpslag(factor);
            commit();
        } catch (RuntimeException ex) {
            rollback();
            throw ex;
        }
    }

    public List<AantalDocentenPerWedde> findAantalDocentenPerWedde() {
        return docentRepository.findAantalDocentenPerWedde();
    }

    public BigDecimal findMaxWedde() {
        return docentRepository.findMaxWedde();
    }

    public List<VoornaamEnId> findVoornaamEnId() {
        return docentRepository.findVoornamen();
    }

    public void create(Docent docent) {
        if(docentRepository.findByRijksRegisterNr(docent.getRijksRegisterNr()).isPresent()) throw new DocentBestaatAlException();
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
