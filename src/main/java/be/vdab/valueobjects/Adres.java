package be.vdab.valueobjects;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Maarten Westelinck on 19/01/2017 for fietsacademy.
 *
 */
@Embeddable
public class Adres implements Serializable{
    private static final long serialVersionUID = 1L;
    private String straat;
    private String huisNr;
    private String postcode;
    private String gemeente;

    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public Adres(String straat, String huisNr, String postcode, String gemeente) {
        this.straat = straat;
        this.huisNr = huisNr;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    protected Adres(){}
}
