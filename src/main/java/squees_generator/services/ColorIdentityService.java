package squees_generator.services;

import squees_generator.domain.ColorIdentity;

/**
 * Created by Brandon.O'Donnell on 3/30/2017.
 */
public interface ColorIdentityService {
    Iterable<ColorIdentity> listAllColorIdentity();

    ColorIdentity getColorIdentityById(String id);

    ColorIdentity saveColorIdentity(ColorIdentity colorIdentity);

    Iterable<ColorIdentity> saveColorIdentityList(Iterable<ColorIdentity> colorIdentityIterable);

    void deleteColorIdentity(String id);
}
