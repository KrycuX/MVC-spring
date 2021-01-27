package games.lab4.models;

import games.lab4.controllers.validators.InvalidValues;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter

@NamedQuery(name="Game.findGameUsingNamedQuery",
query=
        "SELECT g from Game g where "+
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
                "AND ((g.realeseDate BETWEEN :dateFrom AND :dateTo) OR (g.realeseDate <= :dateFrom AND g.realeseDate >= :dateTo))"+
""
)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @InvalidValues(niedozwoloneWartosci = {"Test","brak"},ignoruj = true)
    private String title;

    @Size(min=2,max=25)
    @NotEmpty
    private String author;

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate realeseDate;
    @NumberFormat(pattern="#.00")
    @NotNull
    private Float price;
    private Boolean bestseller;

    @Valid
    @ManyToOne
    private Rate rate;


    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    private RodzajGry rodzajGry;

    @ManyToMany
    private Set<GameGenre> genres;



    private Photo photo;
    public Game() {
        rate=new Rate();
        rodzajGry=new RodzajGry();
        genres=new HashSet<>();
    }

    public Game(Long id, String title, String author, LocalDate realeseDate, Float price, Boolean bestseller) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.realeseDate = realeseDate;
        this.price = price;
        this.bestseller = bestseller;

        this.rate= new Rate();
        this.rodzajGry= new RodzajGry();
        this.genres=new HashSet<>();
    }


}
