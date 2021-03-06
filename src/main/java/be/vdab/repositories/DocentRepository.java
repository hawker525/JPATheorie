package be.vdab.repositories;

import be.vdab.entities.Campus;
import be.vdab.entities.Docent;
import be.vdab.valueobjects.AantalDocentenPerWedde;
import be.vdab.valueobjects.VoornaamEnId;
import com.sun.istack.internal.Nullable;

import javax.print.Doc;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by Maarten Westelinck on 22/12/2016.
 *
 */
public class DocentRepository extends AbstractRepository{
    public Optional<Docent> read(long id) {
        return Optional.ofNullable(getEntityManager().find(Docent.class, id));
    }

    public BigDecimal findMaxWedde() {
        return getEntityManager().createQuery("select max(d.wedde) from Docent d", BigDecimal.class).getSingleResult();
    }

    public void create(Docent docent) {
        getEntityManager().persist(docent);
    }

    public void algemeneOpslag(BigDecimal factor) {
        getEntityManager().createNamedQuery("Docent.algemeneOpslag")
                .setParameter("factor", factor)
                .executeUpdate();
    }

    public Optional<Docent> findByRijksRegisterNr(long rijskRegisterNr) {
        return Optional.ofNullable(getEntityManager()
                .createNamedQuery("Docent.findByRijksRegisterNr", Docent.class)
                .setParameter("rijksRegisterNr", rijskRegisterNr)
                .getSingleResult()
        );
    }

    public void delete(long id) {
        read(id).ifPresent(getEntityManager()::remove);
    }

    public List<AantalDocentenPerWedde> findAantalDocentenPerWedde() {
        return getEntityManager().createQuery("select new be.vdab.valueobjects.AantalDocentenPerWedde(d.wedde, count(d)) from Docent d group by d.wedde", AantalDocentenPerWedde.class).getResultList();
    }


    public List<VoornaamEnId> findVoornamen() {
        return getEntityManager().createQuery(
                "select new be.vdab.valueobjects.VoornaamEnId(d.id, d.voornaam) from Docent d", VoornaamEnId.class)
                .getResultList();
    }

    public List<Docent> findBestBetaaldeVanEenCampus(Campus campus) {
        return getEntityManager()
                .createNamedQuery("Docent.findBestBetaaldeVanEenCampus", Docent.class)
                .setParameter("campus", campus)
                .getResultList();
    }

    public List<Docent> findWeddeBetween(BigDecimal van, BigDecimal tot, int vanafRij, int aantalRijen) {
        return getEntityManager()
                .createNamedQuery("Docent.findByWeddeBetween", Docent.class)
                .setParameter("van", van)
                .setParameter("tot", tot)
                .setFirstResult(vanafRij)
                .setMaxResults(aantalRijen)
                .setHint("javax.persistence.loadgraph", getEntityManager().createEntityGraph(Docent.MET_CAMPUS))
                .getResultList();
    }
}
