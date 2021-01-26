package games.lab4.repository;

import games.lab4.models.Game;
import games.lab4.models.Koszyk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KoszykRep extends JpaRepository<Koszyk, Long> {

Game findGameById(Long id);
List<Game> deleteGameById(Long id);


}
