package squees_generator.services;

import squees_generator.domain.MagicCard;

/**
 * Created by Brandon.O'Donnell on 3/29/2017.
 */
public interface MagicCardService {

    Iterable<MagicCard> listAllMagicCard();

    MagicCard getMagicCardById(String id);

    MagicCard saveMagicCard(MagicCard magicCard);

    Iterable<MagicCard> saveMagicCardList(Iterable<MagicCard> magicCardIterable);

    void deleteMagicCard(String id);

}
