package repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import squees_generator.configuration.RepositoryConfiguration;
import squees_generator.domain.MagicCard;
import squees_generator.repositories.MagicCardRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Brandon.O'Donnell on 3/31/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class MagicCardRepositoryTest {

    @Autowired
    private MagicCardRepository magicCardRepository;


    @Test
    public void findByAlotTest() {

        List<String> colorList = new ArrayList<>();
        List<String> typeList = new ArrayList<>();

        colorList.add("w");
        colorList.add("b");
        colorList.add("r");

        typeList.add("instant");

        List<MagicCard> magicCardList = magicCardRepository.findByRarityAndMagicTypesListAndColorIdentityList("rare", null, null );

        assertEquals(5, magicCardList.size());

    }
}