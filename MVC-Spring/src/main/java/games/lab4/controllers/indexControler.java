package games.lab4.controllers;

import games.lab4.baza;
import games.lab4.repository.GameRepository;
import games.lab4.repository.RateRep;
import games.lab4.repository.RodzajGryRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class indexControler {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private RodzajGryRep rodzajGryRep;
    @Autowired
    private RateRep rateRep;



    @GetMapping("")
    public String showGame(Model m, Optional<Long> id )

    {
        m.addAttribute("gry", gameRepository.findAll());
        return "index";
    }


}
