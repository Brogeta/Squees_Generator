package squees_generator.services.impl;

import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;
import squees_generator.domain.MagicCard;
import squees_generator.domain.MagicDeck;
import squees_generator.domain.Parameters;
import squees_generator.domain.nonDB.DeckRequirements;
import squees_generator.repositories.MagicCardRepository;
import squees_generator.repositories.MagicDeckRepository;
import squees_generator.repositories.ParametersRepository;
import squees_generator.services.MagicDeckService;

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
        fillLands(magicDeck, parameters, deckRequirements);
        fillMainDeck(magicDeck, parameters, deckRequirements);
        fillSideboard(magicDeck, parameters, deckRequirements);


        return magicDeck;
    }

    //adds lands to the deck
    public void fillLands(MagicDeck magicDeck, Parameters parameters, DeckRequirements deckRequirements) {

        String selectedCard = " ";
        String urlAdd;
        int failCount=0;
        int quantityCount = 0;

        //fill basic lands
        for(int x = 0; x < deckRequirements.getBasicLands(); ++x) {
            if(deckRequirements.getColorList().get(x).equals("w"))
                magicDeck.getMainDeck().add(magicCardRepository.findOne("plains"));
            if(deckRequirements.getColorList().get(x).equals("u"))
                magicDeck.getMainDeck().add(magicCardRepository.findOne("island"));
            if(deckRequirements.getColorList().get(x).equals("b"))
                magicDeck.getMainDeck().add(magicCardRepository.findOne("swamp"));
            if(deckRequirements.getColorList().get(x).equals("r"))
                magicDeck.getMainDeck().add(magicCardRepository.findOne("mountain"));
            if(deckRequirements.getColorList().get(x).equals("g"))
                magicDeck.getMainDeck().add(magicCardRepository.findOne("forest"));

            deckRequirements.setDeckSize(deckRequirements.getDeckSize()-1);
        }

        //fill nonBasic Lands
        for (int x = 0; x < deckRequirements.getNonBasicLands();) {

            urlAdd = "?random=true&types=land&" + deckRequirements.colorSearchUrl();


            do {
                try {
                    selectedCard = JsonPath.read(restTemplate.getForObject(serviceUrl + urlAdd, Json.class), "$.cards.name");
                } catch (HttpClientErrorException e) { // 404
                    // Nothing found
                }

                ++failCount;
            }while(magicDeck.checkDeckForCard(selectedCard) && failCount < FAIL_LIMIT);

            failCount = 0;


            do{
                magicDeck.getMainDeck().add(new MagicCard(selectedCard));

                ++x;
                ++quantityCount;
            }while(x< deckRequirements.getNonBasicLands() && quantityCount < parameters.getQuantityPreferred() );

            quantityCount = 0;
        }
    }

    //fills the deck with cards
    public void fillMainDeck(MagicDeck magicDeck, Parameters parameters, DeckRequirements deckRequirements) {
        String urlAdd;
        String selectedCard =" ";
        int failCount = 0;
        int quantityCount =0;


        for(int x = deckRequirements.getDeckSize(); x>0;) {

            //set search URL
            urlAdd = "?random=true&" + deckRequirements.colorSearchUrl() + deckRequirements.typeSearchURL(x)
                    + deckRequirements.raritySearchURL(x);

            //find a card not in the deck
            do {
                try {
                    selectedCard = JsonPath.read(restTemplate.getForObject(serviceUrl + urlAdd, Json.class), "$.cards.name");
                } catch (HttpClientErrorException e) { // 404
                    // Nothing found
                }
                ++failCount;
            } while (magicDeck.checkDeckForCard(selectedCard) && failCount < FAIL_LIMIT);

            //add the selected card to the deck either to quantityPreferred or deck limit reached
            do {
                magicDeck.getMainDeck().add(new MagicCard(selectedCard));

                --x;
                ++quantityCount;
            } while (x > 0 && quantityCount < parameters.getQuantityPreferred());
            quantityCount = 0;
        }
    }

    //fills the deck with cards
    public void fillSideboard(MagicDeck magicDeck, Parameters parameters, DeckRequirements deckRequirements) {
        String urlAdd;
        String selectedCard =" ";
        int failCount = 0;
        int quantityCount =0;


        for(int x = deckRequirements.getSideboardSize(); x>0;) {

            //set search URL
            urlAdd = "?random=true&" + deckRequirements.colorSearchUrl() + deckRequirements.typeSearchURL(x)
                    + deckRequirements.raritySearchURL(x);

            //find a card not in the deck
            do {
                try {
                    selectedCard = JsonPath.read(restTemplate.getForObject(serviceUrl + urlAdd, Json.class), "$.cards.name");
                } catch (HttpClientErrorException e) { // 404
                    // Nothing found
                }
                ++failCount;
            } while (magicDeck.checkDeckForCard(selectedCard) && failCount < FAIL_LIMIT);

            //add the selected card to the deck either to quantityPreferred or deck limit reached
            do {
                magicDeck.getSideboard().add(new MagicCard(selectedCard));

                --x;
                ++quantityCount;
            } while (x > 0 && quantityCount < parameters.getQuantityPreferred());
            quantityCount = 0;
        }
    }
}
