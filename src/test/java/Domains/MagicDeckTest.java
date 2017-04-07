package Domains;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import squees_generator.configuration.RepositoryConfiguration;
import squees_generator.domain.MagicDeck;
import squees_generator.services.MagicDeckService;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

/**
 * Created by Brandon.O'Donnell on 4/7/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
@WebAppConfiguration
public class MagicDeckTest {

    @Autowired
    private MagicDeckService magicDeckService;

    @Test
    public void inputStreamTest() {
        MagicDeck magicDeck = magicDeckService.getMagicDeckById(4);

        InputStream inputStream = magicDeck.deckAsInputStream();

        assertNotNull(inputStream);
    }
}
