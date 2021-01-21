package games.lab4.controllers;


import games.lab4.controllers.filters.GameSearchFilter;
import games.lab4.controllers.validators.SameTitleAndAuthor;
import games.lab4.models.Game;
import games.lab4.models.GameGenre;
import games.lab4.models.Rate;
import games.lab4.models.RodzajGry;
import games.lab4.repository.GameGenreRep;
import games.lab4.repository.GameRepository;
import games.lab4.repository.RateRep;
import games.lab4.repository.RodzajGryRep;


import games.lab4.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.naming.OperationNotSupportedException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static games.lab4.controllers.filters.GameSearchFilter.QUERY_MODE.*;


@Controller
@RequestMapping("/game")
@SessionAttributes({"rGry", "oGry","gameFilter"})
public class GameController {

    private GameRepository gameRepository;
    private RodzajGryRep rodzajGryRep;
    private RateRep rateRep;
    private GameGenreRep gameGenreRep;
@Autowired
    private GameService gameService;
    public GameController(GameRepository gameRepository, RodzajGryRep rodzajGryRep, RateRep rateRep, GameGenreRep gameGenreRep) {
        this.gameRepository = gameRepository;
        this.rodzajGryRep = rodzajGryRep;
        this.rateRep = rateRep;
        this.gameGenreRep = gameGenreRep;

    }

    //details(w Realizacji bo coś nie chce działac)
    /*
    @GetMapping("/show")

    public String showGame(Model model, Optional<Long> id) {

        model.addAttribute("gry", gameRepository.findById(id.get()));
        return "Game/gameDetails";
    }*/

    @GetMapping("/loginPanel")
    public String loginPanel(Model model, Optional<Long> id) {
        model.addAttribute("activePage", "loginPanel");

        return "Game/loginForm";
    }

    //usuwanie
    @GetMapping("/delete")
    public String delete(Model m, Optional<Long> id) {
        gameService.deleteGame(id);

        return "redirect:listEdit";
    }


    @ModelAttribute("gameFilter")
    public GameSearchFilter loadEmptyFilter() {
        return new GameSearchFilter();
    }

//Lista z opcja edycji i usuwania

    @RequestMapping(value = "/listEdit", method = {RequestMethod.GET, RequestMethod.POST})
    public String listBook(Model m, @ModelAttribute("gameFilter") GameSearchFilter filter, @PageableDefault(sort = "title") Pageable pageable) throws OperationNotSupportedException {
        Page page = null;

        switch (filter.getQueryMode()) {
            case NAMED_METHOD -> {
                page = gameRepository.findGameByTitleIgnoreCaseContainingOrAndAuthorIgnoreCaseContaining(filter.getPhrase(), filter.getPhrase(), pageable);
            }

            case NAMED_QUERY -> {
                page = gameRepository.findGameUsingNamedQuery(
                        filter.getPhraseLIKE(),
                        filter.getMinPrice(),
                        filter.getMaxPrice(),
                        filter.isRodzajGryListEmpty() ? null : filter.getRodzajGryList(),
                        filter.isGameGenresEmpty() ? null : filter.getGameGenres(),
                        pageable
                );
            }
            case QUERY -> {
                page = gameRepository.findGameUsingQuery(
                        filter.getPhraseLIKE(),
                        filter.getMinPrice(),
                        filter.getMaxPrice(),
                        filter.isRodzajGryListEmpty() ? null : filter.getRodzajGryList(),
                        filter.isGameGenresEmpty() ? null : filter.getGameGenres(),
                        pageable

                );
            }
            case ENTITY_GRAPH -> {
                page = gameRepository.findGameUsingSpEL(filter, pageable);
            }

            default -> throw new OperationNotSupportedException(String.format("'%s' nie jest obslugiwany", filter.getQueryMode()));

        }


        m.addAttribute("page", page);
        m.addAttribute("activePage", "listEdit");
        return "Game/gameListEdit";
    }


