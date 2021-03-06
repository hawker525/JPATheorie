package be.vdab.entities;

import be.vdab.enums.Geslacht;

import javax.persistence.*;
import javax.print.Doc;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by Maarten Westelinck on 19/12/2016.
 *
 */
//@NamedQuery(name = "Docent.findWeddeBetween", query = "select d from Docent d where d.wedde between :van and :tot order by d.wedde, d.id")


@Entity
@Table(name = "docenten")
@NamedEntityGraph(name = Docent.MET_CAMPUS
        , attributeNodes = @NamedAttributeNode(value = "campus", subgraph = "metManager"), subgraphs = @NamedSubgraph(name = "metManager", attributeNodes = @NamedAttributeNode("manager")))
public class Docent implements Serializable{
    private static final long serialVersionUID = 1L;
    public static final String MET_CAMPUS = "Docent.metCampus";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ElementCollection
    @CollectionTable(name = "docentenbijnamen", joinColumns = @JoinColumn(name = "docentid"))
    @Column(name = "Bijnaam")
    private Set<String> bijnamen;
    private String voornaam;
    private String familienaam;
    private BigDecimal wedde;
    private long rijksRegisterNr;
    @Enumerated(EnumType.STRING)
    private Geslacht geslacht;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "campusid")
    private Campus campus;

    @ManyToMany(mappedBy = "docenten")
    private Set<Verantwoordelijkheid> verantwoordelijkheden = new LinkedHashSet<>();

    public void add(Verantwoordelijkheid verantwoordelijkheid) {
        verantwoordelijkheden.add(verantwoordelijkheid);
        if ( ! verantwoordelijkheid.getDocenten().contains(this)) {
            verantwoordelijkheid.add(this);
        }
    }

    public void remove(Verantwoordelijkheid verantwoordelijkheid) {
        verantwoordelijkheden.remove(verantwoordelijkheid);
        if (verantwoordelijkheid.getDocenten().contains(this)) {
            verantwoordelijkheid.remove(this);
        }
    }

    public Set<Verantwoordelijkheid> getVerantwoordelijkheden() {
        return Collections.unmodifiableSet(verantwoordelijkheden);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Docent docent = (Docent) o;

        return getRijksRegisterNr() == docent.getRijksRegisterNr();
    }

    @Override
    public int hashCode() {
        return (int) (getRijksRegisterNr() ^ (getRijksRegisterNr() >>> 32));
    }


    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        if (this.campus != null && this.campus.getDocenten().contains(this)) {
            this.campus.remove(this);
        }
        this.campus = campus;
        if (campus != null && !campus.getDocenten().contains(this)) {
            campus.add(this);
        }
    }

    public Docent(String voornaam, String familienaam, BigDecimal wedde,  Geslacht geslacht, long rijksRegisterNr) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.wedde = wedde;
        this.rijksRegisterNr = rijksRegisterNr;
        this.geslacht = geslacht;
        bijnamen = new HashSet<>();
        verantwoordelijkheden = new LinkedHashSet<>();
    }

    protected Docent(){}

    public Set<String> getBijnamen() {
        return Collections.unmodifiableSet(bijnamen);
    }

    public void opslag(BigDecimal percentage) {
        BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
        wedde = wedde.multiply(factor).setScale(2, RoundingMode.HALF_UP);
    }

    public static boolean isVoornaamValid(String voornaam) {
        return voornaam != null && !voornaam.isEmpty();
    }

    public static boolean isFamilienaamValid(String familienaam) {
        return familienaam != null && !familienaam.isEmpty();
    }

    public static boolean isWeddeValid(BigDecimal wedde) {
        return wedde != null && wedde.compareTo(BigDecimal.ZERO) >= 0;
    }

    public void addBijnaam(String bijnaam) {
        bijnamen.add(bijnaam);
    }

    public void removeBijnaam(String bijnaam) {
        bijnamen.remove(bijnaam);
    }

    public static boolean isRijksRegisterNrValid(long rijksRegisterNr) {
        long getal = rijksRegisterNr / 100;
        if (rijksRegisterNr / 1_000_000_000 < 50) {
            getal += 2_000_000_000;
        }
        return rijksRegisterNr % 100 == 97 - getal % 97;
    }
    public void setVoornaam(String voornaam) {
        if ( ! isVoornaamValid(voornaam)) {
            throw new IllegalArgumentException();
        }
        this.voornaam = voornaam;
    }
    public void setFamilienaam(String familienaam) {
        if ( ! isFamilienaamValid(familienaam)) {
            throw new IllegalArgumentException();
        }
        this.familienaam = familienaam;
    }
    public void setWedde(BigDecimal wedde) {
        if ( ! isWeddeValid(wedde)) {
            throw new IllegalArgumentException();
        }
        this.wedde = wedde;
    }
    public void setGeslacht(Geslacht geslacht) {
        this.geslacht = geslacht;
    }
    public void setRijksRegisterNr(long rijksRegisterNr) {
        if ( ! isRijksRegisterNrValid(rijksRegisterNr)) {
            throw new IllegalArgumentException();
        }
        this.rijksRegisterNr = rijksRegisterNr;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }
    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public BigDecimal getWedde() {
        return wedde;
    }

    public long getRijksRegisterNr() {
        return rijksRegisterNr;
    }

    // je maakt getters voor de private variabelen, behalve voor serialVersionUID
    public String getNaam() {
        return voornaam + ' ' + familienaam;
    }
}
