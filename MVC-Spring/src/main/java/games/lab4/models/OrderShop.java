package games.lab4.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter

public class OrderShop {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderDate;
    private Float price;


    @ManyToOne
    private Dostawa dostawa;


    @ManyToOne
    private Platnosc platnosc;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Game> game;






}
