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
public class MagicCard {

    //region    DATA
    @Id
    @Column(name = "MagicCardId")
    private String cardName;

    @Version
    private Integer version;

    private float cmc;
    private String rarity;

    @ManyToMany(fetch= FetchType.EAGER)
    private List<MagicTypes> magicTypesList;

    @ManyToMany(fetch= FetchType.EAGER)
    private List<ColorIdentity> colorIdentityList;

    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Legalities> legalitiesList;

    private String imageUrl;

    //endregion

    //region    CONSTRUCTORS
    public MagicCard() {
        this.magicTypesList = new ArrayList<>();
        this.colorIdentityList = new ArrayList<>();
        this.legalitiesList = new ArrayList<>();
    }

    public MagicCard(String cardName) {
        this.cardName = cardName;
        this.magicTypesList = new ArrayList<>();
        this.colorIdentityList = new ArrayList<>();
        this.legalitiesList = new ArrayList<>();
    }

    //endregion

    //region        GET / SET
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

    public float getCmc() {
        return cmc;
    }

    public void setCmc(float cmc) {
        this.cmc = cmc;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public List<MagicTypes> getMagicTypesList() {
        return magicTypesList;
    }

    public void setMagicTypesList(List<MagicTypes> magicTypesList) {
        this.magicTypesList = magicTypesList;
    }

    public List<ColorIdentity> getColorIdentityList() {
        return colorIdentityList;
    }

    public void setColorIdentityList(List<ColorIdentity> colorIdentityList) {
        this.colorIdentityList = colorIdentityList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Legalities> getLegalitiesList() {
        return legalitiesList;
    }

    public void setLegalitiesList(List<Legalities> legalitiesList) {
        this.legalitiesList = legalitiesList;
    }

    //endregion
}
