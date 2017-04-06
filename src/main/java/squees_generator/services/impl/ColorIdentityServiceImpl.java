package squees_generator.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import squees_generator.domain.ColorIdentity;
import squees_generator.repositories.ColorIdentityRepository;
import squees_generator.services.ColorIdentityService;

/**
 * Created by Brandon.O'Donnell on 3/30/2017.
 */
@Service
public class ColorIdentityServiceImpl implements ColorIdentityService {

    @Autowired
    private ColorIdentityRepository colorIdentityRepository;

    @Override
    public Iterable<ColorIdentity> listAllColorIdentity() {
        return colorIdentityRepository.findAll();
    }

    @Override
    public ColorIdentity getColorIdentityById(String id) {
        return colorIdentityRepository.findOne(id);
    }

    @Override
    public ColorIdentity saveColorIdentity(ColorIdentity colorIdentity) {
        return colorIdentityRepository.save(colorIdentity);
    }

    @Override
    public Iterable<ColorIdentity> saveColorIdentityList(Iterable<ColorIdentity> colorIdentityIterable) {
        return colorIdentityRepository.save(colorIdentityIterable);
    }

    @Override
    public void deleteColorIdentity(String id) {
        colorIdentityRepository.delete(id);
    }
}
