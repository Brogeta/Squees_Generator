package squees_generator.services;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import squees_generator.domain.MagicCard;

import java.util.List;

/**
 * Created by Brandon.O'Donnell on 3/29/2017.
 */
public interface MagicCardService {

    Iterable<MagicCard> listAllMagicCard();

    MagicCard getMagicCardById(String id);

    MagicCard saveMagicCard(MagicCard magicCard);

    Iterable<MagicCard> saveMagicCardList(Iterable<MagicCard> magicCardIterable);

    void deleteMagicCard(String id);

    List<MagicCard> findByRequirements(String C1, String C2,String C3,String C4,String C5, String type, String rarity, String format);

    List<MagicCard> findGenerals();

    List<MagicCard> findRandomGeneral();

}
