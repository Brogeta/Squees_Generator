package squees_generator.bootstrap;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import squees_generator.domain.*;
import squees_generator.domain.DTO.MagicCardResponse;
import squees_generator.services.*;

import java.util.Arrays;

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
//        populateParametersTest();
    }

    public void populateParametersCommander() {

        Parameters parameters = new Parameters();

        parameters.setName("Commander");
        parameters.setCardsPerRound(1);
        parameters.setMagicFormat("commander");
        parameters.setDeckSize(99);
        parameters.setSideboardSize(1);
        parameters.setQuantityMax(1);

        parameters.setWeightCreature(3);
        parameters.setWeightEnchantment(3);
        parameters.setWeightArtifact(3);
        parameters.setWeightPlaneswalker(2);
        parameters.setWeightInstant(3);
        parameters.setWeightSorcery(4);
        parameters.setWeightWhite(true);
        parameters.setWeightBlue(true);
        parameters.setWeightBlack(false);
        parameters.setWeightRed(false);
        parameters.setWeightGreen(false);
        parameters.setWeightCommon(4);
        parameters.setWeightUncommon(3);
        parameters.setWeightRare(2);
        parameters.setWeightMythic(1);

        parameters.setPercentLand(35);
        parameters.setPercentNonbasicLand(35);
        parameters.setGeneral1(magicCardService.getMagicCardById("Isperia the Inscrutable"));

//        parameters.setKeyWords(new ArrayList<>());
//        parameters.setMagicSets(new ArrayList<>());

        parametersService.saveParameters(parameters);


    }

    public void populateParameters() {

        Parameters parameters = new Parameters();

        parameters.setName("Modern");
        parameters.setCardsPerRound(1);
        parameters.setMagicFormat("modern");
        parameters.setDeckSize(60);
        parameters.setSideboardSize(15);
        parameters.setQuantityMax(4);

        parameters.setWeightCreature(3);
        parameters.setWeightEnchantment(3);
        parameters.setWeightArtifact(3);
        parameters.setWeightPlaneswalker(2);
        parameters.setWeightInstant(3);
        parameters.setWeightSorcery(4);
        parameters.setWeightWhite(false);
        parameters.setWeightBlue(true);
        parameters.setWeightBlack(true);
        parameters.setWeightRed(false);
        parameters.setWeightGreen(true);
        parameters.setWeightCommon(4);
        parameters.setWeightUncommon(3);
        parameters.setWeightRare(2);
        parameters.setWeightMythic(1);

        parameters.setPercentLand(35);
        parameters.setPercentNonbasicLand(35);

        parametersService.saveParameters(parameters);


    }

    public void populateParametersTest() {

        Parameters parameters = new Parameters();

        parameters.setName("Test");
        parameters.setCardsPerRound(1);
        parameters.setMagicFormat("modern");
        parameters.setDeckSize(10);
        parameters.setSideboardSize(10);
        parameters.setQuantityMax(4);

        parameters.setWeightCreature(3);
        parameters.setWeightEnchantment(3);
        parameters.setWeightArtifact(3);
        parameters.setWeightPlaneswalker(2);
        parameters.setWeightInstant(3);
        parameters.setWeightSorcery(4);
        parameters.setWeightWhite(true);
        parameters.setWeightBlue(true);
        parameters.setWeightBlack(true);
        parameters.setWeightRed(true);
        parameters.setWeightGreen(true);
        parameters.setWeightCommon(4);
        parameters.setWeightUncommon(3);
        parameters.setWeightRare(2);
        parameters.setWeightMythic(1);

        parameters.setPercentLand(10);
        parameters.setPercentNonbasicLand(50);

        parametersService.saveParameters(parameters);


    }

    public void importAllCards() {
        int page = 1;
        MagicCardResponse responseBody;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        //configuration for JsonPath
        Configuration conf = Configuration.defaultConfiguration();
        Configuration conf2 = conf.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);


        do{
            ResponseEntity<MagicCardResponse> response = restTemplate.exchange("https://api.magicthegathering.io/v1/cards?page=" + page, HttpMethod.GET, entity, MagicCardResponse.class);
            responseBody = response.getBody();


            for(MagicCard magicCard : responseBody.getMagicCardList()) {
                if (magicCardService.getMagicCardById(magicCard.getName()) == null)
                    magicCardService.saveMagicCard(responseToCard(magicCard));
            }

            ++page;
        }while(responseBody.getMagicCardList() != null);


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

    public MagicCard responseToCard(MagicCard response) {
        MagicCard magicCard = new MagicCard();

        magicCard.setName(response.getName());
        magicCard.setCmc(response.getCmc());
        magicCard.setRarity(response.getRarity());
        magicCard.setImageUrl(response.getImageUrl());
        magicCard.setType(response.getType());

        response.getLegalities().forEach((legalities) -> magicCard.getLegalities().add(legalities));

        for(ColorIdentity colorIdentity : response.getColorIdentity()) {
            magicCard.getColorIdentity().add(colorIdentityService.getColorIdentityById(colorIdentity.getColor()));
        }

        for(MagicTypes magicTypes : response.getTypes()) {
            magicCard.getTypes().add(magicTypesService.getMagicTypesById(magicTypes.getTypeName()));
        }

        return magicCard;
    }
}
