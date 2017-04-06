package squees_generator.bootstrap;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import squees_generator.domain.*;
import squees_generator.services.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Brandon.O'Donnell on 3/13/2017.
 */
@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

//    @Autowired
//    private MagicDeckService magicDeckService;

    @Autowired
    private MagicTypesService magicTypesService;

    @Autowired
    private ColorIdentityService colorIdentityService;

    @Autowired
    private MagicCardService magicCardService;

    @Autowired
    private ParametersService parametersService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        populateMagicTypes();
//        populateColorIdentity();
//        importAllCards();
//        populateParameters();
//        populateParametersCommander();
    }

    public void populateParametersCommander() {

        Parameters parameters = new Parameters();

        parameters.setCardsPerRound(1);
        parameters.setMagicFormat("commander");
        parameters.setDeckSize(99);
        parameters.setSideboardSize(1);
        parameters.setQuantityMax(1);
        parameters.setQuantityPreferred(1);
        parameters.setQuantityLegendary(1);

        parameters.setWeightCreature(3);
        parameters.setWeightEnchantment(3);
        parameters.setWeightArtifact(3);
        parameters.setWeightPlaneswalker(2);
        parameters.setWeightInstant(3);
        parameters.setWeightSorcery(4);
        parameters.setWeightWhite(3);
        parameters.setWeightBlue(3);
        parameters.setWeightBlack(0);
        parameters.setWeightRed(0);
        parameters.setWeightGreen(0);
        parameters.setWeightColorless(0);
        parameters.setWeightCommon(4);
        parameters.setWeightUncommon(3);
        parameters.setWeightRare(2);
        parameters.setWeightMythic(1);

        parameters.setRareSameAsMythic(true);
        parameters.setCmcMin(0);
        parameters.setCmcMax(15);
        parameters.setCmcMean(4);
        parameters.setPercentLand(35);
        parameters.setPercentNonbasicLand(35);
        parameters.setGeneral1(magicCardService.getMagicCardById("Isperia the Inscrutable"));

//        parameters.setKeyWords(new ArrayList<>());
//        parameters.setMagicSets(new ArrayList<>());

        parametersService.saveParameters(parameters);


    }
    public void populateParameters() {

        Parameters parameters = new Parameters();

        parameters.setCardsPerRound(1);
        parameters.setMagicFormat("modern");
        parameters.setDeckSize(60);
        parameters.setSideboardSize(15);
        parameters.setQuantityMax(4);
        parameters.setQuantityPreferred(4);
        parameters.setQuantityLegendary(3);

        parameters.setWeightCreature(3);
        parameters.setWeightEnchantment(3);
        parameters.setWeightArtifact(3);
        parameters.setWeightPlaneswalker(2);
        parameters.setWeightInstant(3);
        parameters.setWeightSorcery(4);
        parameters.setWeightWhite(0);
        parameters.setWeightBlue(3);
        parameters.setWeightBlack(4);
        parameters.setWeightRed(0);
        parameters.setWeightGreen(2);
        parameters.setWeightColorless(0);
        parameters.setWeightCommon(4);
        parameters.setWeightUncommon(3);
        parameters.setWeightRare(2);
        parameters.setWeightMythic(1);

        parameters.setRareSameAsMythic(true);
        parameters.setCmcMin(0);
        parameters.setCmcMax(15);
        parameters.setCmcMean(4);
        parameters.setPercentLand(35);
        parameters.setPercentNonbasicLand(35);

//        parameters.setKeyWords(new ArrayList<>());
//        parameters.setMagicSets(new ArrayList<>());

        parametersService.saveParameters(parameters);


    }


    public void importAllCards() {
        int resultCount;
        int page = 1;
        int smallCount;
        MagicCard magicCard;
        String jsonString;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        //configuration for JsonPath
        Configuration conf = Configuration.defaultConfiguration();
        Configuration conf2 = conf.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);


        do{
            ResponseEntity<String> response = restTemplate.exchange("https://api.magicthegathering.io/v1/cards?page=" + page, HttpMethod.GET, entity, String.class);
            String responseBody = response.getBody();

            Object document = Configuration.defaultConfiguration().jsonProvider().parse(responseBody);

            resultCount = JsonPath.read(document, "$.cards.length()");

            for(int x =0; x < resultCount; ++x){
                magicCard = new MagicCard();
                magicCard.setCardName(JsonPath.read(document, "$.cards["+x+"].name"));
                magicCard.setRarity(JsonPath.read(document, "$.cards["+x+"].rarity"));

//                //cmc
//                if(!(JsonPath.using(conf2).parse(responseBody).read("$.cards[" + x + "].cmc")==null)) {
//                    magicCard.setCmc((float)JsonPath.using(conf2).parse(responseBody).read("$.cards["+x+"].cmc"));
//                }

                //url
                if(!(JsonPath.using(conf2).parse(responseBody).read("$.cards[" + x + "].imageUrl")==null)) {
                    magicCard.setImageUrl(JsonPath.using(conf2).parse(responseBody).read("$.cards[" + x + "].imageUrl"));
                }

                //set Legalities
                if(!(JsonPath.using(conf2).parse(responseBody).read("$.cards[" + x + "].legalities")==null)) {
                    smallCount = JsonPath.read(document, "$.cards[" + x + "].legalities.length()");
                    for (int y = 0; y < smallCount; ++y) {

                        magicCard.getLegalitiesList().add(new Legalities(JsonPath.read(document, "$.cards[" + x + "].legalities[" + y + "].format"),
                                JsonPath.read(document, "$.cards[" + x + "].legalities[" + y + "].legality")));
                    }
                }

                //set typesList
                if(!(JsonPath.using(conf2).parse(responseBody).read("$.cards[" + x + "].types")==null)) {
                    smallCount = JsonPath.read(document, "$.cards[" + x + "].types.length()");
                    for (int y = 0; y < smallCount; ++y) {
                        MagicTypes magicTypes = magicTypesService.getMagicTypesById(JsonPath.read(document, "$.cards[" + x + "].types[" + y + "]"));

                        magicCard.getMagicTypesList().add(magicTypes);
                    }
                }

                //colorIdentityList
                if(!(JsonPath.using(conf2).parse(responseBody).read("$.cards[" + x + "].colorIdentity")==null)) {
                    smallCount = JsonPath.read(document, "$.cards[" + x + "].colorIdentity.length()");
                    for (int y = 0; y < smallCount; ++y) {
                        ColorIdentity colorIdentity = colorIdentityService.getColorIdentityById(JsonPath.read(document, "$.cards[" + x + "].colorIdentity[" + y + "]"));

                        magicCard.getColorIdentityList().add(colorIdentity);
                    }
                }
                if(magicCardService.getMagicCardById(magicCard.getCardName())==null)
                    magicCardService.saveMagicCard(magicCard);
            }

            ++page;
        }while(resultCount>0);


    }

    public void populateMagicTypes(){
        MagicTypes magicTypes;
        if(magicTypesService.getMagicTypesById("creature")==null) {
            magicTypes = new MagicTypes("creature");
            magicTypesService.saveMagicTypes(magicTypes);
        }
        if(magicTypesService.getMagicTypesById("enchantment")==null) {
            magicTypes = new MagicTypes("enchantment");
            magicTypesService.saveMagicTypes(magicTypes);
        }
        if(magicTypesService.getMagicTypesById("artifact")==null) {
            magicTypes = new MagicTypes("artifact");
            magicTypesService.saveMagicTypes(magicTypes);
        }
        if(magicTypesService.getMagicTypesById("planeswalker")==null) {
            magicTypes = new MagicTypes("planeswalker");
            magicTypesService.saveMagicTypes(magicTypes);
        }
        if(magicTypesService.getMagicTypesById("instant")==null) {
            magicTypes = new MagicTypes("instant");
            magicTypesService.saveMagicTypes(magicTypes);
        }
        if(magicTypesService.getMagicTypesById("sorcery")==null) {
            magicTypes = new MagicTypes("sorcery");
            magicTypesService.saveMagicTypes(magicTypes);
        }
        if(magicTypesService.getMagicTypesById("land")==null) {
            magicTypes = new MagicTypes("land");
            magicTypesService.saveMagicTypes(magicTypes);
        }
        if(magicTypesService.getMagicTypesById("conspiracy")==null) {
            magicTypes = new MagicTypes("conspiracy");
            magicTypesService.saveMagicTypes(magicTypes);
        }
        if(magicTypesService.getMagicTypesById("phenomenon")==null) {
            magicTypes = new MagicTypes("phenomenon");
            magicTypesService.saveMagicTypes(magicTypes);
        }
        if(magicTypesService.getMagicTypesById("plane")==null) {
            magicTypes = new MagicTypes("plane");
            magicTypesService.saveMagicTypes(magicTypes);
        }
        if(magicTypesService.getMagicTypesById("scheme")==null) {
            magicTypes = new MagicTypes("scheme");
            magicTypesService.saveMagicTypes(magicTypes);
        }
        if(magicTypesService.getMagicTypesById("tribal")==null) {
            magicTypes = new MagicTypes("tribal");
            magicTypesService.saveMagicTypes(magicTypes);
        }
        if(magicTypesService.getMagicTypesById("vanguard")==null) {
            magicTypes = new MagicTypes("vanguard");
            magicTypesService.saveMagicTypes(magicTypes);
        }
    }

    public void populateColorIdentity(){
        ColorIdentity colorIdentity = new ColorIdentity("w");
        colorIdentityService.saveColorIdentity(colorIdentity);

        colorIdentity = new ColorIdentity("u");
        colorIdentityService.saveColorIdentity(colorIdentity);

        colorIdentity = new ColorIdentity("b");
        colorIdentityService.saveColorIdentity(colorIdentity);

        colorIdentity = new ColorIdentity("r");
        colorIdentityService.saveColorIdentity(colorIdentity);

        colorIdentity = new ColorIdentity("g");
        colorIdentityService.saveColorIdentity(colorIdentity);
    }
}
