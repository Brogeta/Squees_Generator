package squees_generator.services;

import squees_generator.domain.Parameters;

/**
 * Created by Brandon.O'Donnell on 3/28/2017.
 */
public interface ParametersService {
    Iterable<Parameters> listAllParameters();

    Parameters getParametersById(Integer id);

    Parameters saveParameters(Parameters parameters);

    Iterable<Parameters> saveParametersList(Iterable<Parameters> parametersIterable);

    void deleteParameters(Integer id);
}
