package squees_generator.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import squees_generator.domain.MagicCard;
import squees_generator.repositories.MagicCardRepository;
import squees_generator.services.MagicCardService;

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
}
