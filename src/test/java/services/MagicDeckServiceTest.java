package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import squees_generator.Application;
import squees_generator.domain.MagicDeck;
import squees_generator.domain.Parameters;
import squees_generator.domain.nonDB.DeckRequirements;
import squees_generator.services.MagicDeckService;
import squees_generator.services.ParametersService;

/**
 * Created by Brandon.O'Donnell on 4/3/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class MagicDeckServiceTest {

    @Autowired
    private MagicDeckService magicDeckService;

    @Autowired
    private ParametersService parametersService;

    @Test
    public void createDeckTest() {

        MagicDeck magicDeck = magicDeckService.createDeck(1);

    }
}
