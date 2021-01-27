package games.lab4.services.implementation;

import games.lab4.models.Koszyk;
import games.lab4.models.OrderShop;
import games.lab4.models.User;
import games.lab4.repository.*;
import games.lab4.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        var z=koszykRep.findById(user.getKoszyk().getId()).get();
        var d=dostawaRep.findById(order.getDostawa().getId()).get();
        var p=platnoscRep.findById(order.getPlatnosc().getId()).get();

        Float price= d.getCena()+z.getPrice();
        order.setPrice(Math.round(price*100.0f)/100.0f);

        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        Date date=new Date();

        //var o=new OrderShop( dateFormat.format(date), Math.round(price*100.0f)/100.0f, d, p, z.getGame());

        order.setGame(z.getGame());
        order.setOrderDate(dateFormat.format(date));
        order.setDostawa(d);
        order.setPlatnosc(p);

        orderRep.save(order);



    }
}
