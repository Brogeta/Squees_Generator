package squees_generator.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import squees_generator.domain.MagicCard;
import squees_generator.repositories.MagicCardRepository;
import squees_generator.services.MagicCardService;

import java.util.List;

/**
 * Created by Brandon.O'Donnell on 3/29/2017.
 */
@Service
public class MagicCardServiceImpl implements MagicCardService {

    @Autowired
    private MagicCardRepository magicCardRepository;

    @Override
    public Iterable<MagicCard> listAllMagicCard() {
        return magicCardRepository.findAll();
    }

    @Override
    public MagicCard getMagicCardById(String id) {
        return magicCardRepository.findOne(id);
    }

    @Override
    public MagicCard saveMagicCard(MagicCard magicCard) {
        return magicCardRepository.save(magicCard);
    }

    @Override
    public Iterable<MagicCard> saveMagicCardList(Iterable<MagicCard> magicCardIterable) {
        return magicCardRepository.save(magicCardIterable);
    }

    @Override
    public void deleteMagicCard(String id) {
        magicCardRepository.delete(id);
    }

    @Override
    public List<MagicCard> findByRequirements(String C1, String C2,String C3,String C4,String C5, String type, String rarity, String format) {
        return magicCardRepository.findByRequirements(C1, C2, C3, C4, C5, type, rarity, format);
    }

}
