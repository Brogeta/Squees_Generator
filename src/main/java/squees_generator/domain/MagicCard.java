package squees_generator.domain;/**
 * Created by Brandon.O'Donnell on 3/27/2017.
 */

import javax.persistence.*;

/**
 * Created by Brandon.O'Donnell on 3/27/2017.
 */
@Entity
public class MagicCard {

    //region    DATA
    @Id
    @Column(name = "MagicCardId")
    private String cardName;

    @Version
    private Integer version;

    //endregion

    //region    CONSTRUCTORS
    public MagicCard() {
    }

    //endregion

    //region        GET / SET
    public MagicCard(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    //endregion
}
