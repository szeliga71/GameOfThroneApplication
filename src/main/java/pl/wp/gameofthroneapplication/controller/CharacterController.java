package pl.wp.gameofthroneapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wp.gameofthroneapplication.connection.CharacterAPIHandler;
import pl.wp.gameofthroneapplication.model.CharacterSearchObject;

import java.util.Random;

@Controller
public class CharacterController {

    CharacterAPIHandler characterAPIHandler = new CharacterAPIHandler();

    @GetMapping("/CharacterSearch")
    public String getCharacter(Model model) {
        model.addAttribute("characterSearchObject", new CharacterSearchObject());
        return "searchedCharacter";
    }

    @PostMapping("/CharacterSearch")
    public String getSpecificCharacter(Model model, CharacterSearchObject characterSearchObject) {
        model.addAttribute("character", characterAPIHandler.getCharacterByName(characterSearchObject.getName()));
        return "character";
    }

    @GetMapping("/character")
    public String getRandomCharacter(Model model) {
        Random random = new Random();
        model.addAttribute("character", characterAPIHandler.getCharacterById(String.valueOf(random.nextInt(900) + 1)));
        return "character";
    }
}