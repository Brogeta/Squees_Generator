package squees_generator.domain.nonDB;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import squees_generator.common.helpers.StringHelper;
import squees_generator.domain.MagicCard;
import squees_generator.domain.Parameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Brandon.O'Donnell on 3/28/2017.
 */
public class DeckRequirements {

    //region    DATA

    private int             deckSize;
    private int             sideboardSize;

    //CMC
    private int         cmcMeanCurrent;

    //weight lists
    private List<String>    typeList;
    private List<String>    colorList;
    private List<String>    rarityList;


    //lands
    private int             basicLands;
    private int             nonBasicLands;

    //endregion

    //region CONSTRUCTORS

    public DeckRequirements() {
    }

    //endregion

    //region        GET / SET

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

    public int getCmcMeanCurrent() {
        return cmcMeanCurrent;
    }

    public void setCmcMeanCurrent(int cmcMeanCurrent) {
        this.cmcMeanCurrent = cmcMeanCurrent;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public List<String> getColorList() {
        return colorList;
    }

    public void setColorList(List<String> colorList) {
        this.colorList = colorList;
    }

    public List<String> getRarityList() {
        return rarityList;
    }

    public void setRarityList(List<String> rarityList) {
        this.rarityList = rarityList;
    }

    public int getBasicLands() {
        return basicLands;
    }

    public void setBasicLands(int basicLands) {
        this.basicLands = basicLands;
    }

    public int getNonBasicLands() {
        return nonBasicLands;
    }

    public void setNonBasicLands(int nonBasicLands) {
        this.nonBasicLands = nonBasicLands;
    }

    //endregion

    //region        CUSTOM METHODS

    public void createDeckRequirements(Parameters parameters) {

        this.deckSize = parameters.getDeckSize();
        this.sideboardSize = parameters.getSideboardSize();

        makeTypeList(parameters);
        makeColorList(parameters);
        makeRarityList(parameters);

        determineLands(parameters);
    }

    //takes parameters data a sets basicLands and nonBasicLands
    public void determineLands(Parameters parameters) {
        int totalLands = (int)(parameters.getDeckSize() * ((float)parameters.getPercentLand() / 100));
        this.nonBasicLands = (int)(totalLands * ((float)parameters.getPercentNonbasicLand() / 100));
        this.basicLands=totalLands-this.nonBasicLands;

        //update remaining deck size
        this.deckSize = this.deckSize - this.nonBasicLands - this.basicLands;
    }

    //uses parameters type weights to make a list
    public void makeTypeList(Parameters parameters) {

        this.typeList= new ArrayList<>();

        int y = 10;

        for(int x = 0; x < this.deckSize*10; ++x) {

            if(parameters.getWeightCreature() >= y)
                this.typeList.add("creature");

            if(parameters.getWeightEnchantment() >= y)
                this.typeList.add("enchantment");

            if(parameters.getWeightArtifact() >= y)
                this.typeList.add("artifact");

            if(parameters.getWeightPlaneswalker() >= y)
                this.typeList.add("planeswalker");

            if(parameters.getWeightInstant() >= y)
                this.typeList.add("instant");

            if(parameters.getWeightSorcery() >= y)
                this.typeList.add("sorcery");

            --y;
            if(y == 0)
                y = 10;
        }
    }

    //uses parameters color weights to make a list
    public void makeColorList(Parameters parameters) {

        this.colorList = new ArrayList<>();




            if(parameters.getWeightWhite() == 0)
                this.colorList.add("w");

            if(parameters.getWeightBlue() == 0)
                this.colorList.add("u");

            if(parameters.getWeightBlack() == 0)
                this.colorList.add("b");

            if(parameters.getWeightRed() == 0)
                this.colorList.add("r");

            if(parameters.getWeightGreen() == 0)
                this.colorList.add("g");

    }

    //uses parameters rarity weights to make a list
    public void makeRarityList(Parameters parameters) {
        this.rarityList = new ArrayList<>();

        int y = 10;

        for(int x = 0; x < this.deckSize*10; ++x) {

            if(parameters.getWeightCommon() >= y)
                this.rarityList.add("common");

            if(parameters.getWeightUncommon() >= y)
                this.rarityList.add("uncommon");

            if(parameters.getWeightRare() >= y)
                this.rarityList.add("rare");

            if(parameters.getWeightMythic() >= y)
                this.rarityList.add("mythic rare");


            --y;
            if(y == 0)
                y = 10;
        }
    }

    //returns a list of 'randomly' selected and unique colors to be searched
    public List<String> colorSearchList() {
        Random rng = new Random();
        int max =   this.colorList.size() - 5;

        int start = rng.nextInt(max);
        int legnth = rng.nextInt(4)+1;

        List<String> colorsToSearch = new ArrayList<>();

        for(int x = 0; x < legnth; ++x) {
            //If the color is new to colorsToSearch then add it to the list. else return the list
            if(!StringHelper.stringListHasString(colorsToSearch,this.colorList.get(start)))
                colorsToSearch.add(this.colorList.get(start));
            else
                return  colorsToSearch;

            ++start;
        }

        return colorsToSearch;
    }

    //Returns the colorIdentity part of the Magic API call
    public String colorSearchUrl() {
        List<String> searchList = colorSearchList();
        String  colorURL= "colorIdentity=\"";

        for(String color: searchList) {
            colorURL += color + "|";
        }
        colorURL += "\"&";

        return colorURL;
    }

    //Returns the Type part of the Magic API call
    public String typeSearchURL(int id) {
        return "types="+ this.typeList.get(id) + "&";
    }

    //Returns the Rarity part of the Magic API call
    public String raritySearchURL(int id) {
        return "rarity="+ this.rarityList.get(id) + "&";
    }


    public String colorsParam() {
        String colorSearch = new String();

        for(int x = 0; x < this.colorList.size(); ++x) {

            colorSearch += "'" + colorList.get(x) +"'";

            //add a , unless it is the last color
            if(x != this.colorList.size() -1) {
                colorSearch += ",";
            }
        }

        return colorSearch;
    }

    //endregion

}
