package squees_generator.rest;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import squees_generator.domain.MagicDeck;
import squees_generator.services.MagicDeckService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Brandon.O'Donnell on 3/28/2017.
 */
@RestController
@RequestMapping("/api/magicDeck")
public class MagicDeckRest {

    private Logger log = Logger.getLogger(MagicDeckRest.class);

    @Autowired
    private MagicDeckService magicDeckService;

    //GET ALL
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<MagicDeck> getAll() {
        return magicDeckService.listAllMagicDeck();
    }

    //GET BY ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MagicDeck getById(@PathVariable int id) {
        return magicDeckService.getMagicDeckById(id);
    }

    //SAVE
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public MagicDeck save(@ModelAttribute("magicDeck") MagicDeck magicDeck) {
        return magicDeckService.saveMagicDeck(magicDeck);
    }

    //DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable int id) {
        boolean result = false;
        try{
            magicDeckService.deleteMagicDeck(id);
            result = true;
        } catch (Exception ex) {
            log.info(ex);
        }
        return result;
    }

    //CREATE DECK
    @RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
    public MagicDeck createDeck(@PathVariable int id){
        return magicDeckService.createDeck(id);
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void getDownload(HttpServletResponse response, @PathVariable int id) {

        MagicDeck magicDeck = magicDeckService.getMagicDeckById(id);

        InputStream stream = magicDeck.deckAsInputStream();

        response.addHeader("Content-disposition", "attachment; filename=myfilename.txt");
        response.setContentType("txt/plain");

        try {
            IOUtils.copy(stream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ioX) {
            //put logger here
        }


    }
}
