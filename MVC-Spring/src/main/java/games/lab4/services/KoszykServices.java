package games.lab4.services;

import games.lab4.models.Game;
import games.lab4.models.Koszyk;

import java.util.List;
import java.util.Optional;

public interface KoszykServices {


    void addToList(List<Game> games);
    float price (List<Game> game);
    Optional<Koszyk> getKoszyk(int id);
}
