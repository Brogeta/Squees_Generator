package squees_generator.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import squees_generator.domain.Parameters;
import squees_generator.services.MagicCardService;
import squees_generator.services.MagicDeckService;
import squees_generator.services.ParametersService;

import java.util.ArrayList;

/**
 * Created by Brandon.O'Donnell on 3/13/2017.
 */
@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

//    @Autowired
//    private MagicDeckService magicDeckService;
//
//    @Autowired
//    private MagicCardService magicCardService;

    @Autowired
    private ParametersService parametersService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        populateParameters();
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

}
