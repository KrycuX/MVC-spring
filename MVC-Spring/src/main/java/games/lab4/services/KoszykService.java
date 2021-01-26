package games.lab4.services;

import games.lab4.models.Game;
import games.lab4.models.Koszyk;
import org.springframework.stereotype.Service;

import java.util.List;


public interface KoszykService {

 void addGame(Koszyk koszyk, Long id);
 void delete(Koszyk koszyk, Long id);
}
