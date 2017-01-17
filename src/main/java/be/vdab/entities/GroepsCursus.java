package be.vdab.entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by Maarten Westelinck on 17/01/2017 for fietsacademy.
 */
@Entity
@Table(name = "groepscursussen")
public class GroepsCursus extends Cursus {
    private LocalDate van;
    private LocalDate tot;
}
