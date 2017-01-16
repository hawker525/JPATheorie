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
public class DocentService {
    private final DocentRepository docentRepository = new DocentRepository();

    public Optional<Docent> read(long id) {
        EntityManager entityManager = JPAFilter.getEntityManager();
        try{
            return docentRepository.read(id, entityManager);
        } finally {
            entityManager.close();
        }
    }

    public void opslag(long id, BigDecimal percentage) {
        EntityManager entityManager = JPAFilter.getEntityManager();
        entityManager.getTransaction().begin();
        try {
            docentRepository.read(id, entityManager).ifPresent(d -> d.opslag(percentage));
            entityManager.getTransaction().commit();
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    public void create(Docent docent) {
        EntityManager entityManager = JPAFilter.getEntityManager();
        entityManager.getTransaction().begin();
        try{
            docentRepository.create(docent, entityManager);
            entityManager.getTransaction().commit();
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    public void delete(long id) {
        EntityManager entityManager = JPAFilter.getEntityManager();
        entityManager.getTransaction().begin();
        try {
            docentRepository.delete(id, entityManager);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

}
