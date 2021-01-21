package games.lab4.controllers;

import games.lab4.models.Game;
import games.lab4.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/game")
@SessionAttributes({"rGry", "oGry","gameFilter"})
public class DetailControler {

    @Autowired
    GameService gameService;

    @GetMapping("/show")
    public String show(Model model, @RequestParam("id") Long id) {
       Game game=gameService.findGameById(id).get();
       model.addAttribute("gra", game);
        return "Game/gameDetails";
    }
}