    //Zwykla Lista
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String listBook2(Model m, @ModelAttribute("gameFilter") GameSearchFilter filter, @PageableDefault(sort = "title") Pageable pageable) throws OperationNotSupportedException {//?page=0&size=10&sort=property,asc&sort=property2.dsc


        Page page = null;

        switch (filter.getQueryMode()) {
            case NAMED_METHOD -> {
               page = gameRepository.findGameByTitleIgnoreCaseContainingOrAndAuthorIgnoreCaseContaining(filter.getPhrase(), filter.getPhrase(), pageable);
                //page=gameService.findGames(filter,pageable);
            }

            case NAMED_QUERY -> {
                page = gameRepository.findGameUsingNamedQuery(
                        filter.getPhraseLIKE(),
                        filter.getMinPrice(),
                        filter.getMaxPrice(),
                        filter.isRodzajGryListEmpty() ? null : filter.getRodzajGryList(),
                        filter.isGameGenresEmpty() ? null : filter.getGameGenres(),
                        pageable
                );
            }
            case QUERY -> {
                page = gameRepository.findGameUsingQuery(
                        filter.getPhraseLIKE(),
                        filter.getMinPrice(),
                        filter.getMaxPrice(),
                        filter.isRodzajGryListEmpty() ? null : filter.getRodzajGryList(),
                        filter.isGameGenresEmpty() ? null : filter.getGameGenres(),
                        pageable

                );
            }
            case ENTITY_GRAPH -> {
                page = gameRepository.findGameUsingSpEL(filter, pageable);
            }
          /*  case CRITERIA -> {
                page=gameRepository.findAll(
                        Specification.where(
                                GameSpec.findByPhrase(filter.getPhraseLIKE()).and(
                                        GameSpec.findByPriceRange(filter.getMinPrice(),filter.getMaxPrice()))
                        ),pageable
                );
            }*/

            default -> throw new OperationNotSupportedException(String.format("'%s' nie jest obslugiwany", filter.getQueryMode()));

        }


        m.addAttribute("page", page);
        m.addAttribute("activePage", "list");
        return "Game/gameList";
    }

    // Formularz
    @GetMapping({"/edit", "/add"})
    public String addOrEdit(Model model, Optional<Long> id) {
        var games = new Game();

        if (id.isPresent()) {
            model.addAttribute("gry", gameRepository.findById(id.get()));
        } else {
            model.addAttribute("gry", games);
        }

        model.addAttribute("activePage", "add");

        return "Game/gameForm";


    }

    //Metoda do Zapisywania
    @PostMapping("/save")
    public String save(MultipartFile multipartFile,
            @ModelAttribute("gry") @Valid Game game, Errors result) throws IOException {


        if (result.hasErrors()) {
            return "Game/gameForm";
        }

        gameService.saveGame(game, multipartFile);

        return "redirect:list";
    }


    @ModelAttribute("rGry")
    public List<RodzajGry> loadRodzajGry() {
        return rodzajGryRep.findAll();
    }

    @ModelAttribute("oGry")
    public List<Rate> loadRate() {
        return rateRep.findAll();
    }

    @InitBinder("gry")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(new SameTitleAndAuthor());
    }

    @RequestMapping(value = "/list", method = {RequestMethod.POST}, params = {"clear"})
    public RedirectView clear(@ModelAttribute("gameFilter") GameSearchFilter filter, @PageableDefault(sort = "title") Pageable pageable) {
        filter.clear();
        return new RedirectView("list" + parametry(pageable));
    }
    @RequestMapping(value = "/listEdit", method = {RequestMethod.POST}, params = {"clear"})
    public RedirectView clear2(@ModelAttribute("gameFilter") GameSearchFilter filter, @PageableDefault(sort = "title") Pageable pageable) {
        filter.clear();
        return new RedirectView("listEdit" + parametry(pageable));
    }


    public String parametry(Pageable pageable) {

        return String.format("?page=%d&size=%d&sort=%s", pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().toString().replace(": ", ","));

    }

    @ModelAttribute("dziedziny")
    public List<GameGenre> loadGameGenre() {
        return gameGenreRep.findAll();
    }

    @ModelAttribute("typyZapytan")
    public GameSearchFilter.QUERY_MODE[] loadQueryModes() {
        return GameSearchFilter.QUERY_MODE.values();
    }

}
