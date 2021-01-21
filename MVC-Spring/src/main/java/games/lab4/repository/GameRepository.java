package games.lab4.repository;

import games.lab4.controllers.filters.GameSearchFilter;
import games.lab4.models.Game;
import games.lab4.models.GameGenre;
import games.lab4.models.RodzajGry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository <Game, Long> {

    Page<Game> findGameByTitleIgnoreCaseContainingOrAndAuthorIgnoreCaseContaining(String phraseTitle, String phraseAuthor, Pageable pageable);
    Page<Game> findGameUsingNamedQuery(String phrase, Float minPrice, Float maxPrice, List<RodzajGry> rodzajGryList, List<GameGenre> gameGenres,Pageable pageable);






@Query("SELECT g from Game g where "+
        "("+
        ":phrase is null OR :phrase='' OR "+
        "upper(g.title) Like upper(:phrase) OR "+
        "upper(g.author) Like upper(:phrase) OR "+
        "upper(g.rodzajGry.name) Like upper(:phrase) "+
        ") "+
        "AND"+"(:minPrice is null or :minPrice <= g.price) "+
        "AND (:maxPrice is null OR :maxPrice >= g.price) "+
        "AND (COALESCE(:rodzajGryList) is null or g.rodzajGry in :rodzajGryList) "+
        "AND (COALESCE(:gameGenres) is null or EXISTS (SELECT r FROM g.genres r where r in :gameGenres )) "+
        "")

Page<Game> findGameUsingQuery(String phrase, Float minPrice, Float maxPrice, List<RodzajGry> rodzajGryList, List<GameGenre> gameGenres,Pageable pageable);


@EntityGraph(attributePaths = {"rodzajGry","genres"})
    @Query("SELECT g from Game g where "+
            "("+
            ":#{#filter.phraseLIKE} = '' OR "+
            "upper(g.title) Like upper(:#{#filter.phraseLIKE}) OR "+
            "upper(g.author) Like upper(:#{#filter.phraseLIKE}) OR "+
            "upper(g.rodzajGry.name) Like upper(:#{#filter.phraseLIKE}) "+
            ") "+
            "AND"+"(:#{#filter.minPrice} is null or :#{#filter.minPrice} <= g.price) "+
            "AND (:#{#filter.maxPrice} is null OR :#{#filter.maxPrice} >= g.price) "+
            "AND (:#{#filter.rodzajGryListEmpty} = true or g.rodzajGry in :#{#filter.rodzajGryList}) "+
            "AND (:#{#filter.gameGenresEmpty}= true or EXISTS (SELECT r FROM g.genres r where r in :#{#filter.gameGenres})) "+
            "")
    Page<Game> findGameUsingSpEL(GameSearchFilter filter, Pageable pageable);

}
