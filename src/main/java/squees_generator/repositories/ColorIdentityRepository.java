package squees_generator.repositories;

import org.springframework.data.repository.CrudRepository;
import squees_generator.domain.ColorIdentity;

/**
 * Created by Brandon.O'Donnell on 3/30/2017.
 */
public interface ColorIdentityRepository extends CrudRepository<ColorIdentity, String> {
}
