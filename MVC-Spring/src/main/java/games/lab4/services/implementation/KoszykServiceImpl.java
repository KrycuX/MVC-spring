package games.lab4.services.implementation;

import games.lab4.models.Game;
import games.lab4.models.Koszyk;
import games.lab4.repository.GameRepository;
import games.lab4.repository.KoszykRep;
import games.lab4.services.KoszykService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KoszykServiceImpl implements KoszykService {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    KoszykRep koszykRep;


    @Override
    public void addGame(Koszyk koszyk,Long id) {
if(koszyk==null)
{
    koszyk=new Koszyk();
}

List<Game> x=koszyk.getGame();
        Float price;
if(koszyk.getPrice()==null)
{
    price=0.00f;
}else {
    price = koszyk.getPrice();
}

var g=gameRepository.findById(id).get();

Float cena = g.getPrice();
x.add(g);
price=price+Math.round(cena*100.0f)/100.0f;

koszyk.setGame(x);
koszyk.setPrice(Math.round(price*100.0f)/100.0f);
koszykRep.save(koszyk);

    }

    @Override
    public void delete(Koszyk koszyk, Long id) {

   var g= koszyk.getGame();
    var index=0;
Float cena=0.00f;
        for (var x:g
             ) {

            if(x.getId()==id)
            {
                cena=x.getPrice();
              break;
            }
            index++;
        }

        Float price = koszyk.getPrice();
        price=price-Math.round(cena*100.0f)/100.0f;

g.remove(index);
koszyk.setGame(g);
koszyk.setPrice(Math.round(price*100.0f)/100.0f);
koszykRep.save(koszyk);




    }
}
