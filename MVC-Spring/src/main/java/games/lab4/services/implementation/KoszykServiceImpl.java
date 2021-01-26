package games.lab4.services.implementation;

import games.lab4.models.Game;
import games.lab4.models.Koszyk;
import games.lab4.repository.GameRepository;
import games.lab4.repository.KoszykRep;
import games.lab4.services.KoszykServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KoszykServiceImpl implements KoszykServices {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private KoszykRep koszykRepository;

    @Override
    public void addToList(List<Game> games) {

    }

    @Override
    public float price(List<Game> game) {
        return 0;
    }

    @Override
    public Optional<Koszyk> getKoszyk(int id) {
        return koszykRepository.findById(id);
    }
}
