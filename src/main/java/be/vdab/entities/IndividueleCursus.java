package be.vdab.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by Maarten Westelinck on 17/01/2017 for fietsacademy.
 */
@Entity
@Table(name = "individuelecursussen")
public class IndividueleCursus extends Cursus {
    private int duurtijd;
}
