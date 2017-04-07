package squees_generator.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import squees_generator.common.helpers.StringHelper;
import squees_generator.domain.MagicCard;
import squees_generator.domain.MagicDeck;
import squees_generator.domain.Parameters;
import squees_generator.domain.nonDB.DeckRequirements;
import squees_generator.repositories.MagicCardRepository;
import squees_generator.repositories.MagicDeckRepository;
import squees_generator.repositories.ParametersRepository;
import squees_generator.services.MagicDeckService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Brandon.O'Donnell on 3/28/2017.
 */
@Service
public class MagicDeckServiceImpl implements MagicDeckService {

    private final int FAIL_LIMIT = 20;

    @Autowired
    private MagicDeckRepository magicDeckRepository;

    @Autowired
    private ParametersRepository parametersRepository;

    @Autowired
    private MagicCardRepository magicCardRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String serviceUrl = "https://api.magicthegathering.io/v1/cards";

    Random rng = new Random();

    @Override
    public Iterable<MagicDeck> listAllMagicDeck() {
        return magicDeckRepository.findAll();
    }

    @Override
    public MagicDeck getMagicDeckById(Integer id) {
        return magicDeckRepository.findOne(id);
    }

    @Override
    public MagicDeck saveMagicDeck(MagicDeck magicDeck) {
        return magicDeckRepository.save(magicDeck);
    }

    @Override
    public Iterable<MagicDeck> saveMagicDeckList(Iterable<MagicDeck> magicDeckIterable) {
        return magicDeckRepository.save(magicDeckIterable);
    }

    @Override
    public void deleteMagicDeck(Integer id) {
        magicDeckRepository.delete(id);
    }



    //Creates a deck from parameters and returns it
    @Override
    public MagicDeck createDeck(Integer id) {

        MagicDeck magicDeck;

        Parameters parameters = parametersRepository.findOne(id);

        if(parameters.getExistingDeck() != null) {
            magicDeck = parameters.getExistingDeck();
        }
        else
            magicDeck = new MagicDeck();

        DeckRequirements deckRequirements = new DeckRequirements();
        deckRequirements.createDeckRequirements(parameters);

        //fill the deck with the random cards
        fillGenerals(magicDeck, parameters, deckRequirements);
        fillLands(magicDeck, parameters, deckRequirements);
        fillMainDeck(magicDeck, parameters, deckRequirements);
        fillSideboard(magicDeck, parameters, deckRequirements);

        magicDeckRepository.save(magicDeck);

        deckAsTxt(magicDeck);
        return magicDeck;
    }

    //if the generals exist they will be added to the deck
    public void fillGenerals(MagicDeck magicDeck, Parameters parameters, DeckRequirements deckRequirements) {
        if(parameters.getGeneral1()!=null) {
            magicDeck.getSideboard().add(magicCardRepository.findOne(parameters.getGeneral1().getName()));
            deckRequirements.setSideboardSize(deckRequirements.getSideboardSize()-1);
        }
        if(parameters.getGeneral2()!=null) {
            magicDeck.getSideboard().add(magicCardRepository.findOne(parameters.getGeneral2().getName()));
            deckRequirements.setSideboardSize(deckRequirements.getSideboardSize()-1);
        }
    }

    //fills the deck with cards
    public void fillMainDeck(MagicDeck magicDeck, Parameters parameters, DeckRequirements deckRequirements) {
        List<MagicCard> magicCardList;
        String[] colors = new String[5];
        String selectedCard;
        int count = 0;
        Random rng = new Random();

        //populate colors
        for(String color : deckRequirements.getColorList()) {
            colors[count] = color;
            ++count;
        }

        //fill remaining slots with blank space
        while(count <5) {
            colors[count] = " ";
            ++count;
        }


        for(int x = deckRequirements.getDeckSize(); x>0;) {

            do{
                int typeRnd = rng.nextInt(deckRequirements.getTypeList().size());
                int rarityRnd = rng.nextInt(deckRequirements.getRarityList().size());

                magicCardList = magicCardRepository.findByRequirements(colors[0], colors[1], colors[2], colors[3], colors[4],
                        deckRequirements.getTypeList().get(typeRnd), deckRequirements.getRarityList().get(rarityRnd), parameters.getMagicFormat());

                count = magicCardList.size();
            }while(count == 0);

            do {


                int slot = rng.nextInt(count);

                selectedCard = magicCardList.get(slot).getName();

            } while (magicDeck.checkDeckForCard(selectedCard));


            //add the selected card to the deck either to quantityPreferred or deck limit reached
            int quantityCount =0;
            do {
                magicDeck.getMainDeck().add(magicCardRepository.findOne(selectedCard));

                --x;
                ++quantityCount;
            } while (x > 0 && quantityCount < parameters.getQuantityPreferred());
            quantityCount = 0;
        }
    }

