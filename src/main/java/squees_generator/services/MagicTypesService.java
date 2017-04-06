package squees_generator.services;

import squees_generator.domain.MagicTypes;

/**
 * Created by Brandon.O'Donnell on 3/30/2017.
 */
public interface MagicTypesService {

    Iterable<MagicTypes> listAllMagicTypes();

    MagicTypes getMagicTypesById(String id);

    MagicTypes saveMagicTypes(MagicTypes magicTypes);

    Iterable<MagicTypes> saveMagicTypesList(Iterable<MagicTypes> magicTypesIterable);

    void deleteMagicTypes(String id);
}
