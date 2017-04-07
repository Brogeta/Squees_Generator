package squees_generator.domain;/**
 * Created by Brandon.O'Donnell on 3/27/2017.
 */

import squees_generator.common.helpers.StringHelper;

import javax.persistence.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            if(card.getName().equals(cardName))
                return true;
        }
        for(MagicCard card: this.sideboard) {
            if(card.getName().equals(cardName))
                return true;
        }
        return false;
    }

    //make a txt file for the deck
    public InputStream deckAsInputStream() {
        List<String> cardsMain = new ArrayList<>();
        List<Integer>   quantityMain = new ArrayList<>();
        List<String> cardsSide = new ArrayList<>();
        List<Integer>   quantitySide = new ArrayList<>();
        int index;

        for(MagicCard magicCard : this.getMainDeck()) {
            if(StringHelper.stringListHasString(cardsMain, magicCard.getName())) {
                index = cardsMain.indexOf(magicCard.getName());
                quantityMain.set(index,quantityMain.get(index)+1);

            }
            else {
                cardsMain.add(magicCard.getName());
                quantityMain.add(1);
            }
        }

        for(MagicCard magicCard : this.getSideboard()) {
            if(StringHelper.stringListHasString(cardsSide, magicCard.getName())) {
                index = cardsSide.indexOf(magicCard.getName());
                quantitySide.set(index,quantitySide.get(index)+1);

            }
            else {
                cardsSide.add(magicCard.getName());
                quantitySide.add(1);
            }
        }

        List<String> deckList = new ArrayList<>();
        //add Maindeck
        for(int x = 0; x < cardsMain.size(); ++x) {
            deckList.add(quantityMain.get(x) +" "+ cardsMain.get(x));
        }
        //add space between main and side
        deckList.add(" ");
        //add sideboard
        for(int x = 0; x < cardsSide.size(); ++x) {
            deckList.add(quantitySide.get(x) +" "+ cardsSide.get(x));
        }

        StringBuilder deckString = new StringBuilder();
        for(String str : deckList) {
            deckString.append(str);
        }


        try{
            Files.write(Paths.get("deckTest.txt"), deckList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File("deckTest.txt");
        try{
            InputStream stream = new FileInputStream(file);
            return stream;
        } catch(FileNotFoundException ue) {
            //put logger here
        }
        return null;
    }

    //endregion
}
