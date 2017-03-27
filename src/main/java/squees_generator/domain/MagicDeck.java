package squees_generator.domain;/**
 * Created by Brandon.O'Donnell on 3/27/2017.
 */

import javax.persistence.*;
import java.util.List;

/**
 * Created by Brandon.O'Donnell on 3/27/2017.
 */
@Entity
public class MagicDeck {

    //region    DATA
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MagicDeckId")
    private Integer id;

    @Version
    private Integer version;


    @ManyToMany
    private List<MagicCard>     magicCards;

    private String              name;

    //endregion

    //region        CONSTRUCTORS

    public MagicDeck() {
    }

    public MagicDeck(String name) {
        this.name = name;
    }

    //endregion

    //region        GET / SET
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

    public List<MagicCard> getMagicCards() {
        return magicCards;
    }

    public void setMagicCards(List<MagicCard> magicCards) {
        this.magicCards = magicCards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //endregion
}
