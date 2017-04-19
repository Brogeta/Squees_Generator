package squees_generator.domain;/**
 * Created by Brandon.O'Donnell on 3/27/2017.
 */

import javax.persistence.*;

/**
 * Created by Brandon.O'Donnell on 3/27/2017.
 */
@Entity
public class Parameters {

    //region    Data
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ParameterId")
    private Integer id;

    @Version
    private Integer version;

    private String name;

    @ManyToOne
    private MagicDeck   existingDeck;

    // anything greater than 1 is a draft
    private int         cardsPerRound;

    // used for searching legal cards. No effect on other parameters
    private String          magicFormat;


    @ManyToOne
    private MagicCard        general1;

    @ManyToOne
    private MagicCard        general2;

    private int         deckSize;
    private int         sideboardSize;
    private int         quantityMax;

    //Type Weight
    private int         weightCreature;
    private int         weightEnchantment;
    private int         weightArtifact;
    private int         weightPlaneswalker;
    private int         weightInstant;
    private int         weightSorcery;

    //Color Weight
    private boolean         weightWhite;
    private boolean         weightBlue;
    private boolean         weightBlack;
    private boolean         weightRed;
    private boolean         weightGreen;

    //Rarity weight
    private int         weightCommon;
    private int         weightUncommon;
    private int         weightRare;
    private int         weightMythic;

    //Lands
    private int         percentLand;
    private int         percentNonbasicLand;



    //endregion

    //region    CONSTRUCTORS

    public Parameters() {
        this.general1 = null;
        this.general2 = null;
    }

    //endregion

    //region    GET / SET

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MagicDeck getExistingDeck() {
        return existingDeck;
    }

    public void setExistingDeck(MagicDeck existingDeck) {
        this.existingDeck = existingDeck;
    }

    public int getCardsPerRound() {
        return cardsPerRound;
    }

    public void setCardsPerRound(int cardsPerRound) {
        this.cardsPerRound = cardsPerRound;
    }

    public String getMagicFormat() {
        return magicFormat;
    }

    public void setMagicFormat(String magicFormat) {
        this.magicFormat = magicFormat;
    }

    public MagicCard getGeneral1() {
        return general1;
    }

    public void setGeneral1(MagicCard general1) {
        this.general1 = general1;
    }

    public MagicCard getGeneral2() {
        return general2;
    }

    public void setGeneral2(MagicCard general2) {
        this.general2 = general2;
    }

    public int getDeckSize() {
        return deckSize;
    }

    public void setDeckSize(int deckSize) {
        this.deckSize = deckSize;
    }

    public int getSideboardSize() {
        return sideboardSize;
    }

    public void setSideboardSize(int sideboardSize) {
        this.sideboardSize = sideboardSize;
    }

    public int getQuantityMax() {
        return quantityMax;
    }

    public void setQuantityMax(int quantityMax) {
        this.quantityMax = quantityMax;
    }

    public int getWeightCreature() {
        return weightCreature;
    }

    public void setWeightCreature(int weightCreature) {
        this.weightCreature = weightCreature;
    }

    public int getWeightEnchantment() {
        return weightEnchantment;
    }

    public void setWeightEnchantment(int weightEnchantment) {
        this.weightEnchantment = weightEnchantment;
    }

    public int getWeightArtifact() {
        return weightArtifact;
    }

    public void setWeightArtifact(int weightArtifact) {
        this.weightArtifact = weightArtifact;
    }

    public int getWeightPlaneswalker() {
        return weightPlaneswalker;
    }

    public void setWeightPlaneswalker(int weightPlaneswalker) {
        this.weightPlaneswalker = weightPlaneswalker;
    }

    public int getWeightInstant() {
        return weightInstant;
    }

    public void setWeightInstant(int weightInstant) {
        this.weightInstant = weightInstant;
    }

    public int getWeightSorcery() {
        return weightSorcery;
    }

    public void setWeightSorcery(int weightSorcery) {
        this.weightSorcery = weightSorcery;
    }

    public int getWeightCommon() {
        return weightCommon;
    }

    public void setWeightCommon(int weightCommon) {
        this.weightCommon = weightCommon;
    }

    public int getWeightUncommon() {
        return weightUncommon;
    }

    public void setWeightUncommon(int weightUncommon) {
        this.weightUncommon = weightUncommon;
    }

    public int getWeightRare() {
        return weightRare;
    }

    public void setWeightRare(int weightRare) {
        this.weightRare = weightRare;
    }

    public int getWeightMythic() {
        return weightMythic;
    }

    public void setWeightMythic(int weightMythic) {
        this.weightMythic = weightMythic;
    }

    public int getPercentLand() {
        return percentLand;
    }

    public void setPercentLand(int percentLand) {
        this.percentLand = percentLand;
    }

    public int getPercentNonbasicLand() {
        return percentNonbasicLand;
    }

    public void setPercentNonbasicLand(int percentNonbasicLand) {
        this.percentNonbasicLand = percentNonbasicLand;
    }

    public boolean isWeightWhite() {
        return weightWhite;
    }

    public void setWeightWhite(boolean weightWhite) {
        this.weightWhite = weightWhite;
    }

    public boolean isWeightBlue() {
        return weightBlue;
    }

    public void setWeightBlue(boolean weightBlue) {
        this.weightBlue = weightBlue;
    }

    public boolean isWeightBlack() {
        return weightBlack;
    }

    public void setWeightBlack(boolean weightBlack) {
        this.weightBlack = weightBlack;
    }

    public boolean isWeightRed() {
        return weightRed;
    }

    public void setWeightRed(boolean weightRed) {
        this.weightRed = weightRed;
    }

    public boolean isWeightGreen() {
        return weightGreen;
    }

    public void setWeightGreen(boolean weightGreen) {
        this.weightGreen = weightGreen;
    }

    //    public List<String> getKeyWords() {
//        return keyWords;
//    }
//
//    public void setKeyWords(List<String> keyWords) {
//        this.keyWords = keyWords;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //endregion

}
