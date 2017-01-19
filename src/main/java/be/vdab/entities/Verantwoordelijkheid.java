package be.vdab.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Maarten Westelinck on 19/01/2017 for fietsacademy.
 */
@Entity
@Table(name = "verantwoordelijkheden")
public class Verantwoordelijkheid implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;

    @ManyToMany
    @JoinTable(
            name = "docentenverantwoordelijkheden",
            joinColumns = @JoinColumn(name = "verantwoordelijkheidId"),
            inverseJoinColumns = @JoinColumn(name = "docentId")
    )
    private Set<Docent> docenten = new LinkedHashSet<>();

    public void add(Docent docent) {
        docenten.add(docent);
        if ( ! docent.getVerantwoordelijkheden().contains(this)) {
            docent.add(this);
        }
    }

    public Set<Docent> getDocenten() {
        return Collections.unmodifiableSet(docenten);
    }

    public void remove(Docent docent) {
        docenten.remove(docent);
        if ( docent.getVerantwoordelijkheden().contains(this)) {
            docent.remove(this);
        }
    }

    protected Verantwoordelijkheid() {}

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Verantwoordelijkheid that = (Verantwoordelijkheid) o;

        return getNaam().equals(that.getNaam());
    }

    @Override
    public int hashCode() {
        return getNaam().hashCode();
    }
}
