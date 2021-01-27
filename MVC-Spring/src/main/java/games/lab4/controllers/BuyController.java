package games.lab4.controllers;

import games.lab4.models.*;
import games.lab4.repository.*;
import games.lab4.services.KoszykService;
import games.lab4.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/shop")
@SessionAttributes({"dostawa","platnosc"})
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
    @Autowired
    private PlatnoscRep platnoscRep;
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRep orderRep;
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



        x.setOrder(new OrderShop());

        var order=x.getOrder();

 order.setGame(new ArrayList<Game>(z.getGame()));
 order.setPrice(z.getPrice());

 userRep.save(x);

        model.addAttribute("order",order);


        return "shop/order";
    }

    @PostMapping("/info")
    public String info(@ModelAttribute("order")@Valid OrderShop order, Errors result)
    {
        Authentication y= SecurityContextHolder.getContext().getAuthentication();

        var x= userRep.findByUsername(y.getName());

        orderService.saveOrder(order,x);







        return "redirect:/";
    }


    @ModelAttribute("dostawa")
    public List<Dostawa> loadDostawa() {
        return dostawaRep.findAll();
    }
    @ModelAttribute("platnosc")
    public List<Platnosc> loadPlatnosc() {
        return platnoscRep.findAll();
    }

}
