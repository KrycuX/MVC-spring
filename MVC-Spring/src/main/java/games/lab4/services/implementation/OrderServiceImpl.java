package games.lab4.services.implementation;

import games.lab4.models.Game;
import games.lab4.models.Koszyk;
import games.lab4.models.OrderShop;
import games.lab4.models.User;
import games.lab4.repository.*;
import games.lab4.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRep orderRep;
    @Autowired
    private UserRep userRep;
    @Autowired
    private KoszykRep koszykRep;
    @Autowired
    private DostawaRep dostawaRep;
    @Autowired
    private PlatnoscRep platnoscRep;

    @Override
    public void saveOrder(OrderShop order, User user) {
        var u=userRep.findById(user.getId()).get();

        var k=koszykRep.findById(u.getKoszyk().getId()).get();
        var d=dostawaRep.findById(order.getDostawa().getId()).get();
        var p=platnoscRep.findById(order.getPlatnosc().getId()).get();

        Float price= d.getCena()+k.getPrice();
        order.setPrice(Math.round(price*100.0f)/100.0f);

        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        Date date=new Date();

         List<OrderShop> x=u.getOrder();
         var l=k.getGame();
         l=null;
        order.setGame(new ArrayList<Game>(k.getGame()));
        order.setOrderDate(dateFormat.format(date));
        order.setDostawa(d);
        order.setPlatnosc(p);
        order.setStatus("Oczekujace");
        x.add(order);
        orderRep.save(order);
        k.setGame(l);
k.setPrice(0.00f);
        u.setOrder(x);
        userRep.save(u);
        koszykRep.save(k);




    }
}
