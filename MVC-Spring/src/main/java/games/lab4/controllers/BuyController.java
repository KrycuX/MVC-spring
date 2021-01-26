package games.lab4.controllers;

import games.lab4.models.Koszyk;
import games.lab4.repository.DostawaRep;
import games.lab4.repository.GameRepository;
import games.lab4.repository.KoszykRep;
import games.lab4.repository.UserRep;
import games.lab4.services.GameService;
import games.lab4.services.KoszykService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/shop")
@SessionAttributes({"rGry", "oGry","gameFilter"})
public class BuyController {

 @Autowired
 KoszykRep koszykRep;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    private KoszykService koszykService;

    @Autowired
    private UserRep userRep;

    @Autowired
    private DostawaRep dostawaRep;

    @GetMapping("/show")
    public String showShop(Model model)
    {
        Authentication y= SecurityContextHolder.getContext().getAuthentication();

        var x= userRep.findByUsername(y.getName());

        var z=koszykRep.findById(x.getKoszyk().getId()).get();

        model.addAttribute("shop",z.getGame());
        model.addAttribute("obj",z);

        return "shop/Koszyk";
    }



    @GetMapping("/add")
    public String addToCart(@RequestParam("id") Long idGra) {
        Authentication y = SecurityContextHolder.getContext().getAuthentication();

        var x = userRep.findByUsername(y.getName());


        koszykService.addGame(x.getKoszyk(), idGra);
        return "redirect:show";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long idGra) {

        Authentication y= SecurityContextHolder.getContext().getAuthentication();

        var x= userRep.findByUsername(y.getName());

        var z=koszykRep.findById(x.getKoszyk().getId()).get();

        koszykService.delete(z,idGra);



        return "redirect:show";
    }


    @GetMapping("/order")
public String order(Model model)
    {





        Authentication y= SecurityContextHolder.getContext().getAuthentication();

        var x= userRep.findByUsername(y.getName());

        var z=koszykRep.findById(x.getKoszyk().getId()).get();

        var d=dostawaRep.findAll();
        model.addAttribute("user",x);
        model.addAttribute("obj",z);
        model.addAttribute("dostawa",d);
        model.addAttribute("shop",z.getGame());
        return "shop/order";
    }




}
