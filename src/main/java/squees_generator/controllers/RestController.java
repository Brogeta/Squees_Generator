package squees_generator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Brandon.O'Donnell on 3/27/2017.
 */
@Controller
public class RestController {

    @RequestMapping("/parameters")
    public String parametersPage() {

        return "parameters";
    }

    @RequestMapping("/magicDeck")
    public String magicDeckPage() {

        return "magicDeck";
    }
}
