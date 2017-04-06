package squees_generator.domain;/**
 * Created by Brandon.O'Donnell on 3/27/2017.
 */

import javax.persistence.*;
import java.util.ArrayList;
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
    private List<MagicCard> mainDeck =new ArrayList<>();

    @ManyToMany
    private List<MagicCard> sideboard = new ArrayList<>();

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

    public List<MagicCard> getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(List<MagicCard> mainDeck) {
        this.mainDeck = mainDeck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MagicCard> getSideboard() {
        return sideboard;
    }

    public void setSideboard(List<MagicCard> sideboard) {
        this.sideboard = sideboard;
    }

    //endregion

    //region    CUSTOM

    public boolean checkDeckForCard(String cardName) {
        for(MagicCard card: this.mainDeck) {
            if(card.getCardName().equals(cardName))
                return true;
        }
        for(MagicCard card: this.sideboard) {
            if(card.getCardName().equals(cardName))
                return true;
        }
        return false;
    }

    //endregion
}
