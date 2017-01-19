package be.vdab.entities;

import be.vdab.valueobjects.Adres;

import javax.persistence.*;
import java.io.Serializable;

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

    public Campus(String naam, Adres adres) {
        this.naam = naam;
        this.adres = adres;
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
