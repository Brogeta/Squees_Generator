package squees_generator.domain;/**
 * Created by Brandon.O'Donnell on 4/4/2017.
 */

import javax.persistence.*;

/**
 * Created by Brandon.O'Donnell on 4/4/2017.
 */
@Entity
public class Legalities {

    //region DATA
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LegalitiesId")
    private Integer id;

    @Version
    private Integer version;

    private String  format;
    private String  legality;

    //endregion

    //region    CONSTRUCTORS

    public Legalities() {
    }

    public Legalities(String format, String legality) {
        this.format = format;
        this.legality = legality;
    }

    //endregion

    //region    GET / SET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLegality() {
        return legality;
    }

    public void setLegality(String legality) {
        this.legality = legality;
    }

    //endregion
}


