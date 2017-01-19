package be.vdab.entities;

import javax.persistence.*;

/**
 * Created by Maarten Westelinck on 19/01/2017 for fietsacademy.
 */
@Entity
@Table(name = "managers")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String voornaam;
    private String familienaam;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "manager")
    private Campus campus;

    protected Manager(){}

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public Campus getCampus() {
        return campus;
    }
}
