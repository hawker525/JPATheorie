package be.vdab.valueobjects;

import java.math.BigDecimal;

/**
 * Created by Maarten Westelinck on 16/01/2017 for fietsacademy.
 */
public class AantalDocentenPerWedde {

    private final BigDecimal wedde;
    private final long aantal;

    public AantalDocentenPerWedde(BigDecimal wedde, long aantal) {
        this.wedde = wedde;
        this.aantal = aantal;
    }

    public BigDecimal getWedde() {
        return wedde;
    }

    public long getAantal() {
        return aantal;
    }
}
