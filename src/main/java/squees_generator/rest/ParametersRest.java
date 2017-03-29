package squees_generator.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import squees_generator.domain.Parameters;
import squees_generator.services.ParametersService;

/**
 * Created by Brandon.O'Donnell on 3/28/2017.
 */
@RestController
@RequestMapping("/api/parameters")
public class ParametersRest {

    private Logger log = Logger.getLogger(ParametersRest.class);

    @Autowired
    private ParametersService parametersService;

    //GET ALL
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Parameters> getAll() {
        return parametersService.listAllParameters();
    }

    //GET BY ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Parameters getById(@PathVariable int id) {
        return parametersService.getParametersById(id);
    }

    //SAVE
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Parameters save(@ModelAttribute("parameters") Parameters parameters) {
        return parametersService.saveParameters(parameters);
    }

    //DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable int id) {
        boolean result = false;
        try{
            parametersService.deleteParameters(id);
            result = true;
        } catch (Exception ex) {
            log.info(ex);
        }
        return result;
    }
}
