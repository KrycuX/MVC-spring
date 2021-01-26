package games.lab4.controllers;

import games.lab4.models.Game;
import games.lab4.models.Koszyk;
import games.lab4.services.KoszykServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/game")
@SessionAttributes({"rGry", "oGry","gameFilter"})
public class BuyController {


@Autowired
 KoszykServices koszykServices;

    @GetMapping("/shop")
    public String showShop(Model model, Integer id)
    {

        Optional<Koszyk> x=koszykServices.getKoszyk(id);


        model.addAttribute("koszyk",x);
        return "shop/Koszyk";
    }
}
