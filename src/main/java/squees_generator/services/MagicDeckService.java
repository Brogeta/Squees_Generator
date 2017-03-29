package squees_generator.services;

import squees_generator.domain.MagicDeck;
import squees_generator.domain.Parameters;
import squees_generator.domain.nonDB.DeckRequirements;

/**
 * Created by Brandon.O'Donnell on 3/28/2017.
 */
public interface MagicDeckService {
    Iterable<MagicDeck> listAllMagicDeck();

    MagicDeck getMagicDeckById(Integer id);

    MagicDeck saveMagicDeck(MagicDeck magicDeck);

    Iterable<MagicDeck> saveMagicDeckList(Iterable<MagicDeck> magicDeckIterable);

    void deleteMagicDeck(Integer id);

    MagicDeck createDeck(Integer id);

}
