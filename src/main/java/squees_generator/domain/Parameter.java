package squees_generator.domain;/**
 * Created by Brandon.O'Donnell on 3/27/2017.
 */

import javax.persistence.*;
import java.util.List;

/**
 * Created by Brandon.O'Donnell on 3/27/2017.
 */
@Entity
public class Parameter {

    //region    Data
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ParameterId")
    private Integer id;

    @Version
    private Integer version;

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
    private int         quantityPreferred;
    private int         quantityLegendary;

    //Type Weight
    private int         weightCreature;
    private int         weightEnchantment;
    private int         weightArtifact;
    private int         weightPlaneswalker;
    private int         weightInstant;
    private int         weightSorcery;

    //Color Weight
    private int         weightWhite;
    private int         weightBlue;
    private int         weightBlack;
    private int         weightRed;
    private int         weightGreen;
    private int         weightColorless;

    //Rarity weight
    private int         weightCommon;
    private int         weightUncommon;
    private int         weightRare;
    private int         weightMythic;
    private boolean     rareSameAsMythic;

    //CMC
    private int         cmcMin;
    private int         cmcMax;
    private int         cmcMean;

    //Lands
    private boolean     landFromCmc;
    private int         percentLand;
    private int         percentNonbasicLand;

    //Key Words
    private List<String>    keyWords;

    //sets
    private List<String>    magicSets;

    //endregion

}
