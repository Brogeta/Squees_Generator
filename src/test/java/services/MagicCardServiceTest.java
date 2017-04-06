package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import squees_generator.Application;
import squees_generator.services.MagicCardService;

/**
 * Created by Brandon.O'Donnell on 3/31/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class MagicCardServiceTest {

    @Autowired
    private MagicCardService magicCardService;

    @Test
    public void fillDeckTest() {

    }
}
