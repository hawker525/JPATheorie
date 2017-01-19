package be.vdab.entities;

import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.TelefoonNr;

import javax.persistence.*;
import javax.print.Doc;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Maarten Westelinck on 19/01/2017 for fietsacademy.
 */
@Entity
@Table(name = "campussen")
public class Campus implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @Embedded
    private Adres adres;

    @ElementCollection
    @CollectionTable(name = "campussentelefoonnrs", joinColumns = @JoinColumn(name = "campusid"))
    @OrderBy("fax")
    private Set<TelefoonNr> telefoonNrs;

    @OneToMany(mappedBy = "campus")
    @OrderBy("voornaam, familienaam")
    private Set<Docent> docenten;

    public Set<TelefoonNr> getTelefoonNrs() {
        return Collections.unmodifiableSet(telefoonNrs);
    }

    public Set<Docent> getDocenten() {
        return Collections.unmodifiableSet(docenten);
    }

    public void add(Docent docent) {
        docenten.add(docent);
        if (docent.getCampus() != this) {
            docent.setCampus(this);
        }
    }

    public void remove(Docent docent) {
        docenten.remove(docent);
        if (docent.getCampus() == this) {
            docent.setCampus(null);
        }
    }

    public void add(TelefoonNr telefoonNr) {
        telefoonNrs.add(telefoonNr);
    }

    public void remove(TelefoonNr telefoonNr) {
        telefoonNrs.remove(telefoonNr);
    }

    public Campus(String naam, Adres adres) {
        this.naam = naam;
        this.adres = adres;
        this.telefoonNrs = new HashSet<>();
        this.docenten = new LinkedHashSet<>();
    }

    protected Campus(){}

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Adres getAdres() {
        return adres;
    }
}
