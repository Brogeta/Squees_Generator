package squees_generator.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import squees_generator.domain.MagicTypes;
import squees_generator.repositories.MagicTypesRepository;
import squees_generator.services.MagicTypesService;

/**
 * Created by Brandon.O'Donnell on 3/30/2017.
 */
@Service
public class MagicTypesServiceImpl implements MagicTypesService {

    @Autowired
    private MagicTypesRepository magicTypesRepository;

    @Override
    public Iterable<MagicTypes> listAllMagicTypes() {
        return magicTypesRepository.findAll();
    }

    @Override
    public MagicTypes getMagicTypesById(String id) {
        return magicTypesRepository.findOne(id);
    }

    @Override
    public MagicTypes saveMagicTypes(MagicTypes magicTypes) {
        return magicTypesRepository.save(magicTypes);
    }

    @Override
    public Iterable<MagicTypes> saveMagicTypesList(Iterable<MagicTypes> magicTypesIterable) {
        return magicTypesRepository.save(magicTypesIterable);
    }

    @Override
    public void deleteMagicTypes(String id) {
        magicTypesRepository.delete(id);
    }
}
