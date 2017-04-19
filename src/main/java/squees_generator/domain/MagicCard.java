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
    private String name;

    @Version
    private Integer version;

    private float cmc;
    private String rarity;

    private String type;

    @ManyToMany(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "typeName")
    private List<MagicTypes> types;

    @ManyToMany(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "color")
    private List<ColorIdentity> colorIdentity;

    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Legalities> legalities;

    private String imageUrl;

    //endregion

    //region    CONSTRUCTORS
    public MagicCard() {
        this.types = new ArrayList<>();
        this.colorIdentity = new ArrayList<>();
        this.legalities = new ArrayList<>();
    }

    public MagicCard(String cardName) {
        this.name = cardName;
        this.types = new ArrayList<>();
        this.colorIdentity = new ArrayList<>();
        this.legalities = new ArrayList<>();
    }

    //endregion

    //region        GET / SET
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<MagicTypes> getTypes() {
        return types;
    }

    public void setTypes(List<MagicTypes> types) {
        this.types = types;
    }

    public List<ColorIdentity> getColorIdentity() {
        return colorIdentity;
    }

    public void setColorIdentity(List<ColorIdentity> colorIdentity) {
        this.colorIdentity = colorIdentity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Legalities> getLegalities() {
        return legalities;
    }

    public void setLegalities(List<Legalities> legalities) {
        this.legalities = legalities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //endregion
}
