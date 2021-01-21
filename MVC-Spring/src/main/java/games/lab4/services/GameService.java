package games.lab4.services;

import games.lab4.controllers.filters.GameSearchFilter;
import games.lab4.models.Game;
import games.lab4.models.Rate;
import games.lab4.models.RodzajGry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface GameService {
    Optional<Game> findGameById(Long along);
    Page<Game> findGames(GameSearchFilter filter, Pageable pageable)throws OperationNotSupportedException;
    void saveGame(Game game, MultipartFile multipartFile) throws IOException;
    List<Rate> findAllRate();
    List<RodzajGry> findGenres();
    void deleteGame(Optional<Long> id);
}
