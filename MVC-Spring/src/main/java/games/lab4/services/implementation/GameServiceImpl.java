package games.lab4.services.implementation;

import games.lab4.controllers.filters.GameSearchFilter;
import games.lab4.models.Game;
import games.lab4.models.Photo;
import games.lab4.models.Rate;
import games.lab4.models.RodzajGry;
import games.lab4.repository.GameRepository;
import games.lab4.services.GameService;
import games.lab4.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PhotoService photoService;
    @Override
    public Optional<Game> findGameById(Long along) {
        return gameRepository.findById(along);
    }

    @Override
    public Page<Game> findGames(GameSearchFilter filter, Pageable pageable) throws OperationNotSupportedException {
        return gameRepository.findGameByTitleIgnoreCaseContainingOrAndAuthorIgnoreCaseContaining(filter.getPhraseLIKE(), filter.getPhraseLIKE(), pageable);
    }

    @Override
    public void saveGame(Game game, MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()==false)
        {
            var photo=new Photo();
            photo.setFileName(multipartFile.getOriginalFilename());
            photo.setType(multipartFile.getContentType());
            photo.setContent(multipartFile.getBytes());
            game.setPhoto(photo);
            photoService.saveFile((multipartFile));
        }
           gameRepository.save(game);
    }

    @Override
    public List<Rate> findAllRate() {
        return null;
    }

    @Override
    public List<RodzajGry> findGenres() {
        return null;
    }

    @Override
    public void deleteGame(Optional<Long> id) {
        gameRepository.deleteById(id.get());

    }
}
