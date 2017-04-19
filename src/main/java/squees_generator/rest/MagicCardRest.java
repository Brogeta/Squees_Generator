package squees_generator.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import squees_generator.domain.MagicCard;
import squees_generator.services.MagicCardService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Brandon.O'Donnell on 4/13/2017.
 */
@RestController
@RequestMapping("/api/magicCard")
public class MagicCardRest {

    private Logger log = Logger.getLogger(MagicCardRest.class);

    @Autowired
    private MagicCardService magicCardService;

    //GET ALL
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<MagicCard> getAll() {
        return magicCardService.listAllMagicCard();
    }

    //GET BY ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MagicCard getById(@PathVariable String id) {
        return magicCardService.getMagicCardById(id);
    }

    //SAVE
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public MagicCard save(@ModelAttribute("magicCard") MagicCard magicCard) {
        return magicCardService.saveMagicCard(magicCard);
    }

    //DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable String id) {
        boolean result = false;
        try{
            magicCardService.deleteMagicCard(id);
            result = true;
        } catch (Exception ex) {
            log.info(ex);
        }
        return result;
    }

    //GENERALS
    @RequestMapping(value = "/generals/{request}", method = RequestMethod.GET)
    public Iterable<MagicCard> generals(@PathVariable int request) {
        if(request == 0) {
            return magicCardService.findGenerals();
        }
        if(request == 1) {
            return magicCardService.findRandomGeneral();
        }
        else
            return null;
    }


}
