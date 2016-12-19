package be.vdab.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Maarten Westelinck on 19/12/2016.
 */
@Entity
@Table(name = "docenten")
public class Docent implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String voornaam;
    private String familienaam;
    private BigDecimal wedde;
    private long rijksRegisterNr;
    // je maakt getters voor de private variabelen, behalve voor serialVersionUID
    public String getNaam() {
        return voornaam + ' ' + familienaam;
    }
}