    //fill the sideboard with cards
    public void fillSideboard(MagicDeck magicDeck, Parameters parameters, DeckRequirements deckRequirements) {
        List<MagicCard> magicCardList;
        String[] colors = new String[5];
        String selectedCard;
        int count = 0;
        Random rng = new Random();

        //populate colors
        for(String color : deckRequirements.getColorList()) {
            colors[count] = color;
            ++count;
        }

        //fill remaining slots with blank space
        while(count <5) {
            colors[count] = " ";
            ++count;
        }


        for(int x = deckRequirements.getSideboardSize(); x>0;) {
            do{
                int typeRnd = rng.nextInt(deckRequirements.getTypeList().size());
                int rarityRnd = rng.nextInt(deckRequirements.getRarityList().size());

                magicCardList = magicCardRepository.findByRequirements(colors[0], colors[1], colors[2], colors[3], colors[4],
                        deckRequirements.getTypeList().get(typeRnd), deckRequirements.getRarityList().get(rarityRnd), parameters.getMagicFormat());

                count = magicCardList.size();
            }while(count == 0);





            do {


                int slot = rng.nextInt(count);

                selectedCard = magicCardList.get(slot).getName();

            } while (magicDeck.checkDeckForCard(selectedCard));


            //add the selected card to the deck either to quantityPreferred or deck limit reached
            int quantityCount =0;
            do {
                magicDeck.getSideboard().add(magicCardRepository.findOne(selectedCard));

                --x;
                ++quantityCount;
            } while (x > 0 && quantityCount < parameters.getQuantityPreferred());
            quantityCount = 0;
        }
    }

    //fills the main deck with lands
    public void fillLands(MagicDeck magicDeck, Parameters parameters, DeckRequirements deckRequirements){
        List<MagicCard> magicCardList;
        String[] colors = new String[5];
        String selectedCard;
        int count = 0;
        Random rng = new Random();

        int quantityCount = 0;

        //fill basic lands
        for(int x = 0; x < deckRequirements.getBasicLands();) {
            if(parameters.getWeightWhite()>0 && x <deckRequirements.getBasicLands()) {
                magicDeck.getMainDeck().add(magicCardRepository.findOne("plains"));
                ++x;
            }
            if(parameters.getWeightBlue()>0 && x <deckRequirements.getBasicLands()) {
                magicDeck.getMainDeck().add(magicCardRepository.findOne("island"));
                ++x;
            }
            if(parameters.getWeightBlack()>0 && x <deckRequirements.getBasicLands()) {
                magicDeck.getMainDeck().add(magicCardRepository.findOne("swamp"));
                ++x;
            }
            if(parameters.getWeightRed()>0 && x <deckRequirements.getBasicLands()) {
                magicDeck.getMainDeck().add(magicCardRepository.findOne("mountain"));
                ++x;
            }
            if(parameters.getWeightGreen()>0 && x <deckRequirements.getBasicLands()) {
                magicDeck.getMainDeck().add(magicCardRepository.findOne("forest"));
                ++x;
            }


        }

        //fill nonBasic lands
        //populate colors
        for(String color : deckRequirements.getColorList()) {
            colors[count] = color;
            ++count;
        }

        //fill remaining slots with blank space
        while(count <5) {
            colors[count] = " ";
            ++count;
        }


        for(int x = deckRequirements.getNonBasicLands(); x>0;) {

            do{
                int typeRnd = rng.nextInt(deckRequirements.getTypeList().size());
                int rarityRnd = rng.nextInt(deckRequirements.getRarityList().size());

                magicCardList = magicCardRepository.findByRequirements(colors[0], colors[1], colors[2], colors[3], colors[4],
                        "land", deckRequirements.getRarityList().get(rarityRnd), parameters.getMagicFormat());

                count = magicCardList.size();
            }while(count == 0);

            do {


                int slot = rng.nextInt(count);

                selectedCard = magicCardList.get(slot).getName();

            } while (magicDeck.checkDeckForCard(selectedCard));


            //add the selected card to the deck either to quantityPreferred or deck limit reached
            quantityCount =0;
            do {
                magicDeck.getMainDeck().add(magicCardRepository.findOne(selectedCard));

                --x;
                ++quantityCount;
            } while (x > 0 && quantityCount < parameters.getQuantityPreferred());
            quantityCount = 0;
        }
    }

    //make a txt file for the deck
    public void deckAsTxt(MagicDeck magicDeck) {
        List<String> cardsMain = new ArrayList<>();
        List<Integer>   quantityMain = new ArrayList<>();
        List<String> cardsSide = new ArrayList<>();
        List<Integer>   quantitySide = new ArrayList<>();
        int index;

        for(MagicCard magicCard : magicDeck.getMainDeck()) {
            if(StringHelper.stringListHasString(cardsMain, magicCard.getName())) {
               index = cardsMain.indexOf(magicCard.getName());
                quantityMain.set(index,quantityMain.get(index)+1);

            }
            else {
                cardsMain.add(magicCard.getName());
                quantityMain.add(1);
            }
        }

        for(MagicCard magicCard : magicDeck.getSideboard()) {
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

        try{
            Files.write(Paths.get("deckTest.txt"), deckList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
