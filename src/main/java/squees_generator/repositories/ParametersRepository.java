package squees_generator.repositories;

import org.springframework.data.repository.CrudRepository;
import squees_generator.domain.Parameters;

/**
 * Created by Brandon.O'Donnell on 3/28/2017.
 */
public interface ParametersRepository extends CrudRepository<Parameters, Integer> {
}
