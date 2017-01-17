package be.vdab.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Maarten Westelinck on 17/01/2017 for fietsacademy.
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "cursussen")
public abstract class Cursus implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private long id;
    private String naam;

    @Override
    public String toString() {
        return naam;
    }
}
