package be.vdab.valueobjects;

/**
 * Created by Maarten Westelinck on 16/01/2017 for fietsacademy.
 *
 */
public class VoornaamEnId {
    private final long id;
    private final String voornaam;

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public VoornaamEnId(long id, String voornaam) {
        this.id = id;
        this.voornaam = voornaam;
    }
}
