package be.vdab.valueobjects;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Maarten Westelinck on 19/01/2017 for fietsacademy.
 *
 */
@Embeddable
public class TelefoonNr implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nummer;
    private boolean fax;
    private String opmerking;
    public TelefoonNr(String nummer, boolean fax, String opmerking) {
        this.nummer = nummer;
        this.fax = fax;
        this.opmerking = opmerking; // je maakt getters voor nummer,fax,opmerking
    }
    protected TelefoonNr() {} // default constructor voor JPA
    @Override
    public String toString() {
        return nummer;
    }
    @Override
    public boolean equals(Object obj) {
        if ( ! (obj instanceof TelefoonNr)) {
            return false;
        }
        TelefoonNr telefoonNr = (TelefoonNr) obj;
        return nummer.equalsIgnoreCase(telefoonNr.nummer);
    }

    public String getNummer() {
        return nummer;
    }

    public boolean isFax() {
        return fax;
    }

    public String getOpmerking() {
        return opmerking;
    }

    @Override
    public int hashCode() {
        return nummer.toUpperCase().hashCode();
    }
}
