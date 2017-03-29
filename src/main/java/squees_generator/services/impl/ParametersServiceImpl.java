package squees_generator.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import squees_generator.domain.Parameters;
import squees_generator.repositories.ParametersRepository;
import squees_generator.services.ParametersService;

/**
 * Created by Brandon.O'Donnell on 3/28/2017.
 */
@Service
public class ParametersServiceImpl implements ParametersService{

    @Autowired
    private ParametersRepository parametersRepository;

    @Override
    public Iterable<Parameters> listAllParameters() {
        return parametersRepository.findAll();
    }

    @Override
    public Parameters getParametersById(Integer id) {
        return parametersRepository.findOne(id);
    }

    @Override
    public Parameters saveParameters(Parameters parameters) {
        return parametersRepository.save(parameters);
    }

    @Override
    public Iterable<Parameters> saveParametersList(Iterable<Parameters> parametersIterable) {
        return parametersRepository.save(parametersIterable);
    }

    @Override
    public void deleteParameters(Integer id) {
        parametersRepository.delete(id);
    }
}
